package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationService;
import vn.nextlogix.domain.Quotation;


    import vn.nextlogix.repository.QuotationRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.QuotationSearchRepository;
import vn.nextlogix.service.dto.QuotationDTO;
import vn.nextlogix.service.dto.QuotationSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.QuotationMapper;
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
 * Service Implementation for managing Quotation.
 */
@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

    private final Logger log = LoggerFactory.getLogger(QuotationServiceImpl.class);

    private final QuotationRepository quotationRepository;

    private final QuotationMapper quotationMapper;

    private final QuotationSearchRepository quotationSearchRepository;









        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper, QuotationSearchRepository quotationSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
        this.quotationSearchRepository = quotationSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationDTO save(QuotationDTO quotationDTO) {
        log.debug("Request to save Quotation : {}", quotationDTO);
        Quotation quotation = quotationMapper.toEntity(quotationDTO);
        quotation = quotationRepository.save(quotation);
        QuotationDTO result = quotationMapper.toDto(quotation);
        quotationSearchRepository.save(quotation);
        return result;
    }

    /**
     * Get all the quotations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quotations");
        return quotationRepository.findAll(pageable)
            .map(quotationMapper::toDto);
    }

    /**
     * Get one quotation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationDTO findOne(Long id) {
        log.debug("Request to get Quotation : {}", id);
        Quotation quotation = quotationRepository.findOne(id);
        return quotationMapper.toDto(quotation);
    }

    /**
     * Delete the quotation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quotation : {}", id);
        quotationRepository.delete(id);
        quotationSearchRepository.delete(id);
    }

    /**
     * Search for the quotation corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Quotations for query {}", query);
        Page<Quotation> result = quotationSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(quotationMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDTO> searchExample(QuotationSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getName())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<Quotation> quotationPage= quotationSearchRepository.search(query);
            List<QuotationDTO> quotationList =  StreamSupport
            .stream(quotationPage.spliterator(), false)
            .map(quotationMapper::toDto)
            .collect(Collectors.toList());
            quotationList.forEach(quotationDto -> {
            if(quotationDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(quotationDto.getCompanyId());
                quotationDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(quotationList,pageable,quotationPage.getTotalElements());
        }
}
