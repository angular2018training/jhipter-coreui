package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderServiceService;
import vn.nextlogix.domain.OrderService;
import vn.nextlogix.repository.OrderServiceRepository;
import vn.nextlogix.repository.search.OrderServiceSearchRepository;
import vn.nextlogix.service.dto.OrderServiceDTO;
import vn.nextlogix.service.dto.OrderServiceSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.OrderServiceMapper;
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
 * Service Implementation for managing OrderService.
 */
@Service
@Transactional
public class OrderServiceServiceImpl implements OrderServiceService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceServiceImpl.class);

    private final OrderServiceRepository orderServiceRepository;

    private final OrderServiceMapper orderServiceMapper;

    private final OrderServiceSearchRepository orderServiceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderServiceServiceImpl(OrderServiceRepository orderServiceRepository, OrderServiceMapper orderServiceMapper, OrderServiceSearchRepository orderServiceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderServiceRepository = orderServiceRepository;
        this.orderServiceMapper = orderServiceMapper;
        this.orderServiceSearchRepository = orderServiceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a orderService.
     *
     * @param orderServiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderServiceDTO save(OrderServiceDTO orderServiceDTO) {
        log.debug("Request to save OrderService : {}", orderServiceDTO);
        OrderService orderService = orderServiceMapper.toEntity(orderServiceDTO);
        orderService = orderServiceRepository.save(orderService);
        OrderServiceDTO result = orderServiceMapper.toDto(orderService);
        orderServiceSearchRepository.save(orderService);
        return result;
    }

    /**
     * Get all the orderServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderServices");
        return orderServiceRepository.findAll(pageable)
            .map(orderServiceMapper::toDto);
    }

    /**
     * Get one orderService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderServiceDTO findOne(Long id) {
        log.debug("Request to get OrderService : {}", id);
        OrderService orderService = orderServiceRepository.findOne(id);
        return orderServiceMapper.toDto(orderService);
    }

    /**
     * Delete the orderService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderService : {}", id);
        orderServiceRepository.delete(id);
        orderServiceSearchRepository.delete(id);
    }

    /**
     * Search for the orderService corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderServiceDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderServices for query {}", query);
        Page<OrderService> result = orderServiceSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderServiceMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderServiceDTO> searchExample(OrderServiceSearchDTO searchDto, Pageable pageable) {
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
            Page<OrderService> orderServicePage= orderServiceSearchRepository.search(query);
            List<OrderServiceDTO> orderServiceList =  StreamSupport
            .stream(orderServicePage.spliterator(), false)
            .map(orderServiceMapper::toDto)
            .collect(Collectors.toList());
            orderServiceList.forEach(orderServiceDto -> {
            if(orderServiceDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderServiceDto.getCompanyId());
                orderServiceDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(orderServiceList,pageable,orderServicePage.getTotalElements());
        }
}
