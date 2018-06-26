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

import vn.nextlogix.domain.CustomerType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerTypeRepository;
import vn.nextlogix.repository.search.CustomerTypeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.CustomerTypeCriteria;

import vn.nextlogix.service.dto.CustomerTypeDTO;
import vn.nextlogix.service.mapper.CustomerTypeMapper;

/**
 * Service for executing complex queries for CustomerType entities in the database.
 * The main input is a {@link CustomerTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerTypeDTO} or a {@link Page} of {@link CustomerTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerTypeQueryService extends QueryService<CustomerType> {

    private final Logger log = LoggerFactory.getLogger(CustomerTypeQueryService.class);


    private final CustomerTypeRepository customerTypeRepository;

    private final CustomerTypeMapper customerTypeMapper;

    private final CustomerTypeSearchRepository customerTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public CustomerTypeQueryService(CustomerTypeRepository customerTypeRepository, CustomerTypeMapper customerTypeMapper, CustomerTypeSearchRepository customerTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.customerTypeRepository = customerTypeRepository;
        this.customerTypeMapper = customerTypeMapper;
        this.customerTypeSearchRepository = customerTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerTypeDTO> findByCriteria(CustomerTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerType> specification = createSpecification(criteria);
        return customerTypeMapper.toDto(customerTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerTypeDTO> findByCriteria(CustomerTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerType> specification = createSpecification(criteria);
        final Page<CustomerType> result = customerTypeRepository.findAll(specification, page);
        return result.map(customerTypeMapper::toDto);
    }

    /**
     * Function to convert CustomerTypeCriteria to a {@link Specifications}
     */
    private Specifications<CustomerType> createSpecification(CustomerTypeCriteria criteria) {
        Specifications<CustomerType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CustomerType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), CustomerType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CustomerType_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerType_.company, Company_.id));
            }
        }
        return specification;
    }

}
