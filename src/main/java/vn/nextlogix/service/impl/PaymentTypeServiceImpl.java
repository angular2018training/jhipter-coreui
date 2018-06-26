package vn.nextlogix.service.impl;

import vn.nextlogix.service.PaymentTypeService;
import vn.nextlogix.domain.PaymentType;
import vn.nextlogix.repository.PaymentTypeRepository;
import vn.nextlogix.repository.search.PaymentTypeSearchRepository;
import vn.nextlogix.service.dto.PaymentTypeDTO;
import vn.nextlogix.service.dto.PaymentTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.PaymentTypeMapper;
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
 * Service Implementation for managing PaymentType.
 */
@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final Logger log = LoggerFactory.getLogger(PaymentTypeServiceImpl.class);

    private final PaymentTypeRepository paymentTypeRepository;

    private final PaymentTypeMapper paymentTypeMapper;

    private final PaymentTypeSearchRepository paymentTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper, PaymentTypeSearchRepository paymentTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
        this.paymentTypeSearchRepository = paymentTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a paymentType.
     *
     * @param paymentTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PaymentTypeDTO save(PaymentTypeDTO paymentTypeDTO) {
        log.debug("Request to save PaymentType : {}", paymentTypeDTO);
        PaymentType paymentType = paymentTypeMapper.toEntity(paymentTypeDTO);
        paymentType = paymentTypeRepository.save(paymentType);
        PaymentTypeDTO result = paymentTypeMapper.toDto(paymentType);
        paymentTypeSearchRepository.save(paymentType);
        return result;
    }

    /**
     * Get all the paymentTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentTypes");
        return paymentTypeRepository.findAll(pageable)
            .map(paymentTypeMapper::toDto);
    }

    /**
     * Get one paymentType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PaymentTypeDTO findOne(Long id) {
        log.debug("Request to get PaymentType : {}", id);
        PaymentType paymentType = paymentTypeRepository.findOne(id);
        return paymentTypeMapper.toDto(paymentType);
    }

    /**
     * Delete the paymentType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentType : {}", id);
        paymentTypeRepository.delete(id);
        paymentTypeSearchRepository.delete(id);
    }

    /**
     * Search for the paymentType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PaymentTypes for query {}", query);
        Page<PaymentType> result = paymentTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(paymentTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<PaymentTypeDTO> searchExample(PaymentTypeSearchDTO searchDto, Pageable pageable) {
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
            Page<PaymentType> paymentTypePage= paymentTypeSearchRepository.search(query);
            List<PaymentTypeDTO> paymentTypeList =  StreamSupport
            .stream(paymentTypePage.spliterator(), false)
            .map(paymentTypeMapper::toDto)
            .collect(Collectors.toList());
            paymentTypeList.forEach(paymentTypeDto -> {
            if(paymentTypeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(paymentTypeDto.getCompanyId());
                paymentTypeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(paymentTypeList,pageable,paymentTypePage.getTotalElements());
        }
}
