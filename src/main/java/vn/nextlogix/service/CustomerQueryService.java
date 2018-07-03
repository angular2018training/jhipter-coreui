package vn.nextlogix.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import vn.nextlogix.domain.Customer;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerRepository;
import vn.nextlogix.repository.search.CustomerSearchRepository;

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

import vn.nextlogix.service.dto.CustomerCriteria;

import vn.nextlogix.service.dto.CustomerDTO;
import vn.nextlogix.service.mapper.CustomerMapper;

/**
 * Service for executing complex queries for Customer entities in the database.
 * The main input is a {@link CustomerCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerDTO} or a {@link Page} of {@link CustomerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerQueryService extends QueryService<Customer> {

    private final Logger log = LoggerFactory.getLogger(CustomerQueryService.class);


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


    public CustomerQueryService(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerSearchRepository customerSearchRepository     ,CustomerLegalSearchRepository customerLegalSearchRepository,CustomerLegalMapper  customerLegalMapper
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
     * Return a {@link List} of {@link CustomerDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerDTO> findByCriteria(CustomerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Customer> specification = createSpecification(criteria);
        return customerMapper.toDto(customerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findByCriteria(CustomerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Customer> specification = createSpecification(criteria);
        final Page<Customer> result = customerRepository.findAll(specification, page);
        return result.map(customerMapper::toDto);
    }

    /**
     * Function to convert CustomerCriteria to a {@link Specifications}
     */
    private Specifications<Customer> createSpecification(CustomerCriteria criteria) {
        Specifications<Customer> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Customer_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Customer_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Customer_.name));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Customer_.address));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Customer_.email));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), Customer_.phone));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), Customer_.password));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Customer_.isActive));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Customer_.createDate));
            }
            if (criteria.getLastLoginDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastLoginDate(), Customer_.lastLoginDate));
            }
            if (criteria.getApiToken() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApiToken(), Customer_.apiToken));
            }
            if (criteria.getLegalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getLegalId(), Customer_.legal, CustomerLegal_.id));
            }
            if (criteria.getPaymentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPaymentId(), Customer_.payment, CustomerPayment_.id));
            }
            if (criteria.getCustomerPostOfficeDetailListId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerPostOfficeDetailListId(), Customer_.customerPostOfficeDetailLists, CustomerPostOffice_.id));
            }
            if (criteria.getCustomerWarehouseDetailListId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerWarehouseDetailListId(), Customer_.customerWarehouseDetailLists, CustomerWarehouse_.id));
            }
            if (criteria.getCustomerServicesDetailListId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerServicesDetailListId(), Customer_.customerServicesDetailLists, CustomerServices_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Customer_.company, Company_.id));
            }
            if (criteria.getManageUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getManageUserId(), Customer_.manageUser, UserExtraInfo_.id));
            }
            if (criteria.getSaleUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSaleUserId(), Customer_.saleUser, UserExtraInfo_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), Customer_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), Customer_.district, District_.id));
            }
            if (criteria.getCustomerTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerTypeId(), Customer_.customerType, CustomerType_.id));
            }
            if (criteria.getCustomerSourceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerSourceId(), Customer_.customerSource, CustomerSource_.id));
            }
        }
        return specification;
    }

}
