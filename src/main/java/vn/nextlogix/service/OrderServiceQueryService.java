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

import vn.nextlogix.domain.OrderService;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderServiceRepository;
import vn.nextlogix.repository.search.OrderServiceSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.OrderServiceCriteria;

import vn.nextlogix.service.dto.OrderServiceDTO;
import vn.nextlogix.service.mapper.OrderServiceMapper;

/**
 * Service for executing complex queries for OrderService entities in the database.
 * The main input is a {@link OrderServiceCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderServiceDTO} or a {@link Page} of {@link OrderServiceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceQueryService extends QueryService<OrderService> {

    private final Logger log = LoggerFactory.getLogger(OrderServiceQueryService.class);


    private final OrderServiceRepository orderServiceRepository;

    private final OrderServiceMapper orderServiceMapper;

    private final OrderServiceSearchRepository orderServiceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderServiceQueryService(OrderServiceRepository orderServiceRepository, OrderServiceMapper orderServiceMapper, OrderServiceSearchRepository orderServiceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderServiceRepository = orderServiceRepository;
        this.orderServiceMapper = orderServiceMapper;
        this.orderServiceSearchRepository = orderServiceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link OrderServiceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderServiceDTO> findByCriteria(OrderServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderService> specification = createSpecification(criteria);
        return orderServiceMapper.toDto(orderServiceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderServiceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderServiceDTO> findByCriteria(OrderServiceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderService> specification = createSpecification(criteria);
        final Page<OrderService> result = orderServiceRepository.findAll(specification, page);
        return result.map(orderServiceMapper::toDto);
    }

    /**
     * Function to convert OrderServiceCriteria to a {@link Specifications}
     */
    private Specifications<OrderService> createSpecification(OrderServiceCriteria criteria) {
        Specifications<OrderService> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderService_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrderService_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrderService_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OrderService_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderService_.company, Company_.id));
            }
        }
        return specification;
    }

}
