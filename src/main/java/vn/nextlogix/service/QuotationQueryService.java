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

import vn.nextlogix.domain.Quotation;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationRepository;
import vn.nextlogix.repository.search.QuotationSearchRepository;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.QuotationCriteria;

import vn.nextlogix.service.dto.QuotationDTO;
import vn.nextlogix.service.mapper.QuotationMapper;

/**
 * Service for executing complex queries for Quotation entities in the database.
 * The main input is a {@link QuotationCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationDTO} or a {@link Page} of {@link QuotationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationQueryService extends QueryService<Quotation> {

    private final Logger log = LoggerFactory.getLogger(QuotationQueryService.class);


    private final QuotationRepository quotationRepository;

    private final QuotationMapper quotationMapper;

    private final QuotationSearchRepository quotationSearchRepository;



        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public QuotationQueryService(QuotationRepository quotationRepository, QuotationMapper quotationMapper, QuotationSearchRepository quotationSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
        this.quotationSearchRepository = quotationSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationDTO> findByCriteria(QuotationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Quotation> specification = createSpecification(criteria);
        return quotationMapper.toDto(quotationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationDTO> findByCriteria(QuotationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Quotation> specification = createSpecification(criteria);
        final Page<Quotation> result = quotationRepository.findAll(specification, page);
        return result.map(quotationMapper::toDto);
    }

    /**
     * Function to convert QuotationCriteria to a {@link Specifications}
     */
    private Specifications<Quotation> createSpecification(QuotationCriteria criteria) {
        Specifications<Quotation> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Quotation_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Quotation_.name));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Quotation_.isActive));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Quotation_.createDate));
            }
            if (criteria.getQuotationItemId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationItemId(), Quotation_.quotationItems, QuotationItem_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Quotation_.company, Company_.id));
            }
        }
        return specification;
    }

}
