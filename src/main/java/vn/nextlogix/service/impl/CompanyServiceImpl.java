package vn.nextlogix.service.impl;

import vn.nextlogix.service.CompanyService;
import vn.nextlogix.domain.Company;
import vn.nextlogix.repository.CompanyRepository;
import vn.nextlogix.repository.search.CompanySearchRepository;
import vn.nextlogix.service.dto.CompanyDTO;
import vn.nextlogix.service.dto.CompanySearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
import vn.nextlogix.service.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
 * Service Implementation for managing Company.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    private final CompanySearchRepository companySearchRepository;


        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, CompanySearchRepository companySearchRepository     ,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companySearchRepository = companySearchRepository;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        CompanyDTO result = companyMapper.toDto(company);
        companySearchRepository.save(company);
        return result;
    }

    /**
     * Get all the companies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable)
            .map(companyMapper::toDto);
    }

    /**
     * Get one company by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyDTO findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        Company company = companyRepository.findOne(id);
        return companyMapper.toDto(company);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.delete(id);
        companySearchRepository.delete(id);
    }

    /**
     * Search for the company corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Companies for query {}", query);
        Page<Company> result = companySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(companyMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> searchExample(CompanySearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getAddress())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("address", "*"+searchDto.getAddress()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getPhone())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("phone", "*"+searchDto.getPhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getEmail())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("email", "*"+searchDto.getEmail()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getWebsite())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("website", "*"+searchDto.getWebsite()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<Company> companyPage= companySearchRepository.search(query);
            List<CompanyDTO> companyList =  StreamSupport
            .stream(companyPage.spliterator(), false)
            .map(companyMapper::toDto)
            .collect(Collectors.toList());
            companyList.forEach(companyDto -> {
            if(companyDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(companyDto.getProvinceId());
                companyDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(companyDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(companyDto.getDistrictId());
                companyDto.setDistrictDTO(districtMapper.toDto(district));
            }
            });
            return new PageImpl<>(companyList,pageable,companyPage.getTotalElements());
        }
}
