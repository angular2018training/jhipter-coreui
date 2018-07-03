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

import vn.nextlogix.domain.OrderMain;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderMainRepository;
import vn.nextlogix.repository.search.OrderMainSearchRepository;

    import vn.nextlogix.repository.search.OrderPickupSearchRepository;
    import vn.nextlogix.service.mapper.OrderPickupMapper;

    import vn.nextlogix.repository.search.OrderConsigneeSearchRepository;
    import vn.nextlogix.service.mapper.OrderConsigneeMapper;

    import vn.nextlogix.repository.search.OrderFeeSearchRepository;
    import vn.nextlogix.service.mapper.OrderFeeMapper;

    import vn.nextlogix.repository.search.OrderDeliverySearchRepository;
    import vn.nextlogix.service.mapper.OrderDeliveryMapper;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.OrderStatusSearchRepository;
    import vn.nextlogix.service.mapper.OrderStatusMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;

    import vn.nextlogix.repository.search.OrderServicesSearchRepository;
    import vn.nextlogix.service.mapper.OrderServicesMapper;

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;

import vn.nextlogix.service.dto.OrderMainCriteria;

import vn.nextlogix.service.dto.OrderMainDTO;
import vn.nextlogix.service.mapper.OrderMainMapper;

/**
 * Service for executing complex queries for OrderMain entities in the database.
 * The main input is a {@link OrderMainCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderMainDTO} or a {@link Page} of {@link OrderMainDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderMainQueryService extends QueryService<OrderMain> {

    private final Logger log = LoggerFactory.getLogger(OrderMainQueryService.class);


    private final OrderMainRepository orderMainRepository;

    private final OrderMainMapper orderMainMapper;

    private final OrderMainSearchRepository orderMainSearchRepository;


        private final OrderPickupSearchRepository orderPickupSearchRepository;
        private final OrderPickupMapper orderPickupMapper;

        private final OrderConsigneeSearchRepository orderConsigneeSearchRepository;
        private final OrderConsigneeMapper orderConsigneeMapper;

        private final OrderFeeSearchRepository orderFeeSearchRepository;
        private final OrderFeeMapper orderFeeMapper;

        private final OrderDeliverySearchRepository orderDeliverySearchRepository;
        private final OrderDeliveryMapper orderDeliveryMapper;

        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final OrderStatusSearchRepository orderStatusSearchRepository;
        private final OrderStatusMapper orderStatusMapper;

        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;

        private final OrderServicesSearchRepository orderServicesSearchRepository;
        private final OrderServicesMapper orderServicesMapper;


        private final PostOfficeSearchRepository postOfficeSearchRepository;
        private final PostOfficeMapper postOfficeMapper;


    public OrderMainQueryService(OrderMainRepository orderMainRepository, OrderMainMapper orderMainMapper, OrderMainSearchRepository orderMainSearchRepository     ,OrderPickupSearchRepository orderPickupSearchRepository,OrderPickupMapper  orderPickupMapper
,OrderConsigneeSearchRepository orderConsigneeSearchRepository,OrderConsigneeMapper  orderConsigneeMapper
,OrderFeeSearchRepository orderFeeSearchRepository,OrderFeeMapper  orderFeeMapper
,OrderDeliverySearchRepository orderDeliverySearchRepository,OrderDeliveryMapper  orderDeliveryMapper
,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,OrderStatusSearchRepository orderStatusSearchRepository,OrderStatusMapper  orderStatusMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
,OrderServicesSearchRepository orderServicesSearchRepository,OrderServicesMapper  orderServicesMapper
,PostOfficeSearchRepository postOfficeSearchRepository,PostOfficeMapper  postOfficeMapper
) {
        this.orderMainRepository = orderMainRepository;
        this.orderMainMapper = orderMainMapper;
        this.orderMainSearchRepository = orderMainSearchRepository;
                                    this.orderPickupSearchRepository = orderPickupSearchRepository;
                                     this.orderPickupMapper = orderPickupMapper;
                                    this.orderConsigneeSearchRepository = orderConsigneeSearchRepository;
                                     this.orderConsigneeMapper = orderConsigneeMapper;
                                    this.orderFeeSearchRepository = orderFeeSearchRepository;
                                     this.orderFeeMapper = orderFeeMapper;
                                    this.orderDeliverySearchRepository = orderDeliverySearchRepository;
                                     this.orderDeliveryMapper = orderDeliveryMapper;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.orderStatusSearchRepository = orderStatusSearchRepository;
                                     this.orderStatusMapper = orderStatusMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;
                                    this.orderServicesSearchRepository = orderServicesSearchRepository;
                                     this.orderServicesMapper = orderServicesMapper;
                                    this.postOfficeSearchRepository = postOfficeSearchRepository;
                                     this.postOfficeMapper = postOfficeMapper;

    }

    /**
     * Return a {@link List} of {@link OrderMainDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderMainDTO> findByCriteria(OrderMainCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderMain> specification = createSpecification(criteria);
        return orderMainMapper.toDto(orderMainRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderMainDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderMainDTO> findByCriteria(OrderMainCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderMain> specification = createSpecification(criteria);
        final Page<OrderMain> result = orderMainRepository.findAll(specification, page);
        return result.map(orderMainMapper::toDto);
    }

    /**
     * Function to convert OrderMainCriteria to a {@link Specifications}
     */
    private Specifications<OrderMain> createSpecification(OrderMainCriteria criteria) {
        Specifications<OrderMain> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderMain_.id));
            }
            if (criteria.getOrderNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrderNumber(), OrderMain_.orderNumber));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), OrderMain_.createDate));
            }
            if (criteria.getSendDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSendDate(), OrderMain_.sendDate));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), OrderMain_.weight));
            }
            if (criteria.getWidth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWidth(), OrderMain_.width));
            }
            if (criteria.getHeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeight(), OrderMain_.height));
            }
            if (criteria.getDepth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDepth(), OrderMain_.depth));
            }
            if (criteria.getQuantityItem() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantityItem(), OrderMain_.quantityItem));
            }
            if (criteria.getOrderPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderPrice(), OrderMain_.orderPrice));
            }
            if (criteria.getFinalWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalWeight(), OrderMain_.finalWeight));
            }
            if (criteria.getCustomerOrderNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerOrderNumber(), OrderMain_.customerOrderNumber));
            }
            if (criteria.getCodAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCodAmount(), OrderMain_.codAmount));
            }
            if (criteria.getOrderPickupId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderPickupId(), OrderMain_.orderPickup, OrderPickup_.id));
            }
            if (criteria.getOrderConsigneeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderConsigneeId(), OrderMain_.orderConsignee, OrderConsignee_.id));
            }
            if (criteria.getOrderFeeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderFeeId(), OrderMain_.orderFee, OrderFee_.id));
            }
            if (criteria.getOrderDeliveryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderDeliveryId(), OrderMain_.orderDelivery, OrderDelivery_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderMain_.company, Company_.id));
            }
            if (criteria.getCreateUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreateUserId(), OrderMain_.createUser, UserExtraInfo_.id));
            }
            if (criteria.getOrderStatusId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderStatusId(), OrderMain_.orderStatus, OrderStatus_.id));
            }
            if (criteria.getCustomerId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerId(), OrderMain_.customer, Customer_.id));
            }
            if (criteria.getMainServiceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMainServiceId(), OrderMain_.mainService, OrderServices_.id));
            }
            if (criteria.getCreatePostOfficeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreatePostOfficeId(), OrderMain_.createPostOffice, PostOffice_.id));
            }
            if (criteria.getCurrentPostOfficeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCurrentPostOfficeId(), OrderMain_.currentPostOffice, PostOffice_.id));
            }
        }
        return specification;
    }

}
