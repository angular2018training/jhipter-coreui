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

import vn.nextlogix.domain.QuotationItemType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationItemTypeRepository;
import vn.nextlogix.repository.search.QuotationItemTypeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.QuotationItemTypeCriteria;

import vn.nextlogix.service.dto.QuotationItemTypeDTO;
import vn.nextlogix.service.mapper.QuotationItemTypeMapper;

/**
 * Service for executing complex queries for QuotationItemType entities in the database.
 * The main input is a {@link QuotationItemTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationItemTypeDTO} or a {@link Page} of {@link QuotationItemTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationItemTypeQueryService extends QueryService<QuotationItemType> {

    private final Logger log = LoggerFactory.getLogger(QuotationItemTypeQueryService.class);


    private final QuotationItemTypeRepository quotationItemTypeRepository;

    private final QuotationItemTypeMapper quotationItemTypeMapper;

    private final QuotationItemTypeSearchRepository quotationItemTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public QuotationItemTypeQueryService(QuotationItemTypeRepository quotationItemTypeRepository, QuotationItemTypeMapper quotationItemTypeMapper, QuotationItemTypeSearchRepository quotationItemTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.quotationItemTypeRepository = quotationItemTypeRepository;
        this.quotationItemTypeMapper = quotationItemTypeMapper;
        this.quotationItemTypeSearchRepository = quotationItemTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link QuotationItemTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationItemTypeDTO> findByCriteria(QuotationItemTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationItemType> specification = createSpecification(criteria);
        return quotationItemTypeMapper.toDto(quotationItemTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationItemTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationItemTypeDTO> findByCriteria(QuotationItemTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationItemType> specification = createSpecification(criteria);
        final Page<QuotationItemType> result = quotationItemTypeRepository.findAll(specification, page);
        return result.map(quotationItemTypeMapper::toDto);
    }

    /**
     * Function to convert QuotationItemTypeCriteria to a {@link Specifications}
     */
    private Specifications<QuotationItemType> createSpecification(QuotationItemTypeCriteria criteria) {
        Specifications<QuotationItemType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationItemType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), QuotationItemType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), QuotationItemType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), QuotationItemType_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), QuotationItemType_.company, Company_.id));
            }
        }
        return specification;
    }

}
