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

import vn.nextlogix.domain.OrderServicesType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderServicesTypeRepository;
import vn.nextlogix.repository.search.OrderServicesTypeSearchRepository;

import vn.nextlogix.service.dto.OrderServicesTypeCriteria;

import vn.nextlogix.service.dto.OrderServicesTypeDTO;
import vn.nextlogix.service.mapper.OrderServicesTypeMapper;

/**
 * Service for executing complex queries for OrderServicesType entities in the database.
 * The main input is a {@link OrderServicesTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderServicesTypeDTO} or a {@link Page} of {@link OrderServicesTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderServicesTypeQueryService extends QueryService<OrderServicesType> {

    private final Logger log = LoggerFactory.getLogger(OrderServicesTypeQueryService.class);


    private final OrderServicesTypeRepository orderServicesTypeRepository;

    private final OrderServicesTypeMapper orderServicesTypeMapper;

    private final OrderServicesTypeSearchRepository orderServicesTypeSearchRepository;



    public OrderServicesTypeQueryService(OrderServicesTypeRepository orderServicesTypeRepository, OrderServicesTypeMapper orderServicesTypeMapper, OrderServicesTypeSearchRepository orderServicesTypeSearchRepository     ) {
        this.orderServicesTypeRepository = orderServicesTypeRepository;
        this.orderServicesTypeMapper = orderServicesTypeMapper;
        this.orderServicesTypeSearchRepository = orderServicesTypeSearchRepository;

    }

    /**
     * Return a {@link List} of {@link OrderServicesTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderServicesTypeDTO> findByCriteria(OrderServicesTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderServicesType> specification = createSpecification(criteria);
        return orderServicesTypeMapper.toDto(orderServicesTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderServicesTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderServicesTypeDTO> findByCriteria(OrderServicesTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderServicesType> specification = createSpecification(criteria);
        final Page<OrderServicesType> result = orderServicesTypeRepository.findAll(specification, page);
        return result.map(orderServicesTypeMapper::toDto);
    }

    /**
     * Function to convert OrderServicesTypeCriteria to a {@link Specifications}
     */
    private Specifications<OrderServicesType> createSpecification(OrderServicesTypeCriteria criteria) {
        Specifications<OrderServicesType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderServicesType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrderServicesType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrderServicesType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OrderServicesType_.description));
            }
        }
        return specification;
    }

}
