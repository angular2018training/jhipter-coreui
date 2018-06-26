package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderPickupService;
import vn.nextlogix.domain.OrderPickup;


    import vn.nextlogix.repository.OrderPickupRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderPickupSearchRepository;
import vn.nextlogix.service.dto.OrderPickupDTO;
import vn.nextlogix.service.dto.OrderPickupSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Ward;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.service.mapper.OrderPickupMapper;
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

    import vn.nextlogix.repository.search.WardSearchRepository;
    import vn.nextlogix.service.mapper.WardMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderPickup.
 */
@Service
@Transactional
public class OrderPickupServiceImpl implements OrderPickupService {

    private final Logger log = LoggerFactory.getLogger(OrderPickupServiceImpl.class);

    private final OrderPickupRepository orderPickupRepository;

    private final OrderPickupMapper orderPickupMapper;

    private final OrderPickupSearchRepository orderPickupSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final WardSearchRepository wardSearchRepository;
        private final WardMapper wardMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;


    public OrderPickupServiceImpl(OrderPickupRepository orderPickupRepository, OrderPickupMapper orderPickupMapper, OrderPickupSearchRepository orderPickupSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,WardSearchRepository wardSearchRepository,WardMapper  wardMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
) {
        this.orderPickupRepository = orderPickupRepository;
        this.orderPickupMapper = orderPickupMapper;
        this.orderPickupSearchRepository = orderPickupSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.wardSearchRepository = wardSearchRepository;
                                     this.wardMapper = wardMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;

    }

    /**
     * Save a orderPickup.
     *
     * @param orderPickupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderPickupDTO save(OrderPickupDTO orderPickupDTO) {
        log.debug("Request to save OrderPickup : {}", orderPickupDTO);
        OrderPickup orderPickup = orderPickupMapper.toEntity(orderPickupDTO);
        orderPickup = orderPickupRepository.save(orderPickup);
        OrderPickupDTO result = orderPickupMapper.toDto(orderPickup);
        orderPickupSearchRepository.save(orderPickup);
        return result;
    }

    /**
     * Get all the orderPickups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderPickupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderPickups");
        return orderPickupRepository.findAll(pageable)
            .map(orderPickupMapper::toDto);
    }

    /**
     * Get one orderPickup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderPickupDTO findOne(Long id) {
        log.debug("Request to get OrderPickup : {}", id);
        OrderPickup orderPickup = orderPickupRepository.findOne(id);
        return orderPickupMapper.toDto(orderPickup);
    }

    /**
     * Delete the orderPickup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderPickup : {}", id);
        orderPickupRepository.delete(id);
        orderPickupSearchRepository.delete(id);
    }

    /**
     * Search for the orderPickup corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderPickupDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderPickups for query {}", query);
        Page<OrderPickup> result = orderPickupSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderPickupMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderPickupDTO> searchExample(OrderPickupSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getAddress())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("address", "*"+searchDto.getAddress()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContactPhone())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("contactPhone", "*"+searchDto.getContactPhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContactName())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("contactName", "*"+searchDto.getContactName()+"*"));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.Id", searchDto.getCompanyId()));
            }
            if(searchDto.getWardId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("ward.Id", searchDto.getWardId()));
            }
            if(searchDto.getDistrictId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("district.Id", searchDto.getDistrictId()));
            }
            if(searchDto.getProvinceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("province.Id", searchDto.getProvinceId()));
            }
            if(searchDto.getPickupUserId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("pickupUser.Id", searchDto.getPickupUserId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<OrderPickup> orderPickupPage= orderPickupSearchRepository.search(query);
            List<OrderPickupDTO> orderPickupList =  StreamSupport
            .stream(orderPickupPage.spliterator(), false)
            .map(orderPickupMapper::toDto)
            .collect(Collectors.toList());
            orderPickupList.forEach(orderPickupDto -> {
            if(orderPickupDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderPickupDto.getCompanyId());
                orderPickupDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(orderPickupDto.getWardId()!=null){
                Ward ward= wardSearchRepository.findOne(orderPickupDto.getWardId());
                orderPickupDto.setWardDTO(wardMapper.toDto(ward));
            }
            if(orderPickupDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(orderPickupDto.getDistrictId());
                orderPickupDto.setDistrictDTO(districtMapper.toDto(district));
            }
            if(orderPickupDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(orderPickupDto.getProvinceId());
                orderPickupDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(orderPickupDto.getPickupUserId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(orderPickupDto.getPickupUserId());
                orderPickupDto.setPickupUserDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            });
            return new PageImpl<>(orderPickupList,pageable,orderPickupPage.getTotalElements());
        }
}
