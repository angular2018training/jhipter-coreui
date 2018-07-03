package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderMainService;
import vn.nextlogix.domain.OrderMain;


    import vn.nextlogix.repository.OrderMainRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderMainSearchRepository;
import vn.nextlogix.service.dto.OrderMainDTO;
import vn.nextlogix.service.dto.OrderMainSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.OrderPickup;
    import vn.nextlogix.domain.OrderConsignee;
    import vn.nextlogix.domain.OrderFee;
    import vn.nextlogix.domain.OrderDelivery;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.UserExtraInfo;
    import vn.nextlogix.domain.OrderStatus;
    import vn.nextlogix.domain.Customer;
    import vn.nextlogix.domain.OrderServices;
    import vn.nextlogix.domain.PostOffice;
    import vn.nextlogix.domain.PostOffice;
import vn.nextlogix.service.mapper.OrderMainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderMain.
 */
@Service
@Transactional
public class OrderMainServiceImpl implements OrderMainService {

    private final Logger log = LoggerFactory.getLogger(OrderMainServiceImpl.class);

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


    public OrderMainServiceImpl(OrderMainRepository orderMainRepository, OrderMainMapper orderMainMapper, OrderMainSearchRepository orderMainSearchRepository     ,OrderPickupSearchRepository orderPickupSearchRepository,OrderPickupMapper  orderPickupMapper
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
     * Save a orderMain.
     *
     * @param orderMainDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderMainDTO save(OrderMainDTO orderMainDTO) {
        log.debug("Request to save OrderMain : {}", orderMainDTO);
        OrderMain orderMain = orderMainMapper.toEntity(orderMainDTO);
        orderMain = orderMainRepository.save(orderMain);
        OrderMainDTO result = orderMainMapper.toDto(orderMain);
        orderMainSearchRepository.save(orderMain);
        return result;
    }

    /**
     * Get all the orderMains.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderMainDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderMains");
        return orderMainRepository.findAll(pageable)
            .map(orderMainMapper::toDto);
    }

    /**
     * Get one orderMain by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderMainDTO findOne(Long id) {
        log.debug("Request to get OrderMain : {}", id);
        OrderMain orderMain = orderMainRepository.findOne(id);
        return orderMainMapper.toDto(orderMain);
    }

    /**
     * Delete the orderMain by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderMain : {}", id);
        orderMainRepository.delete(id);
        orderMainSearchRepository.delete(id);
    }

    /**
     * Search for the orderMain corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderMainDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderMains for query {}", query);
        Page<OrderMain> result = orderMainSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderMainMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderMainDTO> searchExample(OrderMainSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getOrderNumber())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("orderNumber", "*"+searchDto.getOrderNumber()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getCustomerOrderNumber())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("customerOrderNumber", "*"+searchDto.getCustomerOrderNumber()+"*"));
            }
            if(searchDto.getOrderPickupId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderPickup.id", searchDto.getOrderPickupId()));
            }
            if(searchDto.getOrderConsigneeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderConsignee.id", searchDto.getOrderConsigneeId()));
            }
            if(searchDto.getOrderFeeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderFee.id", searchDto.getOrderFeeId()));
            }
            if(searchDto.getOrderDeliveryId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderDelivery.id", searchDto.getOrderDeliveryId()));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getCreateUserId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("createUser.id", searchDto.getCreateUserId()));
            }
            if(searchDto.getOrderStatusId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderStatus.id", searchDto.getOrderStatusId()));
            }
            if(searchDto.getCustomerId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customer.id", searchDto.getCustomerId()));
            }
            if(searchDto.getMainServiceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("mainService.id", searchDto.getMainServiceId()));
            }
            if(searchDto.getCreatePostOfficeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("createPostOffice.id", searchDto.getCreatePostOfficeId()));
            }
            if(searchDto.getCurrentPostOfficeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("currentPostOffice.id", searchDto.getCurrentPostOfficeId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<OrderMain> orderMainPage= orderMainSearchRepository.search(query);
            List<OrderMainDTO> orderMainList =  StreamSupport
            .stream(orderMainPage.spliterator(), false)
            .map(orderMainMapper::toDto)
            .collect(Collectors.toList());
            orderMainList.forEach(orderMainDto -> {
            if(orderMainDto.getOrderPickupId()!=null){
                OrderPickup orderPickup= orderPickupSearchRepository.findOne(orderMainDto.getOrderPickupId());
                orderMainDto.setOrderPickupDTO(orderPickupMapper.toDto(orderPickup));
            }
            if(orderMainDto.getOrderConsigneeId()!=null){
                OrderConsignee orderConsignee= orderConsigneeSearchRepository.findOne(orderMainDto.getOrderConsigneeId());
                orderMainDto.setOrderConsigneeDTO(orderConsigneeMapper.toDto(orderConsignee));
            }
            if(orderMainDto.getOrderFeeId()!=null){
                OrderFee orderFee= orderFeeSearchRepository.findOne(orderMainDto.getOrderFeeId());
                orderMainDto.setOrderFeeDTO(orderFeeMapper.toDto(orderFee));
            }
            if(orderMainDto.getOrderDeliveryId()!=null){
                OrderDelivery orderDelivery= orderDeliverySearchRepository.findOne(orderMainDto.getOrderDeliveryId());
                orderMainDto.setOrderDeliveryDTO(orderDeliveryMapper.toDto(orderDelivery));
            }
            if(orderMainDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderMainDto.getCompanyId());
                orderMainDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(orderMainDto.getCreateUserId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(orderMainDto.getCreateUserId());
                orderMainDto.setCreateUserDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            if(orderMainDto.getOrderStatusId()!=null){
                OrderStatus orderStatus= orderStatusSearchRepository.findOne(orderMainDto.getOrderStatusId());
                orderMainDto.setOrderStatusDTO(orderStatusMapper.toDto(orderStatus));
            }
            if(orderMainDto.getCustomerId()!=null){
                Customer customer= customerSearchRepository.findOne(orderMainDto.getCustomerId());
                orderMainDto.setCustomerDTO(customerMapper.toDto(customer));
            }
            if(orderMainDto.getMainServiceId()!=null){
                OrderServices orderServices= orderServicesSearchRepository.findOne(orderMainDto.getMainServiceId());
                orderMainDto.setMainServiceDTO(orderServicesMapper.toDto(orderServices));
            }
            if(orderMainDto.getCreatePostOfficeId()!=null){
                PostOffice postOffice= postOfficeSearchRepository.findOne(orderMainDto.getCreatePostOfficeId());
                orderMainDto.setCreatePostOfficeDTO(postOfficeMapper.toDto(postOffice));
            }
            if(orderMainDto.getCurrentPostOfficeId()!=null){
                PostOffice postOffice= postOfficeSearchRepository.findOne(orderMainDto.getCurrentPostOfficeId());
                orderMainDto.setCurrentPostOfficeDTO(postOfficeMapper.toDto(postOffice));
            }
            });
            return new PageImpl<>(orderMainList,pageable,orderMainPage.getTotalElements());
        }
}
