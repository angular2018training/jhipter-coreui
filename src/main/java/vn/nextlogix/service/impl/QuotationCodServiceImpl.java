package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationCodService;
import vn.nextlogix.domain.QuotationCod;


    import vn.nextlogix.repository.QuotationCodRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationCodSearchRepository;
import vn.nextlogix.service.dto.QuotationCodDTO;
import vn.nextlogix.service.dto.QuotationCodSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.DistrictType;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationCodMapper;
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

    import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
    import vn.nextlogix.service.mapper.DistrictTypeMapper;

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
 * Service Implementation for managing QuotationCod.
 */
@Service
@Transactional
public class QuotationCodServiceImpl implements QuotationCodService {

    private final Logger log = LoggerFactory.getLogger(QuotationCodServiceImpl.class);

    private final QuotationCodRepository quotationCodRepository;

    private final QuotationCodMapper quotationCodMapper;

    private final QuotationCodSearchRepository quotationCodSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationCodServiceImpl(QuotationCodRepository quotationCodRepository, QuotationCodMapper quotationCodMapper, QuotationCodSearchRepository quotationCodSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationCodRepository = quotationCodRepository;
        this.quotationCodMapper = quotationCodMapper;
        this.quotationCodSearchRepository = quotationCodSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a quotationCod.
     *
     * @param quotationCodDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationCodDTO save(QuotationCodDTO quotationCodDTO) {
        log.debug("Request to save QuotationCod : {}", quotationCodDTO);
        QuotationCod quotationCod = quotationCodMapper.toEntity(quotationCodDTO);
        quotationCod = quotationCodRepository.save(quotationCod);
        QuotationCodDTO result = quotationCodMapper.toDto(quotationCod);
        quotationCodSearchRepository.save(quotationCod);
        return result;
    }

    /**
     * Get all the quotationCods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationCodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationCods");
        return quotationCodRepository.findAll(pageable)
            .map(quotationCodMapper::toDto);
    }

    /**
     * Get one quotationCod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationCodDTO findOne(Long id) {
        log.debug("Request to get QuotationCod : {}", id);
        QuotationCod quotationCod = quotationCodRepository.findOne(id);
        return quotationCodMapper.toDto(quotationCod);
    }

    /**
     * Delete the quotationCod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationCod : {}", id);
        quotationCodRepository.delete(id);
        quotationCodSearchRepository.delete(id);
    }

    /**
     * Search for the quotationCod corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationCodDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationCods for query {}", query);
        Page<QuotationCod> result = quotationCodSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationCodMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationCodDTO> searchExample(QuotationCodSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getDistrictTypeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("districtType.id", searchDto.getDistrictTypeId()));
            }
            if(searchDto.getQuotationParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotationParent.id", searchDto.getQuotationParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<QuotationCod> quotationCodPage= quotationCodSearchRepository.search(query);
            List<QuotationCodDTO> quotationCodList =  StreamSupport
            .stream(quotationCodPage.spliterator(), false)
            .map(quotationCodMapper::toDto)
            .collect(Collectors.toList());
            quotationCodList.forEach(quotationCodDto -> {
            if(quotationCodDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationCodDto.getCompanyId());
                quotationCodDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationCodDto.getDistrictTypeId()!=null){
                DistrictType districtType= districtTypeSearchRepository.findOne(quotationCodDto.getDistrictTypeId());
                quotationCodDto.setDistrictTypeDTO(districtTypeMapper.toDto(districtType));
            }
            if(quotationCodDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationCodDto.getQuotationParentId());
                quotationCodDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationCodList,pageable,quotationCodPage.getTotalElements());
        }
}
