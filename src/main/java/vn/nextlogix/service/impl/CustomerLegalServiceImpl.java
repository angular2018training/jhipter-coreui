package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerLegalService;
import vn.nextlogix.domain.CustomerLegal;
import vn.nextlogix.repository.CustomerLegalRepository;
import vn.nextlogix.repository.search.CustomerLegalSearchRepository;
import vn.nextlogix.service.dto.CustomerLegalDTO;
import vn.nextlogix.service.dto.CustomerLegalSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
import vn.nextlogix.service.mapper.CustomerLegalMapper;
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

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerLegal.
 */
@Service
@Transactional
public class CustomerLegalServiceImpl implements CustomerLegalService {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalServiceImpl.class);

    private final CustomerLegalRepository customerLegalRepository;

    private final CustomerLegalMapper customerLegalMapper;

    private final CustomerLegalSearchRepository customerLegalSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;



    public CustomerLegalServiceImpl(CustomerLegalRepository customerLegalRepository, CustomerLegalMapper customerLegalMapper, CustomerLegalSearchRepository customerLegalSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.customerLegalRepository = customerLegalRepository;
        this.customerLegalMapper = customerLegalMapper;
        this.customerLegalSearchRepository = customerLegalSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Save a customerLegal.
     *
     * @param customerLegalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerLegalDTO save(CustomerLegalDTO customerLegalDTO) {
        log.debug("Request to save CustomerLegal : {}", customerLegalDTO);
        CustomerLegal customerLegal = customerLegalMapper.toEntity(customerLegalDTO);
        customerLegal = customerLegalRepository.save(customerLegal);
        CustomerLegalDTO result = customerLegalMapper.toDto(customerLegal);
        customerLegalSearchRepository.save(customerLegal);
        return result;
    }

    /**
     * Get all the customerLegals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerLegalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerLegals");
        return customerLegalRepository.findAll(pageable)
            .map(customerLegalMapper::toDto);
    }

    /**
     * Get one customerLegal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerLegalDTO findOne(Long id) {
        log.debug("Request to get CustomerLegal : {}", id);
        CustomerLegal customerLegal = customerLegalRepository.findOneWithEagerRelationships(id);
        return customerLegalMapper.toDto(customerLegal);
    }

    /**
     * Delete the customerLegal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerLegal : {}", id);
        customerLegalRepository.delete(id);
        customerLegalSearchRepository.delete(id);
    }

    /**
     * Search for the customerLegal corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerLegalDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerLegals for query {}", query);
        Page<CustomerLegal> result = customerLegalSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerLegalMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerLegalDTO> searchExample(CustomerLegalSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getContractCustomerName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contractCustomerName", "*"+searchDto.getContractCustomerName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContractAddress())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contractAddress", "*"+searchDto.getContractAddress()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContractContactName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contractContactName", "*"+searchDto.getContractContactName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContractContactPhone())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contractContactPhone", "*"+searchDto.getContractContactPhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getTaxCode())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("taxCode", "*"+searchDto.getTaxCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContractExpirationDate())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contractExpirationDate", "*"+searchDto.getContractExpirationDate()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<CustomerLegal> customerLegalPage= customerLegalSearchRepository.search(query);
            List<CustomerLegalDTO> customerLegalList =  StreamSupport
            .stream(customerLegalPage.spliterator(), false)
            .map(customerLegalMapper::toDto)
            .collect(Collectors.toList());
            customerLegalList.forEach(customerLegalDto -> {
            if(customerLegalDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerLegalDto.getCompanyId());
                customerLegalDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerLegalDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(customerLegalDto.getProvinceId());
                customerLegalDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(customerLegalDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(customerLegalDto.getDistrictId());
                customerLegalDto.setDistrictDTO(districtMapper.toDto(district));
            }
            });
            return new PageImpl<>(customerLegalList,pageable,customerLegalPage.getTotalElements());
        }
}
