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

import vn.nextlogix.domain.QuotationDomesticDelivery;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationDomesticDeliveryRepository;
import vn.nextlogix.repository.search.QuotationDomesticDeliverySearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

    import vn.nextlogix.repository.search.RegionSearchRepository;
    import vn.nextlogix.service.mapper.RegionMapper;

    import vn.nextlogix.repository.search.WeightSearchRepository;
    import vn.nextlogix.service.mapper.WeightMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.QuotationDomesticDeliveryCriteria;

import vn.nextlogix.service.dto.QuotationDomesticDeliveryDTO;
import vn.nextlogix.service.mapper.QuotationDomesticDeliveryMapper;

/**
 * Service for executing complex queries for QuotationDomesticDelivery entities in the database.
 * The main input is a {@link QuotationDomesticDeliveryCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationDomesticDeliveryDTO} or a {@link Page} of {@link QuotationDomesticDeliveryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationDomesticDeliveryQueryService extends QueryService<QuotationDomesticDelivery> {

    private final Logger log = LoggerFactory.getLogger(QuotationDomesticDeliveryQueryService.class);


    private final QuotationDomesticDeliveryRepository quotationDomesticDeliveryRepository;

    private final QuotationDomesticDeliveryMapper quotationDomesticDeliveryMapper;

    private final QuotationDomesticDeliverySearchRepository quotationDomesticDeliverySearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final RegionSearchRepository regionSearchRepository;
        private final RegionMapper regionMapper;

        private final WeightSearchRepository weightSearchRepository;
        private final WeightMapper weightMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationDomesticDeliveryQueryService(QuotationDomesticDeliveryRepository quotationDomesticDeliveryRepository, QuotationDomesticDeliveryMapper quotationDomesticDeliveryMapper, QuotationDomesticDeliverySearchRepository quotationDomesticDeliverySearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,RegionSearchRepository regionSearchRepository,RegionMapper  regionMapper
,WeightSearchRepository weightSearchRepository,WeightMapper  weightMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationDomesticDeliveryRepository = quotationDomesticDeliveryRepository;
        this.quotationDomesticDeliveryMapper = quotationDomesticDeliveryMapper;
        this.quotationDomesticDeliverySearchRepository = quotationDomesticDeliverySearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.regionSearchRepository = regionSearchRepository;
                                     this.regionMapper = regionMapper;
                                    this.weightSearchRepository = weightSearchRepository;
                                     this.weightMapper = weightMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationDomesticDeliveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationDomesticDeliveryDTO> findByCriteria(QuotationDomesticDeliveryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationDomesticDelivery> specification = createSpecification(criteria);
        return quotationDomesticDeliveryMapper.toDto(quotationDomesticDeliveryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationDomesticDeliveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationDomesticDeliveryDTO> findByCriteria(QuotationDomesticDeliveryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationDomesticDelivery> specification = createSpecification(criteria);
        final Page<QuotationDomesticDelivery> result = quotationDomesticDeliveryRepository.findAll(specification, page);
        return result.map(quotationDomesticDeliveryMapper::toDto);
    }

    /**
     * Function to convert QuotationDomesticDeliveryCriteria to a {@link Specifications}
     */
    private Specifications<QuotationDomesticDelivery> createSpecification(QuotationDomesticDeliveryCriteria criteria) {
        Specifications<QuotationDomesticDelivery> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationDomesticDelivery_.id));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), QuotationDomesticDelivery_.amount));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationDomesticDelivery_.company, Company_.id));
            }
            if (criteria.getDistrictTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictTypeId(), QuotationDomesticDelivery_.districtType, DistrictType_.id));
            }
            if (criteria.getRegionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRegionId(), QuotationDomesticDelivery_.region, Region_.id));
            }
            if (criteria.getWeightId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getWeightId(), QuotationDomesticDelivery_.weight, Weight_.id));
            }
            if (criteria.getQuotationParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationParentId(), QuotationDomesticDelivery_.quotationParent, Quotation_.id));
            }
        }
        return specification;
    }

}
