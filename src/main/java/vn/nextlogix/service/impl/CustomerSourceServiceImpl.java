package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerSourceService;
import vn.nextlogix.domain.CustomerSource;
import vn.nextlogix.repository.CustomerSourceRepository;
import vn.nextlogix.repository.search.CustomerSourceSearchRepository;
import vn.nextlogix.service.dto.CustomerSourceDTO;
import vn.nextlogix.service.dto.CustomerSourceSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.CustomerSourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerSource.
 */
@Service
@Transactional
public class CustomerSourceServiceImpl implements CustomerSourceService {

    private final Logger log = LoggerFactory.getLogger(CustomerSourceServiceImpl.class);

    private final CustomerSourceRepository customerSourceRepository;

    private final CustomerSourceMapper customerSourceMapper;

    private final CustomerSourceSearchRepository customerSourceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public CustomerSourceServiceImpl(CustomerSourceRepository customerSourceRepository, CustomerSourceMapper customerSourceMapper, CustomerSourceSearchRepository customerSourceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.customerSourceRepository = customerSourceRepository;
        this.customerSourceMapper = customerSourceMapper;
        this.customerSourceSearchRepository = customerSourceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a customerSource.
     *
     * @param customerSourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerSourceDTO save(CustomerSourceDTO customerSourceDTO) {
        log.debug("Request to save CustomerSource : {}", customerSourceDTO);
        CustomerSource customerSource = customerSourceMapper.toEntity(customerSourceDTO);
        customerSource = customerSourceRepository.save(customerSource);
        CustomerSourceDTO result = customerSourceMapper.toDto(customerSource);
        customerSourceSearchRepository.save(customerSource);
        return result;
    }

    /**
     * Get all the customerSources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerSourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerSources");
        return customerSourceRepository.findAll(pageable)
            .map(customerSourceMapper::toDto);
    }

    /**
     * Get one customerSource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerSourceDTO findOne(Long id) {
        log.debug("Request to get CustomerSource : {}", id);
        CustomerSource customerSource = customerSourceRepository.findOne(id);
        return customerSourceMapper.toDto(customerSource);
    }

    /**
     * Delete the customerSource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerSource : {}", id);
        customerSourceRepository.delete(id);
        customerSourceSearchRepository.delete(id);
    }

    /**
     * Search for the customerSource corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerSourceDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerSources for query {}", query);
        Page<CustomerSource> result = customerSourceSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerSourceMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerSourceDTO> searchExample(CustomerSourceSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<CustomerSource> customerSourcePage= customerSourceSearchRepository.search(query);
            List<CustomerSourceDTO> customerSourceList =  StreamSupport
            .stream(customerSourcePage.spliterator(), false)
            .map(customerSourceMapper::toDto)
            .collect(Collectors.toList());
            customerSourceList.forEach(customerSourceDto -> {
            if(customerSourceDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerSourceDto.getCompanyId());
                customerSourceDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(customerSourceList,pageable,customerSourcePage.getTotalElements());
        }
}
