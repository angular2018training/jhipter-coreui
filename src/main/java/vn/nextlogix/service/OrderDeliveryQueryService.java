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

import vn.nextlogix.domain.OrderDelivery;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderDeliveryRepository;
import vn.nextlogix.repository.search.OrderDeliverySearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.OrderStatusSearchRepository;
    import vn.nextlogix.service.mapper.OrderStatusMapper;

import vn.nextlogix.service.dto.OrderDeliveryCriteria;

import vn.nextlogix.service.dto.OrderDeliveryDTO;
import vn.nextlogix.service.mapper.OrderDeliveryMapper;

/**
 * Service for executing complex queries for OrderDelivery entities in the database.
 * The main input is a {@link OrderDeliveryCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderDeliveryDTO} or a {@link Page} of {@link OrderDeliveryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderDeliveryQueryService extends QueryService<OrderDelivery> {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryQueryService.class);


    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryMapper orderDeliveryMapper;

    private final OrderDeliverySearchRepository orderDeliverySearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final OrderStatusSearchRepository orderStatusSearchRepository;
        private final OrderStatusMapper orderStatusMapper;


    public OrderDeliveryQueryService(OrderDeliveryRepository orderDeliveryRepository, OrderDeliveryMapper orderDeliveryMapper, OrderDeliverySearchRepository orderDeliverySearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,OrderStatusSearchRepository orderStatusSearchRepository,OrderStatusMapper  orderStatusMapper
) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliveryMapper = orderDeliveryMapper;
        this.orderDeliverySearchRepository = orderDeliverySearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.orderStatusSearchRepository = orderStatusSearchRepository;
                                     this.orderStatusMapper = orderStatusMapper;

    }

    /**
     * Return a {@link List} of {@link OrderDeliveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderDeliveryDTO> findByCriteria(OrderDeliveryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderDelivery> specification = createSpecification(criteria);
        return orderDeliveryMapper.toDto(orderDeliveryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderDeliveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> findByCriteria(OrderDeliveryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderDelivery> specification = createSpecification(criteria);
        final Page<OrderDelivery> result = orderDeliveryRepository.findAll(specification, page);
        return result.map(orderDeliveryMapper::toDto);
    }

    /**
     * Function to convert OrderDeliveryCriteria to a {@link Specifications}
     */
    private Specifications<OrderDelivery> createSpecification(OrderDeliveryCriteria criteria) {
        Specifications<OrderDelivery> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderDelivery_.id));
            }
            if (criteria.getReceiver() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiver(), OrderDelivery_.receiver));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), OrderDelivery_.note));
            }
            if (criteria.getReceiveTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReceiveTime(), OrderDelivery_.receiveTime));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), OrderDelivery_.createDate));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderDelivery_.company, Company_.id));
            }
            if (criteria.getCreateUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreateUserId(), OrderDelivery_.createUser, UserExtraInfo_.id));
            }
            if (criteria.getOrderStatusId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderStatusId(), OrderDelivery_.orderStatus, OrderStatus_.id));
            }
        }
        return specification;
    }

}
