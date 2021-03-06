package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerPostOfficeService;
import vn.nextlogix.domain.CustomerPostOffice;


    import vn.nextlogix.repository.CustomerPostOfficeRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.CustomerPostOfficeSearchRepository;
import vn.nextlogix.service.dto.CustomerPostOfficeDTO;
import vn.nextlogix.service.dto.CustomerPostOfficeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.PostOffice;
    import vn.nextlogix.domain.Customer;
import vn.nextlogix.service.mapper.CustomerPostOfficeMapper;
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

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerPostOffice.
 */
@Service
@Transactional
public class CustomerPostOfficeServiceImpl implements CustomerPostOfficeService {

    private final Logger log = LoggerFactory.getLogger(CustomerPostOfficeServiceImpl.class);

    private final CustomerPostOfficeRepository customerPostOfficeRepository;

    private final CustomerPostOfficeMapper customerPostOfficeMapper;

    private final CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final PostOfficeSearchRepository postOfficeSearchRepository;
        private final PostOfficeMapper postOfficeMapper;

        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public CustomerPostOfficeServiceImpl(CustomerPostOfficeRepository customerPostOfficeRepository, CustomerPostOfficeMapper customerPostOfficeMapper, CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,PostOfficeSearchRepository postOfficeSearchRepository,PostOfficeMapper  postOfficeMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.customerPostOfficeRepository = customerPostOfficeRepository;
        this.customerPostOfficeMapper = customerPostOfficeMapper;
        this.customerPostOfficeSearchRepository = customerPostOfficeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.postOfficeSearchRepository = postOfficeSearchRepository;
                                     this.postOfficeMapper = postOfficeMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Save a customerPostOffice.
     *
     * @param customerPostOfficeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerPostOfficeDTO save(CustomerPostOfficeDTO customerPostOfficeDTO) {
        log.debug("Request to save CustomerPostOffice : {}", customerPostOfficeDTO);
        CustomerPostOffice customerPostOffice = customerPostOfficeMapper.toEntity(customerPostOfficeDTO);
        customerPostOffice = customerPostOfficeRepository.save(customerPostOffice);
        CustomerPostOfficeDTO result = customerPostOfficeMapper.toDto(customerPostOffice);
        customerPostOfficeSearchRepository.save(customerPostOffice);
        return result;
    }

    /**
     * Get all the customerPostOffices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerPostOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerPostOffices");
        return customerPostOfficeRepository.findAll(pageable)
            .map(customerPostOfficeMapper::toDto);
    }

    /**
     * Get one customerPostOffice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerPostOfficeDTO findOne(Long id) {
        log.debug("Request to get CustomerPostOffice : {}", id);
        CustomerPostOffice customerPostOffice = customerPostOfficeRepository.findOne(id);
        return customerPostOfficeMapper.toDto(customerPostOffice);
    }

    /**
     * Delete the customerPostOffice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerPostOffice : {}", id);
        customerPostOfficeRepository.delete(id);
        customerPostOfficeSearchRepository.delete(id);
    }

    /**
     * Search for the customerPostOffice corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerPostOfficeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerPostOffices for query {}", query);
        Page<CustomerPostOffice> result = customerPostOfficeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerPostOfficeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerPostOfficeDTO> searchExample(CustomerPostOfficeSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getPostOfficeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("postOffice.id", searchDto.getPostOfficeId()));
            }
            if(searchDto.getCustomerParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customerParent.id", searchDto.getCustomerParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<CustomerPostOffice> customerPostOfficePage= customerPostOfficeSearchRepository.search(query);
            List<CustomerPostOfficeDTO> customerPostOfficeList =  StreamSupport
            .stream(customerPostOfficePage.spliterator(), false)
            .map(customerPostOfficeMapper::toDto)
            .collect(Collectors.toList());
            customerPostOfficeList.forEach(customerPostOfficeDto -> {
            if(customerPostOfficeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerPostOfficeDto.getCompanyId());
                customerPostOfficeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerPostOfficeDto.getPostOfficeId()!=null){
                PostOffice postOffice= postOfficeSearchRepository.findOne(customerPostOfficeDto.getPostOfficeId());
                customerPostOfficeDto.setPostOfficeDTO(postOfficeMapper.toDto(postOffice));
            }
            if(customerPostOfficeDto.getCustomerParentId()!=null){
                Customer customer= customerSearchRepository.findOne(customerPostOfficeDto.getCustomerParentId());
                customerPostOfficeDto.setCustomerParentDTO(customerMapper.toDto(customer));
            }
            });
            return new PageImpl<>(customerPostOfficeList,pageable,customerPostOfficePage.getTotalElements());
        }
}
