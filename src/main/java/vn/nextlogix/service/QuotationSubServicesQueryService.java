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

import vn.nextlogix.domain.QuotationSubServices;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationSubServicesRepository;
import vn.nextlogix.repository.search.QuotationSubServicesSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.OrderSubServicesTypeSearchRepository;
    import vn.nextlogix.service.mapper.OrderSubServicesTypeMapper;

    import vn.nextlogix.repository.search.OrderSubServicesSearchRepository;
    import vn.nextlogix.service.mapper.OrderSubServicesMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationSubServicesCriteria;

import vn.nextlogix.service.dto.QuotationSubServicesDTO;
import vn.nextlogix.service.mapper.QuotationSubServicesMapper;

/**
 * Service for executing complex queries for QuotationSubServices entities in the database.
 * The main input is a {@link QuotationSubServicesCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationSubServicesDTO} or a {@link Page} of {@link QuotationSubServicesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationSubServicesQueryService extends QueryService<QuotationSubServices> {

    private final Logger log = LoggerFactory.getLogger(QuotationSubServicesQueryService.class);


    private final QuotationSubServicesRepository quotationSubServicesRepository;

    private final QuotationSubServicesMapper quotationSubServicesMapper;

    private final QuotationSubServicesSearchRepository quotationSubServicesSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository;
        private final OrderSubServicesTypeMapper orderSubServicesTypeMapper;

        private final OrderSubServicesSearchRepository orderSubServicesSearchRepository;
        private final OrderSubServicesMapper orderSubServicesMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationSubServicesQueryService(QuotationSubServicesRepository quotationSubServicesRepository, QuotationSubServicesMapper quotationSubServicesMapper, QuotationSubServicesSearchRepository quotationSubServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository,OrderSubServicesTypeMapper  orderSubServicesTypeMapper
,OrderSubServicesSearchRepository orderSubServicesSearchRepository,OrderSubServicesMapper  orderSubServicesMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationSubServicesRepository = quotationSubServicesRepository;
        this.quotationSubServicesMapper = quotationSubServicesMapper;
        this.quotationSubServicesSearchRepository = quotationSubServicesSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.orderSubServicesTypeSearchRepository = orderSubServicesTypeSearchRepository;
                                     this.orderSubServicesTypeMapper = orderSubServicesTypeMapper;
                                    this.orderSubServicesSearchRepository = orderSubServicesSearchRepository;
                                     this.orderSubServicesMapper = orderSubServicesMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationSubServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationSubServicesDTO> findByCriteria(QuotationSubServicesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationSubServices> specification = createSpecification(criteria);
        return quotationSubServicesMapper.toDto(quotationSubServicesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationSubServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationSubServicesDTO> findByCriteria(QuotationSubServicesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationSubServices> specification = createSpecification(criteria);
        final Page<QuotationSubServices> result = quotationSubServicesRepository.findAll(specification, page);
        return result.map(quotationSubServicesMapper::toDto);
    }

    /**
     * Function to convert QuotationSubServicesCriteria to a {@link Specifications}
     */
    private Specifications<QuotationSubServices> createSpecification(QuotationSubServicesCriteria criteria) {
        Specifications<QuotationSubServices> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationSubServices_.id));
            }
            if (criteria.getAmountFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountFrom(), QuotationSubServices_.amountFrom));
            }
            if (criteria.getAmountTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountTo(), QuotationSubServices_.amountTo));
            }
            if (criteria.getFeeAmountMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmountMin(), QuotationSubServices_.feeAmountMin));
            }
            if (criteria.getFeeAmountMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmountMax(), QuotationSubServices_.feeAmountMax));
            }
            if (criteria.getFeeAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmount(), QuotationSubServices_.feeAmount));
            }
            if (criteria.getAutoSelect() != null) {
                specification = specification.and(buildSpecification(criteria.getAutoSelect(), QuotationSubServices_.autoSelect));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationSubServices_.company, Company_.id));
            }
            if (criteria.getOrderSubServicesTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderSubServicesTypeId(), QuotationSubServices_.orderSubServicesType, OrderSubServicesType_.id));
            }
            if (criteria.getOrderSubServicesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderSubServicesId(), QuotationSubServices_.orderSubServices, OrderSubServices_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationSubServices_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
