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

import vn.nextlogix.domain.OrderUserRouteType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderUserRouteTypeRepository;
import vn.nextlogix.repository.search.OrderUserRouteTypeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.OrderUserRouteTypeCriteria;

import vn.nextlogix.service.dto.OrderUserRouteTypeDTO;
import vn.nextlogix.service.mapper.OrderUserRouteTypeMapper;

/**
 * Service for executing complex queries for OrderUserRouteType entities in the database.
 * The main input is a {@link OrderUserRouteTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderUserRouteTypeDTO} or a {@link Page} of {@link OrderUserRouteTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderUserRouteTypeQueryService extends QueryService<OrderUserRouteType> {

    private final Logger log = LoggerFactory.getLogger(OrderUserRouteTypeQueryService.class);


    private final OrderUserRouteTypeRepository orderUserRouteTypeRepository;

    private final OrderUserRouteTypeMapper orderUserRouteTypeMapper;

    private final OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderUserRouteTypeQueryService(OrderUserRouteTypeRepository orderUserRouteTypeRepository, OrderUserRouteTypeMapper orderUserRouteTypeMapper, OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderUserRouteTypeRepository = orderUserRouteTypeRepository;
        this.orderUserRouteTypeMapper = orderUserRouteTypeMapper;
        this.orderUserRouteTypeSearchRepository = orderUserRouteTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link OrderUserRouteTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderUserRouteTypeDTO> findByCriteria(OrderUserRouteTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderUserRouteType> specification = createSpecification(criteria);
        return orderUserRouteTypeMapper.toDto(orderUserRouteTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderUserRouteTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderUserRouteTypeDTO> findByCriteria(OrderUserRouteTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderUserRouteType> specification = createSpecification(criteria);
        final Page<OrderUserRouteType> result = orderUserRouteTypeRepository.findAll(specification, page);
        return result.map(orderUserRouteTypeMapper::toDto);
    }

    /**
     * Function to convert OrderUserRouteTypeCriteria to a {@link Specifications}
     */
    private Specifications<OrderUserRouteType> createSpecification(OrderUserRouteTypeCriteria criteria) {
        Specifications<OrderUserRouteType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderUserRouteType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrderUserRouteType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrderUserRouteType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OrderUserRouteType_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderUserRouteType_.company, Company_.id));
            }
        }
        return specification;
    }

}
