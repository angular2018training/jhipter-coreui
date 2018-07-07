package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationInsuranceService;
import vn.nextlogix.domain.QuotationInsurance;


    import vn.nextlogix.repository.QuotationInsuranceRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationInsuranceSearchRepository;
import vn.nextlogix.service.dto.QuotationInsuranceDTO;
import vn.nextlogix.service.dto.QuotationInsuranceSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.DistrictType;
    import vn.nextlogix.domain.Quotation;
import vn.nextlogix.service.mapper.QuotationInsuranceMapper;
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
 * Service Implementation for managing QuotationInsurance.
 */
@Service
@Transactional
public class QuotationInsuranceServiceImpl implements QuotationInsuranceService {

    private final Logger log = LoggerFactory.getLogger(QuotationInsuranceServiceImpl.class);

    private final QuotationInsuranceRepository quotationInsuranceRepository;

    private final QuotationInsuranceMapper quotationInsuranceMapper;

    private final QuotationInsuranceSearchRepository quotationInsuranceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictTypeSearchRepository districtTypeSearchRepository;
        private final DistrictTypeMapper districtTypeMapper;

        private final QuotationSearchRepository quotationSearchRepository;
        private final QuotationMapper quotationMapper;


    public QuotationInsuranceServiceImpl(QuotationInsuranceRepository quotationInsuranceRepository, QuotationInsuranceMapper quotationInsuranceMapper, QuotationInsuranceSearchRepository quotationInsuranceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictTypeSearchRepository districtTypeSearchRepository,DistrictTypeMapper  districtTypeMapper
,QuotationSearchRepository quotationSearchRepository,QuotationMapper  quotationMapper
) {
        this.quotationInsuranceRepository = quotationInsuranceRepository;
        this.quotationInsuranceMapper = quotationInsuranceMapper;
        this.quotationInsuranceSearchRepository = quotationInsuranceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtTypeSearchRepository = districtTypeSearchRepository;
                                     this.districtTypeMapper = districtTypeMapper;
                                    this.quotationSearchRepository = quotationSearchRepository;
                                     this.quotationMapper = quotationMapper;

    }

    /**
     * Save a quotationInsurance.
     *
     * @param quotationInsuranceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationInsuranceDTO save(QuotationInsuranceDTO quotationInsuranceDTO) {
        log.debug("Request to save QuotationInsurance : {}", quotationInsuranceDTO);
        QuotationInsurance quotationInsurance = quotationInsuranceMapper.toEntity(quotationInsuranceDTO);
        quotationInsurance = quotationInsuranceRepository.save(quotationInsurance);
        QuotationInsuranceDTO result = quotationInsuranceMapper.toDto(quotationInsurance);
        quotationInsuranceSearchRepository.save(quotationInsurance);
        return result;
    }

    /**
     * Get all the quotationInsurances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationInsuranceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationInsurances");
        return quotationInsuranceRepository.findAll(pageable)
            .map(quotationInsuranceMapper::toDto);
    }

    /**
     * Get one quotationInsurance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationInsuranceDTO findOne(Long id) {
        log.debug("Request to get QuotationInsurance : {}", id);
        QuotationInsurance quotationInsurance = quotationInsuranceRepository.findOne(id);
        return quotationInsuranceMapper.toDto(quotationInsurance);
    }

    /**
     * Delete the quotationInsurance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationInsurance : {}", id);
        quotationInsuranceRepository.delete(id);
        quotationInsuranceSearchRepository.delete(id);
    }

    /**
     * Search for the quotationInsurance corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationInsuranceDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of QuotationInsurances for query {}", query);
        Page<QuotationInsurance> result = quotationInsuranceSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationInsuranceMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationInsuranceDTO> searchExample(QuotationInsuranceSearchDTO searchDto, Pageable pageable) {
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
            Page<QuotationInsurance> quotationInsurancePage= quotationInsuranceSearchRepository.search(query);
            List<QuotationInsuranceDTO> quotationInsuranceList =  StreamSupport
            .stream(quotationInsurancePage.spliterator(), false)
            .map(quotationInsuranceMapper::toDto)
            .collect(Collectors.toList());
            quotationInsuranceList.forEach(quotationInsuranceDto -> {
            if(quotationInsuranceDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationInsuranceDto.getCompanyId());
                quotationInsuranceDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(quotationInsuranceDto.getDistrictTypeId()!=null){
                DistrictType districtType= districtTypeSearchRepository.findOne(quotationInsuranceDto.getDistrictTypeId());
                quotationInsuranceDto.setDistrictTypeDTO(districtTypeMapper.toDto(districtType));
            }
            if(quotationInsuranceDto.getQuotationParentId()!=null){
                Quotation quotation= quotationSearchRepository.findOne(quotationInsuranceDto.getQuotationParentId());
                quotationInsuranceDto.setQuotationParentDTO(quotationMapper.toDto(quotation));
            }
            });
            return new PageImpl<>(quotationInsuranceList,pageable,quotationInsurancePage.getTotalElements());
        }
}
