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

import vn.nextlogix.domain.OrderSubServicesType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderSubServicesTypeRepository;
import vn.nextlogix.repository.search.OrderSubServicesTypeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.OrderSubServicesTypeCriteria;

import vn.nextlogix.service.dto.OrderSubServicesTypeDTO;
import vn.nextlogix.service.mapper.OrderSubServicesTypeMapper;

/**
 * Service for executing complex queries for OrderSubServicesType entities in the database.
 * The main input is a {@link OrderSubServicesTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderSubServicesTypeDTO} or a {@link Page} of {@link OrderSubServicesTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderSubServicesTypeQueryService extends QueryService<OrderSubServicesType> {

    private final Logger log = LoggerFactory.getLogger(OrderSubServicesTypeQueryService.class);


    private final OrderSubServicesTypeRepository orderSubServicesTypeRepository;

    private final OrderSubServicesTypeMapper orderSubServicesTypeMapper;

    private final OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderSubServicesTypeQueryService(OrderSubServicesTypeRepository orderSubServicesTypeRepository, OrderSubServicesTypeMapper orderSubServicesTypeMapper, OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderSubServicesTypeRepository = orderSubServicesTypeRepository;
        this.orderSubServicesTypeMapper = orderSubServicesTypeMapper;
        this.orderSubServicesTypeSearchRepository = orderSubServicesTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link OrderSubServicesTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderSubServicesTypeDTO> findByCriteria(OrderSubServicesTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderSubServicesType> specification = createSpecification(criteria);
        return orderSubServicesTypeMapper.toDto(orderSubServicesTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderSubServicesTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderSubServicesTypeDTO> findByCriteria(OrderSubServicesTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderSubServicesType> specification = createSpecification(criteria);
        final Page<OrderSubServicesType> result = orderSubServicesTypeRepository.findAll(specification, page);
        return result.map(orderSubServicesTypeMapper::toDto);
    }

    /**
     * Function to convert OrderSubServicesTypeCriteria to a {@link Specifications}
     */
    private Specifications<OrderSubServicesType> createSpecification(OrderSubServicesTypeCriteria criteria) {
        Specifications<OrderSubServicesType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderSubServicesType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrderSubServicesType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrderSubServicesType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OrderSubServicesType_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderSubServicesType_.company, Company_.id));
            }
        }
        return specification;
    }

}
