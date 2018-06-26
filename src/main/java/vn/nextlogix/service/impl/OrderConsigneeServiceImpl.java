package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderConsigneeService;
import vn.nextlogix.domain.OrderConsignee;
import vn.nextlogix.repository.OrderConsigneeRepository;
import vn.nextlogix.repository.search.OrderConsigneeSearchRepository;
import vn.nextlogix.service.dto.OrderConsigneeDTO;
import vn.nextlogix.service.dto.OrderConsigneeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.Province;
import vn.nextlogix.service.mapper.OrderConsigneeMapper;
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

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderConsignee.
 */
@Service
@Transactional
public class OrderConsigneeServiceImpl implements OrderConsigneeService {

    private final Logger log = LoggerFactory.getLogger(OrderConsigneeServiceImpl.class);

    private final OrderConsigneeRepository orderConsigneeRepository;

    private final OrderConsigneeMapper orderConsigneeMapper;

    private final OrderConsigneeSearchRepository orderConsigneeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;


    public OrderConsigneeServiceImpl(OrderConsigneeRepository orderConsigneeRepository, OrderConsigneeMapper orderConsigneeMapper, OrderConsigneeSearchRepository orderConsigneeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
) {
        this.orderConsigneeRepository = orderConsigneeRepository;
        this.orderConsigneeMapper = orderConsigneeMapper;
        this.orderConsigneeSearchRepository = orderConsigneeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;

    }

    /**
     * Save a orderConsignee.
     *
     * @param orderConsigneeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderConsigneeDTO save(OrderConsigneeDTO orderConsigneeDTO) {
        log.debug("Request to save OrderConsignee : {}", orderConsigneeDTO);
        OrderConsignee orderConsignee = orderConsigneeMapper.toEntity(orderConsigneeDTO);
        orderConsignee = orderConsigneeRepository.save(orderConsignee);
        OrderConsigneeDTO result = orderConsigneeMapper.toDto(orderConsignee);
        orderConsigneeSearchRepository.save(orderConsignee);
        return result;
    }

    /**
     * Get all the orderConsignees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderConsigneeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderConsignees");
        return orderConsigneeRepository.findAll(pageable)
            .map(orderConsigneeMapper::toDto);
    }

    /**
     * Get one orderConsignee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderConsigneeDTO findOne(Long id) {
        log.debug("Request to get OrderConsignee : {}", id);
        OrderConsignee orderConsignee = orderConsigneeRepository.findOne(id);
        return orderConsigneeMapper.toDto(orderConsignee);
    }

    /**
     * Delete the orderConsignee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderConsignee : {}", id);
        orderConsigneeRepository.delete(id);
        orderConsigneeSearchRepository.delete(id);
    }

    /**
     * Search for the orderConsignee corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderConsigneeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderConsignees for query {}", query);
        Page<OrderConsignee> result = orderConsigneeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderConsigneeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderConsigneeDTO> searchExample(OrderConsigneeSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getAddress())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("address", "*"+searchDto.getAddress()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getConsigneePhone())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("consigneePhone", "*"+searchDto.getConsigneePhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getConsigneeName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("consigneeName", "*"+searchDto.getConsigneeName()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<OrderConsignee> orderConsigneePage= orderConsigneeSearchRepository.search(query);
            List<OrderConsigneeDTO> orderConsigneeList =  StreamSupport
            .stream(orderConsigneePage.spliterator(), false)
            .map(orderConsigneeMapper::toDto)
            .collect(Collectors.toList());
            orderConsigneeList.forEach(orderConsigneeDto -> {
            if(orderConsigneeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderConsigneeDto.getCompanyId());
                orderConsigneeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(orderConsigneeDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(orderConsigneeDto.getDistrictId());
                orderConsigneeDto.setDistrictDTO(districtMapper.toDto(district));
            }
            if(orderConsigneeDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(orderConsigneeDto.getProvinceId());
                orderConsigneeDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            });
            return new PageImpl<>(orderConsigneeList,pageable,orderConsigneePage.getTotalElements());
        }
}
