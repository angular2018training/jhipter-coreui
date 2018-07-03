package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerService;
import vn.nextlogix.domain.Customer;


    import vn.nextlogix.repository.CustomerRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.CustomerSearchRepository;
import vn.nextlogix.service.dto.CustomerDTO;
import vn.nextlogix.service.dto.CustomerSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.CustomerLegal;
    import vn.nextlogix.domain.CustomerPayment;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.UserExtraInfo;
    import vn.nextlogix.domain.UserExtraInfo;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.CustomerType;
    import vn.nextlogix.domain.CustomerSource;
import vn.nextlogix.service.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CustomerLegalSearchRepository;
    import vn.nextlogix.service.mapper.CustomerLegalMapper;

    import vn.nextlogix.repository.search.CustomerPaymentSearchRepository;
    import vn.nextlogix.service.mapper.CustomerPaymentMapper;




    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.CustomerTypeSearchRepository;
    import vn.nextlogix.service.mapper.CustomerTypeMapper;

    import vn.nextlogix.repository.search.CustomerSourceSearchRepository;
    import vn.nextlogix.service.mapper.CustomerSourceMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CustomerSearchRepository customerSearchRepository;


        private final CustomerLegalSearchRepository customerLegalSearchRepository;
        private final CustomerLegalMapper customerLegalMapper;

        private final CustomerPaymentSearchRepository customerPaymentSearchRepository;
        private final CustomerPaymentMapper customerPaymentMapper;




        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final CustomerTypeSearchRepository customerTypeSearchRepository;
        private final CustomerTypeMapper customerTypeMapper;

        private final CustomerSourceSearchRepository customerSourceSearchRepository;
        private final CustomerSourceMapper customerSourceMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerSearchRepository customerSearchRepository     ,CustomerLegalSearchRepository customerLegalSearchRepository,CustomerLegalMapper  customerLegalMapper
,CustomerPaymentSearchRepository customerPaymentSearchRepository,CustomerPaymentMapper  customerPaymentMapper
,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,CustomerTypeSearchRepository customerTypeSearchRepository,CustomerTypeMapper  customerTypeMapper
,CustomerSourceSearchRepository customerSourceSearchRepository,CustomerSourceMapper  customerSourceMapper
) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerSearchRepository = customerSearchRepository;
                                    this.customerLegalSearchRepository = customerLegalSearchRepository;
                                     this.customerLegalMapper = customerLegalMapper;
                                    this.customerPaymentSearchRepository = customerPaymentSearchRepository;
                                     this.customerPaymentMapper = customerPaymentMapper;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.customerTypeSearchRepository = customerTypeSearchRepository;
                                     this.customerTypeMapper = customerTypeMapper;
                                    this.customerSourceSearchRepository = customerSourceSearchRepository;
                                     this.customerSourceMapper = customerSourceMapper;

    }

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        CustomerDTO result = customerMapper.toDto(customer);
        customerSearchRepository.save(customer);
        return result;
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable)
            .map(customerMapper::toDto);
    }

    /**
     * Get one customer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        Customer customer = customerRepository.findOne(id);
        return customerMapper.toDto(customer);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.delete(id);
        customerSearchRepository.delete(id);
    }

    /**
     * Search for the customer corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Customers for query {}", query);
        Page<Customer> result = customerSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> searchExample(CustomerSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getAddress())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("address", "*"+searchDto.getAddress()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getEmail())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("email", "*"+searchDto.getEmail()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getPhone())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("phone", "*"+searchDto.getPhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getPassword())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("password", "*"+searchDto.getPassword()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getApiToken())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("apiToken", "*"+searchDto.getApiToken()+"*"));
            }
            if(searchDto.getLegalId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("legal.id", searchDto.getLegalId()));
            }
            if(searchDto.getPaymentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("payment.id", searchDto.getPaymentId()));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getManageUserId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("manageUser.id", searchDto.getManageUserId()));
            }
            if(searchDto.getSaleUserId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("saleUser.id", searchDto.getSaleUserId()));
            }
            if(searchDto.getProvinceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("province.id", searchDto.getProvinceId()));
            }
            if(searchDto.getDistrictId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("district.id", searchDto.getDistrictId()));
            }
            if(searchDto.getCustomerTypeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customerType.id", searchDto.getCustomerTypeId()));
            }
            if(searchDto.getCustomerSourceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customerSource.id", searchDto.getCustomerSourceId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<Customer> customerPage= customerSearchRepository.search(query);
            List<CustomerDTO> customerList =  StreamSupport
            .stream(customerPage.spliterator(), false)
            .map(customerMapper::toDto)
            .collect(Collectors.toList());
            customerList.forEach(customerDto -> {
            if(customerDto.getLegalId()!=null){
                CustomerLegal customerLegal= customerLegalSearchRepository.findOne(customerDto.getLegalId());
                customerDto.setLegalDTO(customerLegalMapper.toDto(customerLegal));
            }
            if(customerDto.getPaymentId()!=null){
                CustomerPayment customerPayment= customerPaymentSearchRepository.findOne(customerDto.getPaymentId());
                customerDto.setPaymentDTO(customerPaymentMapper.toDto(customerPayment));
            }
            if(customerDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerDto.getCompanyId());
                customerDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerDto.getManageUserId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(customerDto.getManageUserId());
                customerDto.setManageUserDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            if(customerDto.getSaleUserId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(customerDto.getSaleUserId());
                customerDto.setSaleUserDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            if(customerDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(customerDto.getProvinceId());
                customerDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(customerDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(customerDto.getDistrictId());
                customerDto.setDistrictDTO(districtMapper.toDto(district));
            }
            if(customerDto.getCustomerTypeId()!=null){
                CustomerType customerType= customerTypeSearchRepository.findOne(customerDto.getCustomerTypeId());
                customerDto.setCustomerTypeDTO(customerTypeMapper.toDto(customerType));
            }
            if(customerDto.getCustomerSourceId()!=null){
                CustomerSource customerSource= customerSourceSearchRepository.findOne(customerDto.getCustomerSourceId());
                customerDto.setCustomerSourceDTO(customerSourceMapper.toDto(customerSource));
            }
            });
            return new PageImpl<>(customerList,pageable,customerPage.getTotalElements());
        }
}
