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

import vn.nextlogix.domain.OrderFee;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderFeeRepository;
import vn.nextlogix.repository.search.OrderFeeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

import vn.nextlogix.service.dto.OrderFeeCriteria;

import vn.nextlogix.service.dto.OrderFeeDTO;
import vn.nextlogix.service.mapper.OrderFeeMapper;

/**
 * Service for executing complex queries for OrderFee entities in the database.
 * The main input is a {@link OrderFeeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderFeeDTO} or a {@link Page} of {@link OrderFeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderFeeQueryService extends QueryService<OrderFee> {

    private final Logger log = LoggerFactory.getLogger(OrderFeeQueryService.class);


    private final OrderFeeRepository orderFeeRepository;

    private final OrderFeeMapper orderFeeMapper;

    private final OrderFeeSearchRepository orderFeeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public OrderFeeQueryService(OrderFeeRepository orderFeeRepository, OrderFeeMapper orderFeeMapper, OrderFeeSearchRepository orderFeeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.orderFeeRepository = orderFeeRepository;
        this.orderFeeMapper = orderFeeMapper;
        this.orderFeeSearchRepository = orderFeeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Return a {@link List} of {@link OrderFeeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderFeeDTO> findByCriteria(OrderFeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderFee> specification = createSpecification(criteria);
        return orderFeeMapper.toDto(orderFeeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderFeeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderFeeDTO> findByCriteria(OrderFeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderFee> specification = createSpecification(criteria);
        final Page<OrderFee> result = orderFeeRepository.findAll(specification, page);
        return result.map(orderFeeMapper::toDto);
    }

    /**
     * Function to convert OrderFeeCriteria to a {@link Specifications}
     */
    private Specifications<OrderFee> createSpecification(OrderFeeCriteria criteria) {
        Specifications<OrderFee> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderFee_.id));
            }
            if (criteria.getDeliveryFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeliveryFee(), OrderFee_.deliveryFee));
            }
            if (criteria.getPickupFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPickupFee(), OrderFee_.pickupFee));
            }
            if (criteria.getCodFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCodFee(), OrderFee_.codFee));
            }
            if (criteria.getInsuranceFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsuranceFee(), OrderFee_.insuranceFee));
            }
            if (criteria.getOtherFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOtherFee(), OrderFee_.otherFee));
            }
            if (criteria.getDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscount(), OrderFee_.discount));
            }
            if (criteria.getTotalFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalFee(), OrderFee_.totalFee));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderFee_.company, Company_.id));
            }
            if (criteria.getQuotationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuotationId(), OrderFee_.quotation, Quotation_.id));
            }
        }
        return specification;
    }

}
