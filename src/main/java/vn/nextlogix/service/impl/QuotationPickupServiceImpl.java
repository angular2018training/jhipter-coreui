package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationPickupService;
import vn.nextlogix.domain.QuotationPickup;


    import vn.nextlogix.repository.QuotationPickupRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationPickupSearchRepository;
import vn.nextlogix.service.dto.QuotationPickupDTO;
import vn.nextlogix.service.dto.QuotationPickupSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.DistrictType;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationPickupMapper;
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

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

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
 * Service Implementation for managing QuotationPickup.
 */
@Service
@Transactional
public class QuotationPickupServiceImpl implements QuotationPickupService {

    private final Logger log = LoggerFactory.getLogger(QuotationPickupServiceImpl.class);

    private final QuotationPickupRepository quotationPickupRepository;

    private final QuotationPickupMapper quotationPickupMapper;

    private final QuotationPickupSearchRepository quotationPickupSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationPickupServiceImpl(QuotationPickupRepository quotationPickupRepository, QuotationPickupMapper quotationPickupMapper, QuotationPickupSearchRepository quotationPickupSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationPickupRepository = quotationPickupRepository;
        this.quotationPickupMapper = quotationPickupMapper;
        this.quotationPickupSearchRepository = quotationPickupSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a quotationPickup.
     *
     * @param quotationPickupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationPickupDTO save(QuotationPickupDTO quotationPickupDTO) {
        log.debug("Request to save QuotationPickup : {}", quotationPickupDTO);
        QuotationPickup quotationPickup = quotationPickupMapper.toEntity(quotationPickupDTO);
        quotationPickup = quotationPickupRepository.save(quotationPickup);
        QuotationPickupDTO result = quotationPickupMapper.toDto(quotationPickup);
        quotationPickupSearchRepository.save(quotationPickup);
        return result;
    }

    /**
     * Get all the quotationPickups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationPickupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationPickups");
        return quotationPickupRepository.findAll(pageable)
            .map(quotationPickupMapper::toDto);
    }

    /**
     * Get one quotationPickup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationPickupDTO findOne(Long id) {
        log.debug("Request to get QuotationPickup : {}", id);
        QuotationPickup quotationPickup = quotationPickupRepository.findOne(id);
        return quotationPickupMapper.toDto(quotationPickup);
    }

    /**
     * Delete the quotationPickup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationPickup : {}", id);
        quotationPickupRepository.delete(id);
        quotationPickupSearchRepository.delete(id);
    }

    /**
     * Search for the quotationPickup corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationPickupDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationPickups for query {}", query);
        Page<QuotationPickup> result = quotationPickupSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationPickupMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationPickupDTO> searchExample(QuotationPickupSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getProvinceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("province.id", searchDto.getProvinceId()));
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
            Page<QuotationPickup> quotationPickupPage= quotationPickupSearchRepository.search(query);
            List<QuotationPickupDTO> quotationPickupList =  StreamSupport
            .stream(quotationPickupPage.spliterator(), false)
            .map(quotationPickupMapper::toDto)
            .collect(Collectors.toList());
            quotationPickupList.forEach(quotationPickupDto -> {
            if(quotationPickupDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationPickupDto.getCompanyId());
                quotationPickupDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationPickupDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(quotationPickupDto.getProvinceId());
                quotationPickupDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(quotationPickupDto.getDistrictTypeId()!=null){
                DistrictType districtType= districtTypeSearchRepository.findOne(quotationPickupDto.getDistrictTypeId());
                quotationPickupDto.setDistrictTypeDTO(districtTypeMapper.toDto(districtType));
            }
            if(quotationPickupDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationPickupDto.getQuotationParentId());
                quotationPickupDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationPickupList,pageable,quotationPickupPage.getTotalElements());
        }
}
