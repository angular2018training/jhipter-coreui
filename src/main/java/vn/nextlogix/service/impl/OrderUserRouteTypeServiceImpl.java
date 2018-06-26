package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderUserRouteTypeService;
import vn.nextlogix.domain.OrderUserRouteType;
import vn.nextlogix.repository.OrderUserRouteTypeRepository;
import vn.nextlogix.repository.search.OrderUserRouteTypeSearchRepository;
import vn.nextlogix.service.dto.OrderUserRouteTypeDTO;
import vn.nextlogix.service.dto.OrderUserRouteTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.OrderUserRouteTypeMapper;
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
 * Service Implementation for managing OrderUserRouteType.
 */
@Service
@Transactional
public class OrderUserRouteTypeServiceImpl implements OrderUserRouteTypeService {

    private final Logger log = LoggerFactory.getLogger(OrderUserRouteTypeServiceImpl.class);

    private final OrderUserRouteTypeRepository orderUserRouteTypeRepository;

    private final OrderUserRouteTypeMapper orderUserRouteTypeMapper;

    private final OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderUserRouteTypeServiceImpl(OrderUserRouteTypeRepository orderUserRouteTypeRepository, OrderUserRouteTypeMapper orderUserRouteTypeMapper, OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderUserRouteTypeRepository = orderUserRouteTypeRepository;
        this.orderUserRouteTypeMapper = orderUserRouteTypeMapper;
        this.orderUserRouteTypeSearchRepository = orderUserRouteTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a orderUserRouteType.
     *
     * @param orderUserRouteTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderUserRouteTypeDTO save(OrderUserRouteTypeDTO orderUserRouteTypeDTO) {
        log.debug("Request to save OrderUserRouteType : {}", orderUserRouteTypeDTO);
        OrderUserRouteType orderUserRouteType = orderUserRouteTypeMapper.toEntity(orderUserRouteTypeDTO);
        orderUserRouteType = orderUserRouteTypeRepository.save(orderUserRouteType);
        OrderUserRouteTypeDTO result = orderUserRouteTypeMapper.toDto(orderUserRouteType);
        orderUserRouteTypeSearchRepository.save(orderUserRouteType);
        return result;
    }

    /**
     * Get all the orderUserRouteTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderUserRouteTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderUserRouteTypes");
        return orderUserRouteTypeRepository.findAll(pageable)
            .map(orderUserRouteTypeMapper::toDto);
    }

    /**
     * Get one orderUserRouteType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderUserRouteTypeDTO findOne(Long id) {
        log.debug("Request to get OrderUserRouteType : {}", id);
        OrderUserRouteType orderUserRouteType = orderUserRouteTypeRepository.findOne(id);
        return orderUserRouteTypeMapper.toDto(orderUserRouteType);
    }

    /**
     * Delete the orderUserRouteType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderUserRouteType : {}", id);
        orderUserRouteTypeRepository.delete(id);
        orderUserRouteTypeSearchRepository.delete(id);
    }

    /**
     * Search for the orderUserRouteType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderUserRouteTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderUserRouteTypes for query {}", query);
        Page<OrderUserRouteType> result = orderUserRouteTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderUserRouteTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderUserRouteTypeDTO> searchExample(OrderUserRouteTypeSearchDTO searchDto, Pageable pageable) {
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
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<OrderUserRouteType> orderUserRouteTypePage= orderUserRouteTypeSearchRepository.search(query);
            List<OrderUserRouteTypeDTO> orderUserRouteTypeList =  StreamSupport
            .stream(orderUserRouteTypePage.spliterator(), false)
            .map(orderUserRouteTypeMapper::toDto)
            .collect(Collectors.toList());
            orderUserRouteTypeList.forEach(orderUserRouteTypeDto -> {
            if(orderUserRouteTypeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderUserRouteTypeDto.getCompanyId());
                orderUserRouteTypeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(orderUserRouteTypeList,pageable,orderUserRouteTypePage.getTotalElements());
        }
}
