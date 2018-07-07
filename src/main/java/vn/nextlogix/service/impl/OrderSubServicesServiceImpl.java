package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderSubServicesService;
import vn.nextlogix.domain.OrderSubServices;


    import vn.nextlogix.repository.OrderSubServicesRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderSubServicesSearchRepository;
import vn.nextlogix.service.dto.OrderSubServicesDTO;
import vn.nextlogix.service.dto.OrderSubServicesSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.OrderSubServicesMapper;
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
 * Service Implementation for managing OrderSubServices.
 */
@Service
@Transactional
public class OrderSubServicesServiceImpl implements OrderSubServicesService {

    private final Logger log = LoggerFactory.getLogger(OrderSubServicesServiceImpl.class);

    private final OrderSubServicesRepository orderSubServicesRepository;

    private final OrderSubServicesMapper orderSubServicesMapper;

    private final OrderSubServicesSearchRepository orderSubServicesSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public OrderSubServicesServiceImpl(OrderSubServicesRepository orderSubServicesRepository, OrderSubServicesMapper orderSubServicesMapper, OrderSubServicesSearchRepository orderSubServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.orderSubServicesRepository = orderSubServicesRepository;
        this.orderSubServicesMapper = orderSubServicesMapper;
        this.orderSubServicesSearchRepository = orderSubServicesSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a orderSubServices.
     *
     * @param orderSubServicesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderSubServicesDTO save(OrderSubServicesDTO orderSubServicesDTO) {
        log.debug("Request to save OrderSubServices : {}", orderSubServicesDTO);
        OrderSubServices orderSubServices = orderSubServicesMapper.toEntity(orderSubServicesDTO);
        orderSubServices = orderSubServicesRepository.save(orderSubServices);
        OrderSubServicesDTO result = orderSubServicesMapper.toDto(orderSubServices);
        orderSubServicesSearchRepository.save(orderSubServices);
        return result;
    }

    /**
     * Get all the orderSubServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderSubServicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderSubServices");
        return orderSubServicesRepository.findAll(pageable)
            .map(orderSubServicesMapper::toDto);
    }

    /**
     * Get one orderSubServices by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderSubServicesDTO findOne(Long id) {
        log.debug("Request to get OrderSubServices : {}", id);
        OrderSubServices orderSubServices = orderSubServicesRepository.findOne(id);
        return orderSubServicesMapper.toDto(orderSubServices);
    }

    /**
     * Delete the orderSubServices by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderSubServices : {}", id);
        orderSubServicesRepository.delete(id);
        orderSubServicesSearchRepository.delete(id);
    }

    /**
     * Search for the orderSubServices corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderSubServicesDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderSubServices for query {}", query);
        Page<OrderSubServices> result = orderSubServicesSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderSubServicesMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderSubServicesDTO> searchExample(OrderSubServicesSearchDTO searchDto, Pageable pageable) {
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
            Page<OrderSubServices> orderSubServicesPage= orderSubServicesSearchRepository.search(query);
            List<OrderSubServicesDTO> orderSubServicesList =  StreamSupport
            .stream(orderSubServicesPage.spliterator(), false)
            .map(orderSubServicesMapper::toDto)
            .collect(Collectors.toList());
            orderSubServicesList.forEach(orderSubServicesDto -> {
            if(orderSubServicesDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderSubServicesDto.getCompanyId());
                orderSubServicesDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(orderSubServicesList,pageable,orderSubServicesPage.getTotalElements());
        }
}
