package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderServicesTypeService;
import vn.nextlogix.domain.OrderServicesType;


    import vn.nextlogix.repository.OrderServicesTypeRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderServicesTypeSearchRepository;
import vn.nextlogix.service.dto.OrderServicesTypeDTO;
import vn.nextlogix.service.dto.OrderServicesTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
import vn.nextlogix.service.mapper.OrderServicesTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderServicesType.
 */
@Service
@Transactional
public class OrderServicesTypeServiceImpl implements OrderServicesTypeService {

    private final Logger log = LoggerFactory.getLogger(OrderServicesTypeServiceImpl.class);

    private final OrderServicesTypeRepository orderServicesTypeRepository;

    private final OrderServicesTypeMapper orderServicesTypeMapper;

    private final OrderServicesTypeSearchRepository orderServicesTypeSearchRepository;



    public OrderServicesTypeServiceImpl(OrderServicesTypeRepository orderServicesTypeRepository, OrderServicesTypeMapper orderServicesTypeMapper, OrderServicesTypeSearchRepository orderServicesTypeSearchRepository     ) {
        this.orderServicesTypeRepository = orderServicesTypeRepository;
        this.orderServicesTypeMapper = orderServicesTypeMapper;
        this.orderServicesTypeSearchRepository = orderServicesTypeSearchRepository;

    }

    /**
     * Save a orderServicesType.
     *
     * @param orderServicesTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderServicesTypeDTO save(OrderServicesTypeDTO orderServicesTypeDTO) {
        log.debug("Request to save OrderServicesType : {}", orderServicesTypeDTO);
        OrderServicesType orderServicesType = orderServicesTypeMapper.toEntity(orderServicesTypeDTO);
        orderServicesType = orderServicesTypeRepository.save(orderServicesType);
        OrderServicesTypeDTO result = orderServicesTypeMapper.toDto(orderServicesType);
        orderServicesTypeSearchRepository.save(orderServicesType);
        return result;
    }

    /**
     * Get all the orderServicesTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderServicesTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderServicesTypes");
        return orderServicesTypeRepository.findAll(pageable)
            .map(orderServicesTypeMapper::toDto);
    }

    /**
     * Get one orderServicesType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderServicesTypeDTO findOne(Long id) {
        log.debug("Request to get OrderServicesType : {}", id);
        OrderServicesType orderServicesType = orderServicesTypeRepository.findOne(id);
        return orderServicesTypeMapper.toDto(orderServicesType);
    }

    /**
     * Delete the orderServicesType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderServicesType : {}", id);
        orderServicesTypeRepository.delete(id);
        orderServicesTypeSearchRepository.delete(id);
    }

    /**
     * Search for the orderServicesType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderServicesTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderServicesTypes for query {}", query);
        Page<OrderServicesType> result = orderServicesTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderServicesTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderServicesTypeDTO> searchExample(OrderServicesTypeSearchDTO searchDto, Pageable pageable) {
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
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<OrderServicesType> orderServicesTypePage= orderServicesTypeSearchRepository.search(query);
            List<OrderServicesTypeDTO> orderServicesTypeList =  StreamSupport
            .stream(orderServicesTypePage.spliterator(), false)
            .map(orderServicesTypeMapper::toDto)
            .collect(Collectors.toList());
            orderServicesTypeList.forEach(orderServicesTypeDto -> {
            });
            return new PageImpl<>(orderServicesTypeList,pageable,orderServicesTypePage.getTotalElements());
        }
}
