package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationItemTypeService;
import vn.nextlogix.domain.QuotationItemType;
import vn.nextlogix.repository.QuotationItemTypeRepository;
import vn.nextlogix.repository.search.QuotationItemTypeSearchRepository;
import vn.nextlogix.service.dto.QuotationItemTypeDTO;
import vn.nextlogix.service.dto.QuotationItemTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.QuotationItemTypeMapper;
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
 * Service Implementation for managing QuotationItemType.
 */
@Service
@Transactional
public class QuotationItemTypeServiceImpl implements QuotationItemTypeService {

    private final Logger log = LoggerFactory.getLogger(QuotationItemTypeServiceImpl.class);

    private final QuotationItemTypeRepository quotationItemTypeRepository;

    private final QuotationItemTypeMapper quotationItemTypeMapper;

    private final QuotationItemTypeSearchRepository quotationItemTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public QuotationItemTypeServiceImpl(QuotationItemTypeRepository quotationItemTypeRepository, QuotationItemTypeMapper quotationItemTypeMapper, QuotationItemTypeSearchRepository quotationItemTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.quotationItemTypeRepository = quotationItemTypeRepository;
        this.quotationItemTypeMapper = quotationItemTypeMapper;
        this.quotationItemTypeSearchRepository = quotationItemTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a quotationItemType.
     *
     * @param quotationItemTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationItemTypeDTO save(QuotationItemTypeDTO quotationItemTypeDTO) {
        log.debug("Request to save QuotationItemType : {}", quotationItemTypeDTO);
        QuotationItemType quotationItemType = quotationItemTypeMapper.toEntity(quotationItemTypeDTO);
        quotationItemType = quotationItemTypeRepository.save(quotationItemType);
        QuotationItemTypeDTO result = quotationItemTypeMapper.toDto(quotationItemType);
        quotationItemTypeSearchRepository.save(quotationItemType);
        return result;
    }

    /**
     * Get all the quotationItemTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationItemTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationItemTypes");
        return quotationItemTypeRepository.findAll(pageable)
            .map(quotationItemTypeMapper::toDto);
    }

    /**
     * Get one quotationItemType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationItemTypeDTO findOne(Long id) {
        log.debug("Request to get QuotationItemType : {}", id);
        QuotationItemType quotationItemType = quotationItemTypeRepository.findOne(id);
        return quotationItemTypeMapper.toDto(quotationItemType);
    }

    /**
     * Delete the quotationItemType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationItemType : {}", id);
        quotationItemTypeRepository.delete(id);
        quotationItemTypeSearchRepository.delete(id);
    }

    /**
     * Search for the quotationItemType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationItemTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationItemTypes for query {}", query);
        Page<QuotationItemType> result = quotationItemTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationItemTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationItemTypeDTO> searchExample(QuotationItemTypeSearchDTO searchDto, Pageable pageable) {
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
            Page<QuotationItemType> quotationItemTypePage= quotationItemTypeSearchRepository.search(query);
            List<QuotationItemTypeDTO> quotationItemTypeList =  StreamSupport
            .stream(quotationItemTypePage.spliterator(), false)
            .map(quotationItemTypeMapper::toDto)
            .collect(Collectors.toList());
            quotationItemTypeList.forEach(quotationItemTypeDto -> {
            if(quotationItemTypeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationItemTypeDto.getCompanyId());
                quotationItemTypeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(quotationItemTypeList,pageable,quotationItemTypePage.getTotalElements());
        }
}
