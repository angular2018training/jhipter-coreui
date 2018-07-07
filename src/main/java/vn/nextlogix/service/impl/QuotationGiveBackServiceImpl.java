package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationGiveBackService;
import vn.nextlogix.domain.QuotationGiveBack;


    import vn.nextlogix.repository.QuotationGiveBackRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationGiveBackSearchRepository;
import vn.nextlogix.service.dto.QuotationGiveBackDTO;
import vn.nextlogix.service.dto.QuotationGiveBackSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.DistrictType;
    import vn.nextlogix.domain.Region;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationGiveBackMapper;
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

    import vn.nextlogix.repository.search.RegionSearchRepository;
    import vn.nextlogix.service.mapper.RegionMapper;

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
 * Service Implementation for managing QuotationGiveBack.
 */
@Service
@Transactional
public class QuotationGiveBackServiceImpl implements QuotationGiveBackService {

    private final Logger log = LoggerFactory.getLogger(QuotationGiveBackServiceImpl.class);

    private final QuotationGiveBackRepository quotationGiveBackRepository;

    private final QuotationGiveBackMapper quotationGiveBackMapper;

    private final QuotationGiveBackSearchRepository quotationGiveBackSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final RegionSearchRepository regionSearchRepository;
        private final RegionMapper regionMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationGiveBackServiceImpl(QuotationGiveBackRepository quotationGiveBackRepository, QuotationGiveBackMapper quotationGiveBackMapper, QuotationGiveBackSearchRepository quotationGiveBackSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,RegionSearchRepository regionSearchRepository,RegionMapper  regionMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationGiveBackRepository = quotationGiveBackRepository;
        this.quotationGiveBackMapper = quotationGiveBackMapper;
        this.quotationGiveBackSearchRepository = quotationGiveBackSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.regionSearchRepository = regionSearchRepository;
                                     this.regionMapper = regionMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a quotationGiveBack.
     *
     * @param quotationGiveBackDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationGiveBackDTO save(QuotationGiveBackDTO quotationGiveBackDTO) {
        log.debug("Request to save QuotationGiveBack : {}", quotationGiveBackDTO);
        QuotationGiveBack quotationGiveBack = quotationGiveBackMapper.toEntity(quotationGiveBackDTO);
        quotationGiveBack = quotationGiveBackRepository.save(quotationGiveBack);
        QuotationGiveBackDTO result = quotationGiveBackMapper.toDto(quotationGiveBack);
        quotationGiveBackSearchRepository.save(quotationGiveBack);
        return result;
    }

    /**
     * Get all the quotationGiveBacks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationGiveBackDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationGiveBacks");
        return quotationGiveBackRepository.findAll(pageable)
            .map(quotationGiveBackMapper::toDto);
    }

    /**
     * Get one quotationGiveBack by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationGiveBackDTO findOne(Long id) {
        log.debug("Request to get QuotationGiveBack : {}", id);
        QuotationGiveBack quotationGiveBack = quotationGiveBackRepository.findOne(id);
        return quotationGiveBackMapper.toDto(quotationGiveBack);
    }

    /**
     * Delete the quotationGiveBack by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationGiveBack : {}", id);
        quotationGiveBackRepository.delete(id);
        quotationGiveBackSearchRepository.delete(id);
    }

    /**
     * Search for the quotationGiveBack corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationGiveBackDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationGiveBacks for query {}", query);
        Page<QuotationGiveBack> result = quotationGiveBackSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationGiveBackMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationGiveBackDTO> searchExample(QuotationGiveBackSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getDistrictTypeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("districtType.id", searchDto.getDistrictTypeId()));
            }
            if(searchDto.getRegionId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("region.id", searchDto.getRegionId()));
            }
            if(searchDto.getQuotationParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotationParent.id", searchDto.getQuotationParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<QuotationGiveBack> quotationGiveBackPage= quotationGiveBackSearchRepository.search(query);
            List<QuotationGiveBackDTO> quotationGiveBackList =  StreamSupport
            .stream(quotationGiveBackPage.spliterator(), false)
            .map(quotationGiveBackMapper::toDto)
            .collect(Collectors.toList());
            quotationGiveBackList.forEach(quotationGiveBackDto -> {
            if(quotationGiveBackDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationGiveBackDto.getCompanyId());
                quotationGiveBackDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationGiveBackDto.getDistrictTypeId()!=null){
                DistrictType districtType= districtTypeSearchRepository.findOne(quotationGiveBackDto.getDistrictTypeId());
                quotationGiveBackDto.setDistrictTypeDTO(districtTypeMapper.toDto(districtType));
            }
            if(quotationGiveBackDto.getRegionId()!=null){
                Region region= regionSearchRepository.findOne(quotationGiveBackDto.getRegionId());
                quotationGiveBackDto.setRegionDTO(regionMapper.toDto(region));
            }
            if(quotationGiveBackDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationGiveBackDto.getQuotationParentId());
                quotationGiveBackDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationGiveBackList,pageable,quotationGiveBackPage.getTotalElements());
        }
}
