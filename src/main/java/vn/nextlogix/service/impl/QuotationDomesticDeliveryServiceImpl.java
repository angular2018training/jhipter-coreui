package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationDomesticDeliveryService;
import vn.nextlogix.domain.QuotationDomesticDelivery;


    import vn.nextlogix.repository.QuotationDomesticDeliveryRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationDomesticDeliverySearchRepository;
import vn.nextlogix.service.dto.QuotationDomesticDeliveryDTO;
import vn.nextlogix.service.dto.QuotationDomesticDeliverySearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.DistrictType;
    import vn.nextlogix.domain.Region;
    import vn.nextlogix.domain.Weight;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationDomesticDeliveryMapper;
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

    import vn.nextlogix.repository.search.WeightSearchRepository;
    import vn.nextlogix.service.mapper.WeightMapper;

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
 * Service Implementation for managing QuotationDomesticDelivery.
 */
@Service
@Transactional
public class QuotationDomesticDeliveryServiceImpl implements QuotationDomesticDeliveryService {

    private final Logger log = LoggerFactory.getLogger(QuotationDomesticDeliveryServiceImpl.class);

    private final QuotationDomesticDeliveryRepository quotationDomesticDeliveryRepository;

    private final QuotationDomesticDeliveryMapper quotationDomesticDeliveryMapper;

    private final QuotationDomesticDeliverySearchRepository quotationDomesticDeliverySearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final RegionSearchRepository regionSearchRepository;
        private final RegionMapper regionMapper;

        private final WeightSearchRepository weightSearchRepository;
        private final WeightMapper weightMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationDomesticDeliveryServiceImpl(QuotationDomesticDeliveryRepository quotationDomesticDeliveryRepository, QuotationDomesticDeliveryMapper quotationDomesticDeliveryMapper, QuotationDomesticDeliverySearchRepository quotationDomesticDeliverySearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,RegionSearchRepository regionSearchRepository,RegionMapper  regionMapper
,WeightSearchRepository weightSearchRepository,WeightMapper  weightMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationDomesticDeliveryRepository = quotationDomesticDeliveryRepository;
        this.quotationDomesticDeliveryMapper = quotationDomesticDeliveryMapper;
        this.quotationDomesticDeliverySearchRepository = quotationDomesticDeliverySearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.regionSearchRepository = regionSearchRepository;
                                     this.regionMapper = regionMapper;
                                    this.weightSearchRepository = weightSearchRepository;
                                     this.weightMapper = weightMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a quotationDomesticDelivery.
     *
     * @param quotationDomesticDeliveryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationDomesticDeliveryDTO save(QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO) {
        log.debug("Request to save QuotationDomesticDelivery : {}", quotationDomesticDeliveryDTO);
        QuotationDomesticDelivery quotationDomesticDelivery = quotationDomesticDeliveryMapper.toEntity(quotationDomesticDeliveryDTO);
        quotationDomesticDelivery = quotationDomesticDeliveryRepository.save(quotationDomesticDelivery);
        QuotationDomesticDeliveryDTO result = quotationDomesticDeliveryMapper.toDto(quotationDomesticDelivery);
        quotationDomesticDeliverySearchRepository.save(quotationDomesticDelivery);
        return result;
    }

    /**
     * Get all the quotationDomesticDeliveries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDomesticDeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationDomesticDeliveries");
        return quotationDomesticDeliveryRepository.findAll(pageable)
            .map(quotationDomesticDeliveryMapper::toDto);
    }

    /**
     * Get one quotationDomesticDelivery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationDomesticDeliveryDTO findOne(Long id) {
        log.debug("Request to get QuotationDomesticDelivery : {}", id);
        QuotationDomesticDelivery quotationDomesticDelivery = quotationDomesticDeliveryRepository.findOne(id);
        return quotationDomesticDeliveryMapper.toDto(quotationDomesticDelivery);
    }

    /**
     * Delete the quotationDomesticDelivery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationDomesticDelivery : {}", id);
        quotationDomesticDeliveryRepository.delete(id);
        quotationDomesticDeliverySearchRepository.delete(id);
    }

    /**
     * Search for the quotationDomesticDelivery corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDomesticDeliveryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationDomesticDeliveries for query {}", query);
        Page<QuotationDomesticDelivery> result = quotationDomesticDeliverySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationDomesticDeliveryMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDomesticDeliveryDTO> searchExample(QuotationDomesticDeliverySearchDTO searchDto, Pageable pageable) {
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
            if(searchDto.getWeightId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("weight.id", searchDto.getWeightId()));
            }
            if(searchDto.getQuotationParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("quotationParent.id", searchDto.getQuotationParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<QuotationDomesticDelivery> quotationDomesticDeliveryPage= quotationDomesticDeliverySearchRepository.search(query);
            List<QuotationDomesticDeliveryDTO> quotationDomesticDeliveryList =  StreamSupport
            .stream(quotationDomesticDeliveryPage.spliterator(), false)
            .map(quotationDomesticDeliveryMapper::toDto)
            .collect(Collectors.toList());
            quotationDomesticDeliveryList.forEach(quotationDomesticDeliveryDto -> {
            if(quotationDomesticDeliveryDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationDomesticDeliveryDto.getCompanyId());
                quotationDomesticDeliveryDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationDomesticDeliveryDto.getDistrictTypeId()!=null){
                DistrictType districtType= districtTypeSearchRepository.findOne(quotationDomesticDeliveryDto.getDistrictTypeId());
                quotationDomesticDeliveryDto.setDistrictTypeDTO(districtTypeMapper.toDto(districtType));
            }
            if(quotationDomesticDeliveryDto.getRegionId()!=null){
                Region region= regionSearchRepository.findOne(quotationDomesticDeliveryDto.getRegionId());
                quotationDomesticDeliveryDto.setRegionDTO(regionMapper.toDto(region));
            }
            if(quotationDomesticDeliveryDto.getWeightId()!=null){
                Weight weight= weightSearchRepository.findOne(quotationDomesticDeliveryDto.getWeightId());
                quotationDomesticDeliveryDto.setWeightDTO(weightMapper.toDto(weight));
            }
            if(quotationDomesticDeliveryDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationDomesticDeliveryDto.getQuotationParentId());
                quotationDomesticDeliveryDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationDomesticDeliveryList,pageable,quotationDomesticDeliveryPage.getTotalElements());
        }
}
