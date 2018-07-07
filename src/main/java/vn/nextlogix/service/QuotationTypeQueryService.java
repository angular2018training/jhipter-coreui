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

import vn.nextlogix.domain.QuotationType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.QuotationTypeRepository;
import vn.nextlogix.repository.search.QuotationTypeSearchRepository;

import vn.nextlogix.service.dto.QuotationTypeCriteria;

import vn.nextlogix.service.dto.QuotationTypeDTO;
import vn.nextlogix.service.mapper.QuotationTypeMapper;

/**
 * Service for executing complex queries for QuotationType entities in the database.
 * The main input is a {@link QuotationTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuotationTypeDTO} or a {@link Page} of {@link QuotationTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuotationTypeQueryService extends QueryService<QuotationType> {

    private final Logger log = LoggerFactory.getLogger(QuotationTypeQueryService.class);


    private final QuotationTypeRepository quotationTypeRepository;

    private final QuotationTypeMapper quotationTypeMapper;

    private final QuotationTypeSearchRepository quotationTypeSearchRepository;



    public QuotationTypeQueryService(QuotationTypeRepository quotationTypeRepository, QuotationTypeMapper quotationTypeMapper, QuotationTypeSearchRepository quotationTypeSearchRepository     ) {
        this.quotationTypeRepository = quotationTypeRepository;
        this.quotationTypeMapper = quotationTypeMapper;
        this.quotationTypeSearchRepository = quotationTypeSearchRepository;

    }

    /**
     * Return a {@link List} of {@link QuotationTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuotationTypeDTO> findByCriteria(QuotationTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<QuotationType> specification = createSpecification(criteria);
        return quotationTypeMapper.toDto(quotationTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuotationTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuotationTypeDTO> findByCriteria(QuotationTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<QuotationType> specification = createSpecification(criteria);
        final Page<QuotationType> result = quotationTypeRepository.findAll(specification, page);
        return result.map(quotationTypeMapper::toDto);
    }

    /**
     * Function to convert QuotationTypeCriteria to a {@link Specifications}
     */
    private Specifications<QuotationType> createSpecification(QuotationTypeCriteria criteria) {
        Specifications<QuotationType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), QuotationType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), QuotationType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), QuotationType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), QuotationType_.description));
            }
        }
        return specification;
    }

}
