package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderUserRouteService;
import vn.nextlogix.domain.OrderUserRoute;


    import vn.nextlogix.repository.OrderUserRouteRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderUserRouteSearchRepository;
import vn.nextlogix.service.dto.OrderUserRouteDTO;
import vn.nextlogix.service.dto.OrderUserRouteSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.UserExtraInfo;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.OrderUserRouteType;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.Customer;
import vn.nextlogix.service.mapper.OrderUserRouteMapper;
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

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.OrderUserRouteTypeSearchRepository;
    import vn.nextlogix.service.mapper.OrderUserRouteTypeMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderUserRoute.
 */
@Service
@Transactional
public class OrderUserRouteServiceImpl implements OrderUserRouteService {

    private final Logger log = LoggerFactory.getLogger(OrderUserRouteServiceImpl.class);

    private final OrderUserRouteRepository orderUserRouteRepository;

    private final OrderUserRouteMapper orderUserRouteMapper;

    private final OrderUserRouteSearchRepository orderUserRouteSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository;
        private final OrderUserRouteTypeMapper orderUserRouteTypeMapper;


        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public OrderUserRouteServiceImpl(OrderUserRouteRepository orderUserRouteRepository, OrderUserRouteMapper orderUserRouteMapper, OrderUserRouteSearchRepository orderUserRouteSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository,OrderUserRouteTypeMapper  orderUserRouteTypeMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.orderUserRouteRepository = orderUserRouteRepository;
        this.orderUserRouteMapper = orderUserRouteMapper;
        this.orderUserRouteSearchRepository = orderUserRouteSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.orderUserRouteTypeSearchRepository = orderUserRouteTypeSearchRepository;
                                     this.orderUserRouteTypeMapper = orderUserRouteTypeMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Save a orderUserRoute.
     *
     * @param orderUserRouteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderUserRouteDTO save(OrderUserRouteDTO orderUserRouteDTO) {
        log.debug("Request to save OrderUserRoute : {}", orderUserRouteDTO);
        OrderUserRoute orderUserRoute = orderUserRouteMapper.toEntity(orderUserRouteDTO);
        orderUserRoute = orderUserRouteRepository.save(orderUserRoute);
        OrderUserRouteDTO result = orderUserRouteMapper.toDto(orderUserRoute);
        orderUserRouteSearchRepository.save(orderUserRoute);
        return result;
    }

    /**
     * Get all the orderUserRoutes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderUserRouteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderUserRoutes");
        return orderUserRouteRepository.findAll(pageable)
            .map(orderUserRouteMapper::toDto);
    }

    /**
     * Get one orderUserRoute by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderUserRouteDTO findOne(Long id) {
        log.debug("Request to get OrderUserRoute : {}", id);
        OrderUserRoute orderUserRoute = orderUserRouteRepository.findOne(id);
        return orderUserRouteMapper.toDto(orderUserRoute);
    }

    /**
     * Delete the orderUserRoute by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderUserRoute : {}", id);
        orderUserRouteRepository.delete(id);
        orderUserRouteSearchRepository.delete(id);
    }

    /**
     * Search for the orderUserRoute corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderUserRouteDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderUserRoutes for query {}", query);
        Page<OrderUserRoute> result = orderUserRouteSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderUserRouteMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderUserRouteDTO> searchExample(OrderUserRouteSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.Id", searchDto.getCompanyId()));
            }
            if(searchDto.getUserId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("user.Id", searchDto.getUserId()));
            }
            if(searchDto.getProvinceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("province.Id", searchDto.getProvinceId()));
            }
            if(searchDto.getDistrictId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("district.Id", searchDto.getDistrictId()));
            }
            if(searchDto.getOrderUserRouteTypeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderUserRouteType.Id", searchDto.getOrderUserRouteTypeId()));
            }
            if(searchDto.getWardId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("ward.Id", searchDto.getWardId()));
            }
            if(searchDto.getCustomerId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customer.Id", searchDto.getCustomerId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<OrderUserRoute> orderUserRoutePage= orderUserRouteSearchRepository.search(query);
            List<OrderUserRouteDTO> orderUserRouteList =  StreamSupport
            .stream(orderUserRoutePage.spliterator(), false)
            .map(orderUserRouteMapper::toDto)
            .collect(Collectors.toList());
            orderUserRouteList.forEach(orderUserRouteDto -> {
            if(orderUserRouteDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderUserRouteDto.getCompanyId());
                orderUserRouteDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(orderUserRouteDto.getUserId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(orderUserRouteDto.getUserId());
                orderUserRouteDto.setUserDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            if(orderUserRouteDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(orderUserRouteDto.getProvinceId());
                orderUserRouteDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(orderUserRouteDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(orderUserRouteDto.getDistrictId());
                orderUserRouteDto.setDistrictDTO(districtMapper.toDto(district));
            }
            if(orderUserRouteDto.getOrderUserRouteTypeId()!=null){
                OrderUserRouteType orderUserRouteType= orderUserRouteTypeSearchRepository.findOne(orderUserRouteDto.getOrderUserRouteTypeId());
                orderUserRouteDto.setOrderUserRouteTypeDTO(orderUserRouteTypeMapper.toDto(orderUserRouteType));
            }
            if(orderUserRouteDto.getWardId()!=null){
                District district= districtSearchRepository.findOne(orderUserRouteDto.getWardId());
                orderUserRouteDto.setWardDTO(districtMapper.toDto(district));
            }
            if(orderUserRouteDto.getCustomerId()!=null){
                Customer customer= customerSearchRepository.findOne(orderUserRouteDto.getCustomerId());
                orderUserRouteDto.setCustomerDTO(customerMapper.toDto(customer));
            }
            });
            return new PageImpl<>(orderUserRouteList,pageable,orderUserRoutePage.getTotalElements());
        }
}
