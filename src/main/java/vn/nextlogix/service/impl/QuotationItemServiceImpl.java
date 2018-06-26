package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationItemService;
import vn.nextlogix.domain.QuotationItem;
import vn.nextlogix.repository.QuotationItemRepository;
import vn.nextlogix.repository.search.QuotationItemSearchRepository;
import vn.nextlogix.service.dto.QuotationItemDTO;
import vn.nextlogix.service.dto.QuotationItemSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Quotation;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.QuotationItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.QuotationSearchRepository;
    import vn.nextlogix.service.mapper.QuotationMapper;

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
 * Service Implementation for managing QuotationItem.
 */
@Service
@Transactional
public class QuotationItemServiceImpl implements QuotationItemService {

    private final Logger log = LoggerFactory.getLogger(QuotationItemServiceImpl.class);

    private final QuotationItemRepository quotationItemRepository;

    private final QuotationItemMapper quotationItemMapper;

    private final QuotationItemSearchRepository quotationItemSearchRepository;


        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;

        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public QuotationItemServiceImpl(QuotationItemRepository quotationItemRepository, QuotationItemMapper quotationItemMapper, QuotationItemSearchRepository quotationItemSearchRepository     ,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.quotationItemRepository = quotationItemRepository;
        this.quotationItemMapper = quotationItemMapper;
        this.quotationItemSearchRepository = quotationItemSearchRepository;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a quotationItem.
     *
     * @param quotationItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationItemDTO save(QuotationItemDTO quotationItemDTO) {
        log.debug("Request to save QuotationItem : {}", quotationItemDTO);
        QuotationItem quotationItem = quotationItemMapper.toEntity(quotationItemDTO);
        quotationItem = quotationItemRepository.save(quotationItem);
        QuotationItemDTO result = quotationItemMapper.toDto(quotationItem);
        quotationItemSearchRepository.save(quotationItem);
        return result;
    }

    /**
     * Get all the quotationItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationItems");
        return quotationItemRepository.findAll(pageable)
            .map(quotationItemMapper::toDto);
    }

    /**
     * Get one quotationItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationItemDTO findOne(Long id) {
        log.debug("Request to get QuotationItem : {}", id);
        QuotationItem quotationItem = quotationItemRepository.findOne(id);
        return quotationItemMapper.toDto(quotationItem);
    }

    /**
     * Delete the quotationItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationItem : {}", id);
        quotationItemRepository.delete(id);
        quotationItemSearchRepository.delete(id);
    }

    /**
     * Search for the quotationItem corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationItemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationItems for query {}", query);
        Page<QuotationItem> result = quotationItemSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationItemMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationItemDTO> searchExample(QuotationItemSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<QuotationItem> quotationItemPage= quotationItemSearchRepository.search(query);
            List<QuotationItemDTO> quotationItemList =  StreamSupport
            .stream(quotationItemPage.spliterator(), false)
            .map(quotationItemMapper::toDto)
            .collect(Collectors.toList());
            quotationItemList.forEach(quotationItemDto -> {
            if(quotationItemDto.getQuotationId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationItemDto.getQuotationId());
                quotationItemDto.setQuotationDTO(quotationMapper.toDto(quotation));
            }
            if(quotationItemDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationItemDto.getCompanyId());
                quotationItemDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(quotationItemList,pageable,quotationItemPage.getTotalElements());
        }
}
