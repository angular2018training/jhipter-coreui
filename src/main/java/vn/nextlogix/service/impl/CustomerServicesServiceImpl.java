package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerServicesService;
import vn.nextlogix.domain.CustomerServices;


    import vn.nextlogix.repository.CustomerServicesRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.CustomerServicesSearchRepository;
import vn.nextlogix.service.dto.CustomerServicesDTO;
import vn.nextlogix.service.dto.CustomerServicesSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.OrderServices;
    import vn.nextlogix.domain.Quotation;
    import vn.nextlogix.domain.Customer;
import vn.nextlogix.service.mapper.CustomerServicesMapper;
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

    import vn.nextlogix.repository.search.OrderServicesSearchRepository;
    import vn.nextlogix.service.mapper.OrderServicesMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

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
 * Service Implementation for managing CustomerServices.
 */
@Service
@Transactional
public class CustomerServicesServiceImpl implements CustomerServicesService {

    private final Logger log = LoggerFactory.getLogger(CustomerServicesServiceImpl.class);

    private final CustomerServicesRepository customerServicesRepository;

    private final CustomerServicesMapper customerServicesMapper;

    private final CustomerServicesSearchRepository customerServicesSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final OrderServicesSearchRepository orderServicesSearchRepository;
        private final OrderServicesMapper orderServicesMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;

        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public CustomerServicesServiceImpl(CustomerServicesRepository customerServicesRepository, CustomerServicesMapper customerServicesMapper, CustomerServicesSearchRepository customerServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,OrderServicesSearchRepository orderServicesSearchRepository,OrderServicesMapper  orderServicesMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.customerServicesRepository = customerServicesRepository;
        this.customerServicesMapper = customerServicesMapper;
        this.customerServicesSearchRepository = customerServicesSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.orderServicesSearchRepository = orderServicesSearchRepository;
                                     this.orderServicesMapper = orderServicesMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Save a customerServices.
     *
     * @param customerServicesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerServicesDTO save(CustomerServicesDTO customerServicesDTO) {
        log.debug("Request to save CustomerServices : {}", customerServicesDTO);
        CustomerServices customerServices = customerServicesMapper.toEntity(customerServicesDTO);
        customerServices = customerServicesRepository.save(customerServices);
        CustomerServicesDTO result = customerServicesMapper.toDto(customerServices);
        customerServicesSearchRepository.save(customerServices);
        return result;
    }

    /**
     * Get all the customerServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerServicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerServices");
        return customerServicesRepository.findAll(pageable)
            .map(customerServicesMapper::toDto);
    }

    /**
     * Get one customerServices by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerServicesDTO findOne(Long id) {
        log.debug("Request to get CustomerServices : {}", id);
        CustomerServices customerServices = customerServicesRepository.findOne(id);
        return customerServicesMapper.toDto(customerServices);
    }

    /**
     * Delete the customerServices by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerServices : {}", id);
        customerServicesRepository.delete(id);
        customerServicesSearchRepository.delete(id);
    }

    /**
     * Search for the customerServices corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerServicesDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerServices for query {}", query);
        Page<CustomerServices> result = customerServicesSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerServicesMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerServicesDTO> searchExample(CustomerServicesSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getOrderServicesId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderServices.id", searchDto.getOrderServicesId()));
            }
            if(searchDto.getQuotationId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotation.id", searchDto.getQuotationId()));
            }
            if(searchDto.getCustomerParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customerParent.id", searchDto.getCustomerParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<CustomerServices> customerServicesPage= customerServicesSearchRepository.search(query);
            List<CustomerServicesDTO> customerServicesList =  StreamSupport
            .stream(customerServicesPage.spliterator(), false)
            .map(customerServicesMapper::toDto)
            .collect(Collectors.toList());
            customerServicesList.forEach(customerServicesDto -> {
            if(customerServicesDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerServicesDto.getCompanyId());
                customerServicesDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerServicesDto.getOrderServicesId()!=null){
                OrderServices orderServices= orderServicesSearchRepository.findOne(customerServicesDto.getOrderServicesId());
                customerServicesDto.setOrderServicesDTO(orderServicesMapper.toDto(orderServices));
            }
            if(customerServicesDto.getQuotationId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(customerServicesDto.getQuotationId());
                customerServicesDto.setQuotationDTO(quotationMapper.toDto(quotation));
            }
            if(customerServicesDto.getCustomerParentId()!=null){
                Customer customer= customerSearchRepository.findOne(customerServicesDto.getCustomerParentId());
                customerServicesDto.setCustomerParentDTO(customerMapper.toDto(customer));
            }
            });
            return new PageImpl<>(customerServicesList,pageable,customerServicesPage.getTotalElements());
        }
}
