package vn.nextlogix.service.impl;

import vn.nextlogix.service.ProvinceService;
import vn.nextlogix.domain.Province;
import vn.nextlogix.repository.ProvinceRepository;
import vn.nextlogix.repository.search.ProvinceSearchRepository;
import vn.nextlogix.service.dto.ProvinceDTO;
import vn.nextlogix.service.dto.ProvinceSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.ProvinceMapper;
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
 * Service Implementation for managing Province.
 */
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    private final Logger log = LoggerFactory.getLogger(ProvinceServiceImpl.class);

    private final ProvinceRepository provinceRepository;

    private final ProvinceMapper provinceMapper;

    private final ProvinceSearchRepository provinceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper, ProvinceSearchRepository provinceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
        this.provinceSearchRepository = provinceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a province.
     *
     * @param provinceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProvinceDTO save(ProvinceDTO provinceDTO) {
        log.debug("Request to save Province : {}", provinceDTO);
        Province province = provinceMapper.toEntity(provinceDTO);
        province = provinceRepository.save(province);
        ProvinceDTO result = provinceMapper.toDto(province);
        provinceSearchRepository.save(province);
        return result;
    }

    /**
     * Get all the provinces.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProvinceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Provinces");
        return provinceRepository.findAll(pageable)
            .map(provinceMapper::toDto);
    }

    /**
     * Get one province by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProvinceDTO findOne(Long id) {
        log.debug("Request to get Province : {}", id);
        Province province = provinceRepository.findOne(id);
        return provinceMapper.toDto(province);
    }

    /**
     * Delete the province by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Province : {}", id);
        provinceRepository.delete(id);
        provinceSearchRepository.delete(id);
    }

    /**
     * Search for the province corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProvinceDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Provinces for query {}", query);
        Page<Province> result = provinceSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(provinceMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<ProvinceDTO> searchExample(ProvinceSearchDTO searchDto, Pageable pageable) {
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
            Page<Province> provincePage= provinceSearchRepository.search(query);
            List<ProvinceDTO> provinceList =  StreamSupport
            .stream(provincePage.spliterator(), false)
            .map(provinceMapper::toDto)
            .collect(Collectors.toList());
            provinceList.forEach(provinceDto -> {
            if(provinceDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(provinceDto.getCompanyId());
                provinceDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(provinceList,pageable,provincePage.getTotalElements());
        }
}
