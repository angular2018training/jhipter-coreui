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

import vn.nextlogix.domain.CustomerSource;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerSourceRepository;
import vn.nextlogix.repository.search.CustomerSourceSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.CustomerSourceCriteria;

import vn.nextlogix.service.dto.CustomerSourceDTO;
import vn.nextlogix.service.mapper.CustomerSourceMapper;

/**
 * Service for executing complex queries for CustomerSource entities in the database.
 * The main input is a {@link CustomerSourceCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerSourceDTO} or a {@link Page} of {@link CustomerSourceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerSourceQueryService extends QueryService<CustomerSource> {

    private final Logger log = LoggerFactory.getLogger(CustomerSourceQueryService.class);


    private final CustomerSourceRepository customerSourceRepository;

    private final CustomerSourceMapper customerSourceMapper;

    private final CustomerSourceSearchRepository customerSourceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public CustomerSourceQueryService(CustomerSourceRepository customerSourceRepository, CustomerSourceMapper customerSourceMapper, CustomerSourceSearchRepository customerSourceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.customerSourceRepository = customerSourceRepository;
        this.customerSourceMapper = customerSourceMapper;
        this.customerSourceSearchRepository = customerSourceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerSourceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerSourceDTO> findByCriteria(CustomerSourceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerSource> specification = createSpecification(criteria);
        return customerSourceMapper.toDto(customerSourceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerSourceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerSourceDTO> findByCriteria(CustomerSourceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerSource> specification = createSpecification(criteria);
        final Page<CustomerSource> result = customerSourceRepository.findAll(specification, page);
        return result.map(customerSourceMapper::toDto);
    }

    /**
     * Function to convert CustomerSourceCriteria to a {@link Specifications}
     */
    private Specifications<CustomerSource> createSpecification(CustomerSourceCriteria criteria) {
        Specifications<CustomerSource> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerSource_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CustomerSource_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), CustomerSource_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CustomerSource_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerSource_.company, Company_.id));
            }
        }
        return specification;
    }

}
