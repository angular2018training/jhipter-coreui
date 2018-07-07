package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationTypeService;
import vn.nextlogix.domain.QuotationType;


    import vn.nextlogix.repository.QuotationTypeRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationTypeSearchRepository;
import vn.nextlogix.service.dto.QuotationTypeDTO;
import vn.nextlogix.service.dto.QuotationTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
import vn.nextlogix.service.mapper.QuotationTypeMapper;
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
 * Service Implementation for managing QuotationType.
 */
@Service
@Transactional
public class QuotationTypeServiceImpl implements QuotationTypeService {

    private final Logger log = LoggerFactory.getLogger(QuotationTypeServiceImpl.class);

    private final QuotationTypeRepository quotationTypeRepository;

    private final QuotationTypeMapper quotationTypeMapper;

    private final QuotationTypeSearchRepository quotationTypeSearchRepository;



    public QuotationTypeServiceImpl(QuotationTypeRepository quotationTypeRepository, QuotationTypeMapper quotationTypeMapper, QuotationTypeSearchRepository quotationTypeSearchRepository     ) {
        this.quotationTypeRepository = quotationTypeRepository;
        this.quotationTypeMapper = quotationTypeMapper;
        this.quotationTypeSearchRepository = quotationTypeSearchRepository;

    }

    /**
     * Save a quotationType.
     *
     * @param quotationTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationTypeDTO save(QuotationTypeDTO quotationTypeDTO) {
        log.debug("Request to save QuotationType : {}", quotationTypeDTO);
        QuotationType quotationType = quotationTypeMapper.toEntity(quotationTypeDTO);
        quotationType = quotationTypeRepository.save(quotationType);
        QuotationTypeDTO result = quotationTypeMapper.toDto(quotationType);
        quotationTypeSearchRepository.save(quotationType);
        return result;
    }

    /**
     * Get all the quotationTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationTypes");
        return quotationTypeRepository.findAll(pageable)
            .map(quotationTypeMapper::toDto);
    }

    /**
     * Get one quotationType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationTypeDTO findOne(Long id) {
        log.debug("Request to get QuotationType : {}", id);
        QuotationType quotationType = quotationTypeRepository.findOne(id);
        return quotationTypeMapper.toDto(quotationType);
    }

    /**
     * Delete the quotationType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationType : {}", id);
        quotationTypeRepository.delete(id);
        quotationTypeSearchRepository.delete(id);
    }

    /**
     * Search for the quotationType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationTypes for query {}", query);
        Page<QuotationType> result = quotationTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationTypeDTO> searchExample(QuotationTypeSearchDTO searchDto, Pageable pageable) {
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
            Page<QuotationType> quotationTypePage= quotationTypeSearchRepository.search(query);
            List<QuotationTypeDTO> quotationTypeList =  StreamSupport
            .stream(quotationTypePage.spliterator(), false)
            .map(quotationTypeMapper::toDto)
            .collect(Collectors.toList());
            quotationTypeList.forEach(quotationTypeDto -> {
            });
            return new PageImpl<>(quotationTypeList,pageable,quotationTypePage.getTotalElements());
        }
}
