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

import vn.nextlogix.domain.QuotationGiveBack;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationGiveBackRepository;
import vn.nextlogix.repository.search.QuotationGiveBackSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

    import vn.nextlogix.repository.search.RegionSearchRepository;
    import vn.nextlogix.service.mapper.RegionMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationGiveBackCriteria;

import vn.nextlogix.service.dto.QuotationGiveBackDTO;
import vn.nextlogix.service.mapper.QuotationGiveBackMapper;

/**
 * Service for executing complex queries for QuotationGiveBack entities in the database.
 * The main input is a {@link QuotationGiveBackCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationGiveBackDTO} or a {@link Page} of {@link QuotationGiveBackDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationGiveBackQueryService extends QueryService<QuotationGiveBack> {

    private final Logger log = LoggerFactory.getLogger(QuotationGiveBackQueryService.class);


    private final QuotationGiveBackRepository quotationGiveBackRepository;

    private final QuotationGiveBackMapper quotationGiveBackMapper;

    private final QuotationGiveBackSearchRepository quotationGiveBackSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final RegionSearchRepository regionSearchRepository;
        private final RegionMapper regionMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationGiveBackQueryService(QuotationGiveBackRepository quotationGiveBackRepository, QuotationGiveBackMapper quotationGiveBackMapper, QuotationGiveBackSearchRepository quotationGiveBackSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,RegionSearchRepository regionSearchRepository,RegionMapper  regionMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationGiveBackRepository = quotationGiveBackRepository;
        this.quotationGiveBackMapper = quotationGiveBackMapper;
        this.quotationGiveBackSearchRepository = quotationGiveBackSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.regionSearchRepository = regionSearchRepository;
                                     this.regionMapper = regionMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationGiveBackDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationGiveBackDTO> findByCriteria(QuotationGiveBackCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationGiveBack> specification = createSpecification(criteria);
        return quotationGiveBackMapper.toDto(quotationGiveBackRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationGiveBackDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationGiveBackDTO> findByCriteria(QuotationGiveBackCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationGiveBack> specification = createSpecification(criteria);
        final Page<QuotationGiveBack> result = quotationGiveBackRepository.findAll(specification, page);
        return result.map(quotationGiveBackMapper::toDto);
    }

    /**
     * Function to convert QuotationGiveBackCriteria to a {@link Specifications}
     */
    private Specifications<QuotationGiveBack> createSpecification(QuotationGiveBackCriteria criteria) {
        Specifications<QuotationGiveBack> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationGiveBack_.id));
            }
            if (criteria.getFeePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeePercent(), QuotationGiveBack_.feePercent));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationGiveBack_.company, Company_.id));
            }
            if (criteria.getDistrictTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictTypeId(), QuotationGiveBack_.districtType, DistrictType_.id));
            }
            if (criteria.getRegionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRegionId(), QuotationGiveBack_.region, Region_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationGiveBack_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
