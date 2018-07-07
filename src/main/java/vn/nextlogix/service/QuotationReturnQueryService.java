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

import vn.nextlogix.domain.QuotationReturn;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationReturnRepository;
import vn.nextlogix.repository.search.QuotationReturnSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

    import vn.nextlogix.repository.search.RegionSearchRepository;
    import vn.nextlogix.service.mapper.RegionMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationReturnCriteria;

import vn.nextlogix.service.dto.QuotationReturnDTO;
import vn.nextlogix.service.mapper.QuotationReturnMapper;

/**
 * Service for executing complex queries for QuotationReturn entities in the database.
 * The main input is a {@link QuotationReturnCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationReturnDTO} or a {@link Page} of {@link QuotationReturnDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationReturnQueryService extends QueryService<QuotationReturn> {

    private final Logger log = LoggerFactory.getLogger(QuotationReturnQueryService.class);


    private final QuotationReturnRepository quotationReturnRepository;

    private final QuotationReturnMapper quotationReturnMapper;

    private final QuotationReturnSearchRepository quotationReturnSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final RegionSearchRepository regionSearchRepository;
        private final RegionMapper regionMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationReturnQueryService(QuotationReturnRepository quotationReturnRepository, QuotationReturnMapper quotationReturnMapper, QuotationReturnSearchRepository quotationReturnSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,RegionSearchRepository regionSearchRepository,RegionMapper  regionMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationReturnRepository = quotationReturnRepository;
        this.quotationReturnMapper = quotationReturnMapper;
        this.quotationReturnSearchRepository = quotationReturnSearchRepository;
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
     * Return a {@link List} of {@link QuotationReturnDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationReturnDTO> findByCriteria(QuotationReturnCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationReturn> specification = createSpecification(criteria);
        return quotationReturnMapper.toDto(quotationReturnRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationReturnDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationReturnDTO> findByCriteria(QuotationReturnCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationReturn> specification = createSpecification(criteria);
        final Page<QuotationReturn> result = quotationReturnRepository.findAll(specification, page);
        return result.map(quotationReturnMapper::toDto);
    }

    /**
     * Function to convert QuotationReturnCriteria to a {@link Specifications}
     */
    private Specifications<QuotationReturn> createSpecification(QuotationReturnCriteria criteria) {
        Specifications<QuotationReturn> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationReturn_.id));
            }
            if (criteria.getFeePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeePercent(), QuotationReturn_.feePercent));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationReturn_.company, Company_.id));
            }
            if (criteria.getDistrictTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictTypeId(), QuotationReturn_.districtType, DistrictType_.id));
            }
            if (criteria.getRegionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRegionId(), QuotationReturn_.region, Region_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationReturn_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
