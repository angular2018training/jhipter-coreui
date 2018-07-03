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

import vn.nextlogix.domain.OrderServices;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderServicesRepository;
import vn.nextlogix.repository.search.OrderServicesSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.OrderServicesTypeSearchRepository;
    import vn.nextlogix.service.mapper.OrderServicesTypeMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.OrderServicesCriteria;

import vn.nextlogix.service.dto.OrderServicesDTO;
import vn.nextlogix.service.mapper.OrderServicesMapper;

/**
 * Service for executing complex queries for OrderServices entities in the database.
 * The main input is a {@link OrderServicesCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderServicesDTO} or a {@link Page} of {@link OrderServicesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderServicesQueryService extends QueryService<OrderServices> {

    private final Logger log = LoggerFactory.getLogger(OrderServicesQueryService.class);


    private final OrderServicesRepository orderServicesRepository;

    private final OrderServicesMapper orderServicesMapper;

    private final OrderServicesSearchRepository orderServicesSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final OrderServicesTypeSearchRepository orderServicesTypeSearchRepository;
        private final OrderServicesTypeMapper orderServicesTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public OrderServicesQueryService(OrderServicesRepository orderServicesRepository, OrderServicesMapper orderServicesMapper, OrderServicesSearchRepository orderServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,OrderServicesTypeSearchRepository orderServicesTypeSearchRepository,OrderServicesTypeMapper  orderServicesTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.orderServicesRepository = orderServicesRepository;
        this.orderServicesMapper = orderServicesMapper;
        this.orderServicesSearchRepository = orderServicesSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.orderServicesTypeSearchRepository = orderServicesTypeSearchRepository;
                                     this.orderServicesTypeMapper = orderServicesTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link OrderServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderServicesDTO> findByCriteria(OrderServicesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderServices> specification = createSpecification(criteria);
        return orderServicesMapper.toDto(orderServicesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderServicesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderServicesDTO> findByCriteria(OrderServicesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderServices> specification = createSpecification(criteria);
        final Page<OrderServices> result = orderServicesRepository.findAll(specification, page);
        return result.map(orderServicesMapper::toDto);
    }

    /**
     * Function to convert OrderServicesCriteria to a {@link Specifications}
     */
    private Specifications<OrderServices> createSpecification(OrderServicesCriteria criteria) {
        Specifications<OrderServices> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderServices_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrderServices_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrderServices_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OrderServices_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderServices_.company, Company_.id));
            }
            if (criteria.getOrderServicesTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderServicesTypeId(), OrderServices_.orderServicesType, OrderServicesType_.id));
            }
            if (criteria.getQuotationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationId(), OrderServices_.quotation, Quotation_.id));
            }
        }
        return specification;
    }

}
