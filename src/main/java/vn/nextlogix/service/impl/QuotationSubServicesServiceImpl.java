package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationSubServicesService;
import vn.nextlogix.domain.QuotationSubServices;


    import vn.nextlogix.repository.QuotationSubServicesRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationSubServicesSearchRepository;
import vn.nextlogix.service.dto.QuotationSubServicesDTO;
import vn.nextlogix.service.dto.QuotationSubServicesSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.OrderSubServicesType;
    import vn.nextlogix.domain.OrderSubServices;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationSubServicesMapper;
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

    import vn.nextlogix.repository.search.OrderSubServicesTypeSearchRepository;
    import vn.nextlogix.service.mapper.OrderSubServicesTypeMapper;

    import vn.nextlogix.repository.search.OrderSubServicesSearchRepository;
    import vn.nextlogix.service.mapper.OrderSubServicesMapper;

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
 * Service Implementation for managing QuotationSubServices.
 */
@Service
@Transactional
public class QuotationSubServicesServiceImpl implements QuotationSubServicesService {

    private final Logger log = LoggerFactory.getLogger(QuotationSubServicesServiceImpl.class);

    private final QuotationSubServicesRepository quotationSubServicesRepository;

    private final QuotationSubServicesMapper quotationSubServicesMapper;

    private final QuotationSubServicesSearchRepository quotationSubServicesSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository;
        private final OrderSubServicesTypeMapper orderSubServicesTypeMapper;

        private final OrderSubServicesSearchRepository orderSubServicesSearchRepository;
        private final OrderSubServicesMapper orderSubServicesMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationSubServicesServiceImpl(QuotationSubServicesRepository quotationSubServicesRepository, QuotationSubServicesMapper quotationSubServicesMapper, QuotationSubServicesSearchRepository quotationSubServicesSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository,OrderSubServicesTypeMapper  orderSubServicesTypeMapper
,OrderSubServicesSearchRepository orderSubServicesSearchRepository,OrderSubServicesMapper  orderSubServicesMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationSubServicesRepository = quotationSubServicesRepository;
        this.quotationSubServicesMapper = quotationSubServicesMapper;
        this.quotationSubServicesSearchRepository = quotationSubServicesSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.orderSubServicesTypeSearchRepository = orderSubServicesTypeSearchRepository;
                                     this.orderSubServicesTypeMapper = orderSubServicesTypeMapper;
                                    this.orderSubServicesSearchRepository = orderSubServicesSearchRepository;
                                     this.orderSubServicesMapper = orderSubServicesMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a quotationSubServices.
     *
     * @param quotationSubServicesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationSubServicesDTO save(QuotationSubServicesDTO quotationSubServicesDTO) {
        log.debug("Request to save QuotationSubServices : {}", quotationSubServicesDTO);
        QuotationSubServices quotationSubServices = quotationSubServicesMapper.toEntity(quotationSubServicesDTO);
        quotationSubServices = quotationSubServicesRepository.save(quotationSubServices);
        QuotationSubServicesDTO result = quotationSubServicesMapper.toDto(quotationSubServices);
        quotationSubServicesSearchRepository.save(quotationSubServices);
        return result;
    }

    /**
     * Get all the quotationSubServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationSubServicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationSubServices");
        return quotationSubServicesRepository.findAll(pageable)
            .map(quotationSubServicesMapper::toDto);
    }

    /**
     * Get one quotationSubServices by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationSubServicesDTO findOne(Long id) {
        log.debug("Request to get QuotationSubServices : {}", id);
        QuotationSubServices quotationSubServices = quotationSubServicesRepository.findOne(id);
        return quotationSubServicesMapper.toDto(quotationSubServices);
    }

    /**
     * Delete the quotationSubServices by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationSubServices : {}", id);
        quotationSubServicesRepository.delete(id);
        quotationSubServicesSearchRepository.delete(id);
    }

    /**
     * Search for the quotationSubServices corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationSubServicesDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationSubServices for query {}", query);
        Page<QuotationSubServices> result = quotationSubServicesSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationSubServicesMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationSubServicesDTO> searchExample(QuotationSubServicesSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getOrderSubServicesTypeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderSubServicesType.id", searchDto.getOrderSubServicesTypeId()));
            }
            if(searchDto.getOrderSubServicesId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("orderSubServices.id", searchDto.getOrderSubServicesId()));
            }
            if(searchDto.getQuotationParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotationParent.id", searchDto.getQuotationParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<QuotationSubServices> quotationSubServicesPage= quotationSubServicesSearchRepository.search(query);
            List<QuotationSubServicesDTO> quotationSubServicesList =  StreamSupport
            .stream(quotationSubServicesPage.spliterator(), false)
            .map(quotationSubServicesMapper::toDto)
            .collect(Collectors.toList());
            quotationSubServicesList.forEach(quotationSubServicesDto -> {
            if(quotationSubServicesDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationSubServicesDto.getCompanyId());
                quotationSubServicesDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationSubServicesDto.getOrderSubServicesTypeId()!=null){
                OrderSubServicesType orderSubServicesType= orderSubServicesTypeSearchRepository.findOne(quotationSubServicesDto.getOrderSubServicesTypeId());
                quotationSubServicesDto.setOrderSubServicesTypeDTO(orderSubServicesTypeMapper.toDto(orderSubServicesType));
            }
            if(quotationSubServicesDto.getOrderSubServicesId()!=null){
                OrderSubServices orderSubServices= orderSubServicesSearchRepository.findOne(quotationSubServicesDto.getOrderSubServicesId());
                quotationSubServicesDto.setOrderSubServicesDTO(orderSubServicesMapper.toDto(orderSubServices));
            }
            if(quotationSubServicesDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationSubServicesDto.getQuotationParentId());
                quotationSubServicesDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationSubServicesList,pageable,quotationSubServicesPage.getTotalElements());
        }
}
