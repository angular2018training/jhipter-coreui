package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderDeliveryService;
import vn.nextlogix.domain.OrderDelivery;


    import vn.nextlogix.repository.OrderDeliveryRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderDeliverySearchRepository;
import vn.nextlogix.service.dto.OrderDeliveryDTO;
import vn.nextlogix.service.dto.OrderDeliverySearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.UserExtraInfo;
    import vn.nextlogix.domain.OrderStatus;
import vn.nextlogix.service.mapper.OrderDeliveryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.OrderStatusSearchRepository;
    import vn.nextlogix.service.mapper.OrderStatusMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderDelivery.
 */
@Service
@Transactional
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryServiceImpl.class);

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryMapper orderDeliveryMapper;

    private final OrderDeliverySearchRepository orderDeliverySearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final OrderStatusSearchRepository orderStatusSearchRepository;
        private final OrderStatusMapper orderStatusMapper;


    public OrderDeliveryServiceImpl(OrderDeliveryRepository orderDeliveryRepository, OrderDeliveryMapper orderDeliveryMapper, OrderDeliverySearchRepository orderDeliverySearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
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
     * Save a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderDeliveryDTO save(OrderDeliveryDTO orderDeliveryDTO) {
        log.debug("Request to save OrderDelivery : {}", orderDeliveryDTO);
        OrderDelivery orderDelivery = orderDeliveryMapper.toEntity(orderDeliveryDTO);
        orderDelivery = orderDeliveryRepository.save(orderDelivery);
        OrderDeliveryDTO result = orderDeliveryMapper.toDto(orderDelivery);
        orderDeliverySearchRepository.save(orderDelivery);
        return result;
    }

    /**
     * Get all the orderDeliveries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderDeliveries");
        return orderDeliveryRepository.findAll(pageable)
            .map(orderDeliveryMapper::toDto);
    }

    /**
     * Get one orderDelivery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderDeliveryDTO findOne(Long id) {
        log.debug("Request to get OrderDelivery : {}", id);
        OrderDelivery orderDelivery = orderDeliveryRepository.findOne(id);
        return orderDeliveryMapper.toDto(orderDelivery);
    }

    /**
     * Delete the orderDelivery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderDelivery : {}", id);
        orderDeliveryRepository.delete(id);
        orderDeliverySearchRepository.delete(id);
    }

    /**
     * Search for the orderDelivery corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderDeliveries for query {}", query);
        Page<OrderDelivery> result = orderDeliverySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderDeliveryMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> searchExample(OrderDeliverySearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getReceiver())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("receiver", "*"+searchDto.getReceiver()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getNote())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("note", "*"+searchDto.getNote()+"*"));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.Id", searchDto.getCompanyId()));
            }
            if(searchDto.getCreateUserId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("createUser.Id", searchDto.getCreateUserId()));
            }
            if(searchDto.getOrderStatusId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderStatus.Id", searchDto.getOrderStatusId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<OrderDelivery> orderDeliveryPage= orderDeliverySearchRepository.search(query);
            List<OrderDeliveryDTO> orderDeliveryList =  StreamSupport
            .stream(orderDeliveryPage.spliterator(), false)
            .map(orderDeliveryMapper::toDto)
            .collect(Collectors.toList());
            orderDeliveryList.forEach(orderDeliveryDto -> {
            if(orderDeliveryDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderDeliveryDto.getCompanyId());
                orderDeliveryDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(orderDeliveryDto.getCreateUserId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(orderDeliveryDto.getCreateUserId());
                orderDeliveryDto.setCreateUserDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            if(orderDeliveryDto.getOrderStatusId()!=null){
                OrderStatus orderStatus= orderStatusSearchRepository.findOne(orderDeliveryDto.getOrderStatusId());
                orderDeliveryDto.setOrderStatusDTO(orderStatusMapper.toDto(orderStatus));
            }
            });
            return new PageImpl<>(orderDeliveryList,pageable,orderDeliveryPage.getTotalElements());
        }
}
