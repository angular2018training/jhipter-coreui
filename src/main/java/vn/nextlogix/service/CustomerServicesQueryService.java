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

import vn.nextlogix.domain.CustomerServices;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerServicesRepository;
import vn.nextlogix.repository.search.CustomerServicesSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.OrderServicesSearchRepository;
    import vn.nextlogix.service.mapper.OrderServicesMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;

import vn.nextlogix.service.dto.CustomerServicesCriteria;

import vn.nextlogix.service.dto.CustomerServicesDTO;
import vn.nextlogix.service.mapper.CustomerServicesMapper;

/**
 * Service for executing complex queries for CustomerServices entities in the database.
 * The main input is a {@link CustomerServicesCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerServicesDTO} or a {@link Page} of {@link CustomerServicesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerServicesQueryService extends QueryService<CustomerServices> {

    private final Logger log = LoggerFactory.getLogger(CustomerServicesQueryService.class);


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


    public CustomerServicesQueryService(CustomerServicesRepository customerServicesRepository, CustomerServicesMapper customerServicesMapper, CustomerServicesSearchRepository customerServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
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
     * Return a {@link List} of {@link CustomerServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerServicesDTO> findByCriteria(CustomerServicesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerServices> specification = createSpecification(criteria);
        return customerServicesMapper.toDto(customerServicesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerServicesDTO> findByCriteria(CustomerServicesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerServices> specification = createSpecification(criteria);
        final Page<CustomerServices> result = customerServicesRepository.findAll(specification, page);
        return result.map(customerServicesMapper::toDto);
    }

    /**
     * Function to convert CustomerServicesCriteria to a {@link Specifications}
     */
    private Specifications<CustomerServices> createSpecification(CustomerServicesCriteria criteria) {
        Specifications<CustomerServices> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerServices_.id));
            }
            if (criteria.getDiscountPercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscountPercent(), CustomerServices_.discountPercent));
            }
            if (criteria.getIncreasePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIncreasePercent(), CustomerServices_.increasePercent));
            }
            if (criteria.getDecreasePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDecreasePercent(), CustomerServices_.decreasePercent));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerServices_.company, Company_.id));
            }
            if (criteria.getOrderServicesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderServicesId(), CustomerServices_.orderServices, OrderServices_.id));
            }
            if (criteria.getQuotationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationId(), CustomerServices_.quotation, Quotation_.id));
            }
            if (criteria.getCustomerParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerParentId(), CustomerServices_.customerParent, Customer_.id));
            }
        }
        return specification;
    }

}
