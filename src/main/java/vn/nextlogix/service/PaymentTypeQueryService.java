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

import vn.nextlogix.domain.PaymentType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.PaymentTypeRepository;
import vn.nextlogix.repository.search.PaymentTypeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.PaymentTypeCriteria;

import vn.nextlogix.service.dto.PaymentTypeDTO;
import vn.nextlogix.service.mapper.PaymentTypeMapper;

/**
 * Service for executing complex queries for PaymentType entities in the database.
 * The main input is a {@link PaymentTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaymentTypeDTO} or a {@link Page} of {@link PaymentTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaymentTypeQueryService extends QueryService<PaymentType> {

    private final Logger log = LoggerFactory.getLogger(PaymentTypeQueryService.class);


    private final PaymentTypeRepository paymentTypeRepository;

    private final PaymentTypeMapper paymentTypeMapper;

    private final PaymentTypeSearchRepository paymentTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public PaymentTypeQueryService(PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper, PaymentTypeSearchRepository paymentTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
        this.paymentTypeSearchRepository = paymentTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link PaymentTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentTypeDTO> findByCriteria(PaymentTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<PaymentType> specification = createSpecification(criteria);
        return paymentTypeMapper.toDto(paymentTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaymentTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentTypeDTO> findByCriteria(PaymentTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<PaymentType> specification = createSpecification(criteria);
        final Page<PaymentType> result = paymentTypeRepository.findAll(specification, page);
        return result.map(paymentTypeMapper::toDto);
    }

    /**
     * Function to convert PaymentTypeCriteria to a {@link Specifications}
     */
    private Specifications<PaymentType> createSpecification(PaymentTypeCriteria criteria) {
        Specifications<PaymentType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PaymentType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), PaymentType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PaymentType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PaymentType_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), PaymentType_.company, Company_.id));
            }
        }
        return specification;
    }

}
