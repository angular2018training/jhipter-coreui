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

import vn.nextlogix.domain.QuotationInsurance;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationInsuranceRepository;
import vn.nextlogix.repository.search.QuotationInsuranceSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationInsuranceCriteria;

import vn.nextlogix.service.dto.QuotationInsuranceDTO;
import vn.nextlogix.service.mapper.QuotationInsuranceMapper;

/**
 * Service for executing complex queries for QuotationInsurance entities in the database.
 * The main input is a {@link QuotationInsuranceCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationInsuranceDTO} or a {@link Page} of {@link QuotationInsuranceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationInsuranceQueryService extends QueryService<QuotationInsurance> {

    private final Logger log = LoggerFactory.getLogger(QuotationInsuranceQueryService.class);


    private final QuotationInsuranceRepository quotationInsuranceRepository;

    private final QuotationInsuranceMapper quotationInsuranceMapper;

    private final QuotationInsuranceSearchRepository quotationInsuranceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationInsuranceQueryService(QuotationInsuranceRepository quotationInsuranceRepository, QuotationInsuranceMapper quotationInsuranceMapper, QuotationInsuranceSearchRepository quotationInsuranceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationInsuranceRepository = quotationInsuranceRepository;
        this.quotationInsuranceMapper = quotationInsuranceMapper;
        this.quotationInsuranceSearchRepository = quotationInsuranceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationInsuranceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationInsuranceDTO> findByCriteria(QuotationInsuranceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationInsurance> specification = createSpecification(criteria);
        return quotationInsuranceMapper.toDto(quotationInsuranceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationInsuranceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationInsuranceDTO> findByCriteria(QuotationInsuranceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationInsurance> specification = createSpecification(criteria);
        final Page<QuotationInsurance> result = quotationInsuranceRepository.findAll(specification, page);
        return result.map(quotationInsuranceMapper::toDto);
    }

    /**
     * Function to convert QuotationInsuranceCriteria to a {@link Specifications}
     */
    private Specifications<QuotationInsurance> createSpecification(QuotationInsuranceCriteria criteria) {
        Specifications<QuotationInsurance> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationInsurance_.id));
            }
            if (criteria.getAmountFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountFrom(), QuotationInsurance_.amountFrom));
            }
            if (criteria.getAmountTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountTo(), QuotationInsurance_.amountTo));
            }
            if (criteria.getFeeAmountMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmountMin(), QuotationInsurance_.feeAmountMin));
            }
            if (criteria.getFeeAmountMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmountMax(), QuotationInsurance_.feeAmountMax));
            }
            if (criteria.getFeePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeePercent(), QuotationInsurance_.feePercent));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationInsurance_.company, Company_.id));
            }
            if (criteria.getDistrictTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictTypeId(), QuotationInsurance_.districtType, DistrictType_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationInsurance_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
