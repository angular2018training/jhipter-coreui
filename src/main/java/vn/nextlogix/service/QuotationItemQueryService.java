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

import vn.nextlogix.domain.QuotationItem;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationItemRepository;
import vn.nextlogix.repository.search.QuotationItemSearchRepository;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.QuotationItemCriteria;

import vn.nextlogix.service.dto.QuotationItemDTO;
import vn.nextlogix.service.mapper.QuotationItemMapper;

/**
 * Service for executing complex queries for QuotationItem entities in the database.
 * The main input is a {@link QuotationItemCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationItemDTO} or a {@link Page} of {@link QuotationItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationItemQueryService extends QueryService<QuotationItem> {

    private final Logger log = LoggerFactory.getLogger(QuotationItemQueryService.class);


    private final QuotationItemRepository quotationItemRepository;

    private final QuotationItemMapper quotationItemMapper;

    private final QuotationItemSearchRepository quotationItemSearchRepository;


        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;

        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public QuotationItemQueryService(QuotationItemRepository quotationItemRepository, QuotationItemMapper quotationItemMapper, QuotationItemSearchRepository quotationItemSearchRepository     ,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.quotationItemRepository = quotationItemRepository;
        this.quotationItemMapper = quotationItemMapper;
        this.quotationItemSearchRepository = quotationItemSearchRepository;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationItemDTO> findByCriteria(QuotationItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationItem> specification = createSpecification(criteria);
        return quotationItemMapper.toDto(quotationItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationItemDTO> findByCriteria(QuotationItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationItem> specification = createSpecification(criteria);
        final Page<QuotationItem> result = quotationItemRepository.findAll(specification, page);
        return result.map(quotationItemMapper::toDto);
    }

    /**
     * Function to convert QuotationItemCriteria to a {@link Specifications}
     */
    private Specifications<QuotationItem> createSpecification(QuotationItemCriteria criteria) {
        Specifications<QuotationItem> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationItem_.id));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), QuotationItem_.createDate));
            }
            if (criteria.getQuotationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationId(), QuotationItem_.quotation, Quotation_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationItem_.company, Company_.id));
            }
        }
        return specification;
    }

}
