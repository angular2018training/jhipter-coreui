package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationReturnService;
import vn.nextlogix.domain.QuotationReturn;


    import vn.nextlogix.repository.QuotationReturnRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationReturnSearchRepository;
import vn.nextlogix.service.dto.QuotationReturnDTO;
import vn.nextlogix.service.dto.QuotationReturnSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.DistrictType;
    import vn.nextlogix.domain.Region;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationReturnMapper;
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
 * Service Implementation for managing QuotationReturn.
 */
@Service
@Transactional
public class QuotationReturnServiceImpl implements QuotationReturnService {

    private final Logger log = LoggerFactory.getLogger(QuotationReturnServiceImpl.class);

    private final QuotationReturnRepository quotationReturnRepository;

    private final QuotationReturnMapper quotationReturnMapper;

    private final QuotationReturnSearchRepository quotationReturnSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final RegionSearchRepository regionSearchRepository;
        private final RegionMapper regionMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationReturnServiceImpl(QuotationReturnRepository quotationReturnRepository, QuotationReturnMapper quotationReturnMapper, QuotationReturnSearchRepository quotationReturnSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,RegionSearchRepository regionSearchRepository,RegionMapper  regionMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationReturnRepository = quotationReturnRepository;
        this.quotationReturnMapper = quotationReturnMapper;
        this.quotationReturnSearchRepository = quotationReturnSearchRepository;
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
     * Save a quotationReturn.
     *
     * @param quotationReturnDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationReturnDTO save(QuotationReturnDTO quotationReturnDTO) {
        log.debug("Request to save QuotationReturn : {}", quotationReturnDTO);
        QuotationReturn quotationReturn = quotationReturnMapper.toEntity(quotationReturnDTO);
        quotationReturn = quotationReturnRepository.save(quotationReturn);
        QuotationReturnDTO result = quotationReturnMapper.toDto(quotationReturn);
        quotationReturnSearchRepository.save(quotationReturn);
        return result;
    }

    /**
     * Get all the quotationReturns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationReturnDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationReturns");
        return quotationReturnRepository.findAll(pageable)
            .map(quotationReturnMapper::toDto);
    }

    /**
     * Get one quotationReturn by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationReturnDTO findOne(Long id) {
        log.debug("Request to get QuotationReturn : {}", id);
        QuotationReturn quotationReturn = quotationReturnRepository.findOne(id);
        return quotationReturnMapper.toDto(quotationReturn);
    }

    /**
     * Delete the quotationReturn by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationReturn : {}", id);
        quotationReturnRepository.delete(id);
        quotationReturnSearchRepository.delete(id);
    }

    /**
     * Search for the quotationReturn corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationReturnDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationReturns for query {}", query);
        Page<QuotationReturn> result = quotationReturnSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationReturnMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationReturnDTO> searchExample(QuotationReturnSearchDTO searchDto, Pageable pageable) {
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
            Page<QuotationReturn> quotationReturnPage= quotationReturnSearchRepository.search(query);
            List<QuotationReturnDTO> quotationReturnList =  StreamSupport
            .stream(quotationReturnPage.spliterator(), false)
            .map(quotationReturnMapper::toDto)
            .collect(Collectors.toList());
            quotationReturnList.forEach(quotationReturnDto -> {
            if(quotationReturnDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationReturnDto.getCompanyId());
                quotationReturnDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationReturnDto.getDistrictTypeId()!=null){
                DistrictType districtType= districtTypeSearchRepository.findOne(quotationReturnDto.getDistrictTypeId());
                quotationReturnDto.setDistrictTypeDTO(districtTypeMapper.toDto(districtType));
            }
            if(quotationReturnDto.getRegionId()!=null){
                Region region= regionSearchRepository.findOne(quotationReturnDto.getRegionId());
                quotationReturnDto.setRegionDTO(regionMapper.toDto(region));
            }
            if(quotationReturnDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationReturnDto.getQuotationParentId());
                quotationReturnDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationReturnList,pageable,quotationReturnPage.getTotalElements());
        }
}
