package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderSubServicesTypeService;
import vn.nextlogix.domain.OrderSubServicesType;


    import vn.nextlogix.repository.OrderSubServicesTypeRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderSubServicesTypeSearchRepository;
import vn.nextlogix.service.dto.OrderSubServicesTypeDTO;
import vn.nextlogix.service.dto.OrderSubServicesTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.OrderSubServicesTypeMapper;
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
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderSubServicesType.
 */
@Service
@Transactional
public class OrderSubServicesTypeServiceImpl implements OrderSubServicesTypeService {

    private final Logger log = LoggerFactory.getLogger(OrderSubServicesTypeServiceImpl.class);

    private final OrderSubServicesTypeRepository orderSubServicesTypeRepository;

    private final OrderSubServicesTypeMapper orderSubServicesTypeMapper;

    private final OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderSubServicesTypeServiceImpl(OrderSubServicesTypeRepository orderSubServicesTypeRepository, OrderSubServicesTypeMapper orderSubServicesTypeMapper, OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderSubServicesTypeRepository = orderSubServicesTypeRepository;
        this.orderSubServicesTypeMapper = orderSubServicesTypeMapper;
        this.orderSubServicesTypeSearchRepository = orderSubServicesTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a orderSubServicesType.
     *
     * @param orderSubServicesTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderSubServicesTypeDTO save(OrderSubServicesTypeDTO orderSubServicesTypeDTO) {
        log.debug("Request to save OrderSubServicesType : {}", orderSubServicesTypeDTO);
        OrderSubServicesType orderSubServicesType = orderSubServicesTypeMapper.toEntity(orderSubServicesTypeDTO);
        orderSubServicesType = orderSubServicesTypeRepository.save(orderSubServicesType);
        OrderSubServicesTypeDTO result = orderSubServicesTypeMapper.toDto(orderSubServicesType);
        orderSubServicesTypeSearchRepository.save(orderSubServicesType);
        return result;
    }

    /**
     * Get all the orderSubServicesTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderSubServicesTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderSubServicesTypes");
        return orderSubServicesTypeRepository.findAll(pageable)
            .map(orderSubServicesTypeMapper::toDto);
    }

    /**
     * Get one orderSubServicesType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderSubServicesTypeDTO findOne(Long id) {
        log.debug("Request to get OrderSubServicesType : {}", id);
        OrderSubServicesType orderSubServicesType = orderSubServicesTypeRepository.findOne(id);
        return orderSubServicesTypeMapper.toDto(orderSubServicesType);
    }

    /**
     * Delete the orderSubServicesType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderSubServicesType : {}", id);
        orderSubServicesTypeRepository.delete(id);
        orderSubServicesTypeSearchRepository.delete(id);
    }

    /**
     * Search for the orderSubServicesType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderSubServicesTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderSubServicesTypes for query {}", query);
        Page<OrderSubServicesType> result = orderSubServicesTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderSubServicesTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderSubServicesTypeDTO> searchExample(OrderSubServicesTypeSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<OrderSubServicesType> orderSubServicesTypePage= orderSubServicesTypeSearchRepository.search(query);
            List<OrderSubServicesTypeDTO> orderSubServicesTypeList =  StreamSupport
            .stream(orderSubServicesTypePage.spliterator(), false)
            .map(orderSubServicesTypeMapper::toDto)
            .collect(Collectors.toList());
            orderSubServicesTypeList.forEach(orderSubServicesTypeDto -> {
            if(orderSubServicesTypeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderSubServicesTypeDto.getCompanyId());
                orderSubServicesTypeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(orderSubServicesTypeList,pageable,orderSubServicesTypePage.getTotalElements());
        }
}
