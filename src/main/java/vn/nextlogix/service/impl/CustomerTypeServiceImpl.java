package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerTypeService;
import vn.nextlogix.domain.CustomerType;
import vn.nextlogix.repository.CustomerTypeRepository;
import vn.nextlogix.repository.search.CustomerTypeSearchRepository;
import vn.nextlogix.service.dto.CustomerTypeDTO;
import vn.nextlogix.service.dto.CustomerTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.CustomerTypeMapper;
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
 * Service Implementation for managing CustomerType.
 */
@Service
@Transactional
public class CustomerTypeServiceImpl implements CustomerTypeService {

    private final Logger log = LoggerFactory.getLogger(CustomerTypeServiceImpl.class);

    private final CustomerTypeRepository customerTypeRepository;

    private final CustomerTypeMapper customerTypeMapper;

    private final CustomerTypeSearchRepository customerTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public CustomerTypeServiceImpl(CustomerTypeRepository customerTypeRepository, CustomerTypeMapper customerTypeMapper, CustomerTypeSearchRepository customerTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.customerTypeRepository = customerTypeRepository;
        this.customerTypeMapper = customerTypeMapper;
        this.customerTypeSearchRepository = customerTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a customerType.
     *
     * @param customerTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerTypeDTO save(CustomerTypeDTO customerTypeDTO) {
        log.debug("Request to save CustomerType : {}", customerTypeDTO);
        CustomerType customerType = customerTypeMapper.toEntity(customerTypeDTO);
        customerType = customerTypeRepository.save(customerType);
        CustomerTypeDTO result = customerTypeMapper.toDto(customerType);
        customerTypeSearchRepository.save(customerType);
        return result;
    }

    /**
     * Get all the customerTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerTypes");
        return customerTypeRepository.findAll(pageable)
            .map(customerTypeMapper::toDto);
    }

    /**
     * Get one customerType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerTypeDTO findOne(Long id) {
        log.debug("Request to get CustomerType : {}", id);
        CustomerType customerType = customerTypeRepository.findOne(id);
        return customerTypeMapper.toDto(customerType);
    }

    /**
     * Delete the customerType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerType : {}", id);
        customerTypeRepository.delete(id);
        customerTypeSearchRepository.delete(id);
    }

    /**
     * Search for the customerType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerTypes for query {}", query);
        Page<CustomerType> result = customerTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerTypeDTO> searchExample(CustomerTypeSearchDTO searchDto, Pageable pageable) {
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
            Page<CustomerType> customerTypePage= customerTypeSearchRepository.search(query);
            List<CustomerTypeDTO> customerTypeList =  StreamSupport
            .stream(customerTypePage.spliterator(), false)
            .map(customerTypeMapper::toDto)
            .collect(Collectors.toList());
            customerTypeList.forEach(customerTypeDto -> {
            if(customerTypeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerTypeDto.getCompanyId());
                customerTypeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(customerTypeList,pageable,customerTypePage.getTotalElements());
        }
}
