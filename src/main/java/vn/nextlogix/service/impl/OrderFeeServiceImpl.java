package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderFeeService;
import vn.nextlogix.domain.OrderFee;
import vn.nextlogix.repository.OrderFeeRepository;
import vn.nextlogix.repository.search.OrderFeeSearchRepository;
import vn.nextlogix.service.dto.OrderFeeDTO;
import vn.nextlogix.service.dto.OrderFeeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.OrderFeeMapper;
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

    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderFee.
 */
@Service
@Transactional
public class OrderFeeServiceImpl implements OrderFeeService {

    private final Logger log = LoggerFactory.getLogger(OrderFeeServiceImpl.class);

    private final OrderFeeRepository orderFeeRepository;

    private final OrderFeeMapper orderFeeMapper;

    private final OrderFeeSearchRepository orderFeeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public OrderFeeServiceImpl(OrderFeeRepository orderFeeRepository, OrderFeeMapper orderFeeMapper, OrderFeeSearchRepository orderFeeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.orderFeeRepository = orderFeeRepository;
        this.orderFeeMapper = orderFeeMapper;
        this.orderFeeSearchRepository = orderFeeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a orderFee.
     *
     * @param orderFeeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderFeeDTO save(OrderFeeDTO orderFeeDTO) {
        log.debug("Request to save OrderFee : {}", orderFeeDTO);
        OrderFee orderFee = orderFeeMapper.toEntity(orderFeeDTO);
        orderFee = orderFeeRepository.save(orderFee);
        OrderFeeDTO result = orderFeeMapper.toDto(orderFee);
        orderFeeSearchRepository.save(orderFee);
        return result;
    }

    /**
     * Get all the orderFees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderFeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderFees");
        return orderFeeRepository.findAll(pageable)
            .map(orderFeeMapper::toDto);
    }

    /**
     * Get one orderFee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderFeeDTO findOne(Long id) {
        log.debug("Request to get OrderFee : {}", id);
        OrderFee orderFee = orderFeeRepository.findOne(id);
        return orderFeeMapper.toDto(orderFee);
    }

    /**
     * Delete the orderFee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderFee : {}", id);
        orderFeeRepository.delete(id);
        orderFeeSearchRepository.delete(id);
    }

    /**
     * Search for the orderFee corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderFeeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderFees for query {}", query);
        Page<OrderFee> result = orderFeeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderFeeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<OrderFeeDTO> searchExample(OrderFeeSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<OrderFee> orderFeePage= orderFeeSearchRepository.search(query);
            List<OrderFeeDTO> orderFeeList =  StreamSupport
            .stream(orderFeePage.spliterator(), false)
            .map(orderFeeMapper::toDto)
            .collect(Collectors.toList());
            orderFeeList.forEach(orderFeeDto -> {
            if(orderFeeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(orderFeeDto.getCompanyId());
                orderFeeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(orderFeeDto.getQuotationId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(orderFeeDto.getQuotationId());
                orderFeeDto.setQuotationDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(orderFeeList,pageable,orderFeePage.getTotalElements());
        }
}
