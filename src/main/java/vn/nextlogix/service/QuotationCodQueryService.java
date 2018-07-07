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

import vn.nextlogix.domain.QuotationCod;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationCodRepository;
import vn.nextlogix.repository.search.QuotationCodSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationCodCriteria;

import vn.nextlogix.service.dto.QuotationCodDTO;
import vn.nextlogix.service.mapper.QuotationCodMapper;

/**
 * Service for executing complex queries for QuotationCod entities in the database.
 * The main input is a {@link QuotationCodCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationCodDTO} or a {@link Page} of {@link QuotationCodDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationCodQueryService extends QueryService<QuotationCod> {

    private final Logger log = LoggerFactory.getLogger(QuotationCodQueryService.class);


    private final QuotationCodRepository quotationCodRepository;

    private final QuotationCodMapper quotationCodMapper;

    private final QuotationCodSearchRepository quotationCodSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationCodQueryService(QuotationCodRepository quotationCodRepository, QuotationCodMapper quotationCodMapper, QuotationCodSearchRepository quotationCodSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationCodRepository = quotationCodRepository;
        this.quotationCodMapper = quotationCodMapper;
        this.quotationCodSearchRepository = quotationCodSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationCodDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationCodDTO> findByCriteria(QuotationCodCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationCod> specification = createSpecification(criteria);
        return quotationCodMapper.toDto(quotationCodRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationCodDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationCodDTO> findByCriteria(QuotationCodCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationCod> specification = createSpecification(criteria);
        final Page<QuotationCod> result = quotationCodRepository.findAll(specification, page);
        return result.map(quotationCodMapper::toDto);
    }

    /**
     * Function to convert QuotationCodCriteria to a {@link Specifications}
     */
    private Specifications<QuotationCod> createSpecification(QuotationCodCriteria criteria) {
        Specifications<QuotationCod> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationCod_.id));
            }
            if (criteria.getAmountFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountFrom(), QuotationCod_.amountFrom));
            }
            if (criteria.getAmountTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountTo(), QuotationCod_.amountTo));
            }
            if (criteria.getFeeAmountMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmountMin(), QuotationCod_.feeAmountMin));
            }
            if (criteria.getFeeAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeAmount(), QuotationCod_.feeAmount));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationCod_.company, Company_.id));
            }
            if (criteria.getDistrictTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictTypeId(), QuotationCod_.districtType, DistrictType_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationCod_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
