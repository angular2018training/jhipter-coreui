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

import vn.nextlogix.domain.OrderSubServices;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderSubServicesRepository;
import vn.nextlogix.repository.search.OrderSubServicesSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.OrderSubServicesCriteria;

import vn.nextlogix.service.dto.OrderSubServicesDTO;
import vn.nextlogix.service.mapper.OrderSubServicesMapper;

/**
 * Service for executing complex queries for OrderSubServices entities in the database.
 * The main input is a {@link OrderSubServicesCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderSubServicesDTO} or a {@link Page} of {@link OrderSubServicesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderSubServicesQueryService extends QueryService<OrderSubServices> {

    private final Logger log = LoggerFactory.getLogger(OrderSubServicesQueryService.class);


    private final OrderSubServicesRepository orderSubServicesRepository;

    private final OrderSubServicesMapper orderSubServicesMapper;

    private final OrderSubServicesSearchRepository orderSubServicesSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderSubServicesQueryService(OrderSubServicesRepository orderSubServicesRepository, OrderSubServicesMapper orderSubServicesMapper, OrderSubServicesSearchRepository orderSubServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderSubServicesRepository = orderSubServicesRepository;
        this.orderSubServicesMapper = orderSubServicesMapper;
        this.orderSubServicesSearchRepository = orderSubServicesSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link OrderSubServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderSubServicesDTO> findByCriteria(OrderSubServicesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderSubServices> specification = createSpecification(criteria);
        return orderSubServicesMapper.toDto(orderSubServicesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderSubServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderSubServicesDTO> findByCriteria(OrderSubServicesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderSubServices> specification = createSpecification(criteria);
        final Page<OrderSubServices> result = orderSubServicesRepository.findAll(specification, page);
        return result.map(orderSubServicesMapper::toDto);
    }

    /**
     * Function to convert OrderSubServicesCriteria to a {@link Specifications}
     */
    private Specifications<OrderSubServices> createSpecification(OrderSubServicesCriteria criteria) {
        Specifications<OrderSubServices> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderSubServices_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrderSubServices_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrderSubServices_.name));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), OrderSubServices_.isActive));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OrderSubServices_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderSubServices_.company, Company_.id));
            }
        }
        return specification;
    }

}
