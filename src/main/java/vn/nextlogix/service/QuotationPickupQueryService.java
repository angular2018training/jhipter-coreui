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

import vn.nextlogix.domain.QuotationPickup;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationPickupRepository;
import vn.nextlogix.repository.search.QuotationPickupSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationPickupCriteria;

import vn.nextlogix.service.dto.QuotationPickupDTO;
import vn.nextlogix.service.mapper.QuotationPickupMapper;

/**
 * Service for executing complex queries for QuotationPickup entities in the database.
 * The main input is a {@link QuotationPickupCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationPickupDTO} or a {@link Page} of {@link QuotationPickupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationPickupQueryService extends QueryService<QuotationPickup> {

    private final Logger log = LoggerFactory.getLogger(QuotationPickupQueryService.class);


    private final QuotationPickupRepository quotationPickupRepository;

    private final QuotationPickupMapper quotationPickupMapper;

    private final QuotationPickupSearchRepository quotationPickupSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationPickupQueryService(QuotationPickupRepository quotationPickupRepository, QuotationPickupMapper quotationPickupMapper, QuotationPickupSearchRepository quotationPickupSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationPickupRepository = quotationPickupRepository;
        this.quotationPickupMapper = quotationPickupMapper;
        this.quotationPickupSearchRepository = quotationPickupSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationPickupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationPickupDTO> findByCriteria(QuotationPickupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationPickup> specification = createSpecification(criteria);
        return quotationPickupMapper.toDto(quotationPickupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationPickupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationPickupDTO> findByCriteria(QuotationPickupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationPickup> specification = createSpecification(criteria);
        final Page<QuotationPickup> result = quotationPickupRepository.findAll(specification, page);
        return result.map(quotationPickupMapper::toDto);
    }

    /**
     * Function to convert QuotationPickupCriteria to a {@link Specifications}
     */
    private Specifications<QuotationPickup> createSpecification(QuotationPickupCriteria criteria) {
        Specifications<QuotationPickup> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationPickup_.id));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), QuotationPickup_.amount));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationPickup_.company, Company_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), QuotationPickup_.province, Province_.id));
            }
            if (criteria.getDistrictTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictTypeId(), QuotationPickup_.districtType, DistrictType_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationPickup_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
