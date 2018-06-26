package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderStatusService;
import vn.nextlogix.domain.OrderStatus;
import vn.nextlogix.repository.OrderStatusRepository;
import vn.nextlogix.repository.search.OrderStatusSearchRepository;
import vn.nextlogix.service.dto.OrderStatusDTO;
import vn.nextlogix.service.dto.OrderStatusSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.OrderStatusMapper;
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
 * Service Implementation for managing OrderStatus.
 */
@Service
@Transactional
public class OrderStatusServiceImpl implements OrderStatusService {

    private final Logger log = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusMapper orderStatusMapper;

    private final OrderStatusSearchRepository orderStatusSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository, OrderStatusMapper orderStatusMapper, OrderStatusSearchRepository orderStatusSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapper = orderStatusMapper;
        this.orderStatusSearchRepository = orderStatusSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a orderStatus.
     *
     * @param orderStatusDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderStatusDTO save(OrderStatusDTO orderStatusDTO) {
        log.debug("Request to save OrderStatus : {}", orderStatusDTO);
        OrderStatus orderStatus = orderStatusMapper.toEntity(orderStatusDTO);
        orderStatus = orderStatusRepository.save(orderStatus);
        OrderStatusDTO result = orderStatusMapper.toDto(orderStatus);
        orderStatusSearchRepository.save(orderStatus);
        return result;
    }

    /**
     * Get all the orderStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderStatuses");
        return orderStatusRepository.findAll(pageable)
            .map(orderStatusMapper::toDto);
    }

    /**
     * Get one orderStatus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderStatusDTO findOne(Long id) {
        log.debug("Request to get OrderStatus : {}", id);
        OrderStatus orderStatus = orderStatusRepository.findOne(id);
        return orderStatusMapper.toDto(orderStatus);
    }

    /**
     * Delete the orderStatus by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderStatus : {}", id);
        orderStatusRepository.delete(id);
        orderStatusSearchRepository.delete(id);
    }

    /**
     * Search for the orderStatus corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderStatusDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderStatuses for query {}", query);
        Page<OrderStatus> result = orderStatusSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderStatusMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderStatusDTO> searchExample(OrderStatusSearchDTO searchDto, Pageable pageable) {
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
            Page<OrderStatus> orderStatusPage= orderStatusSearchRepository.search(query);
            List<OrderStatusDTO> orderStatusList =  StreamSupport
            .stream(orderStatusPage.spliterator(), false)
            .map(orderStatusMapper::toDto)
            .collect(Collectors.toList());
            orderStatusList.forEach(orderStatusDto -> {
            if(orderStatusDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderStatusDto.getCompanyId());
                orderStatusDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(orderStatusList,pageable,orderStatusPage.getTotalElements());
        }
}
