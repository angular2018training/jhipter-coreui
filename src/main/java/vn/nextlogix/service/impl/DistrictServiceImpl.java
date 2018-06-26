package vn.nextlogix.service.impl;

import vn.nextlogix.service.DistrictService;
import vn.nextlogix.domain.District;
import vn.nextlogix.repository.DistrictRepository;
import vn.nextlogix.repository.search.DistrictSearchRepository;
import vn.nextlogix.service.dto.DistrictDTO;
import vn.nextlogix.service.dto.DistrictSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Province;
import vn.nextlogix.service.mapper.DistrictMapper;
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
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing District.
 */
@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final Logger log = LoggerFactory.getLogger(DistrictServiceImpl.class);

    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;

    private final DistrictSearchRepository districtSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;


    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper, DistrictSearchRepository districtSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
        this.districtSearchRepository = districtSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;

    }

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DistrictDTO save(DistrictDTO districtDTO) {
        log.debug("Request to save District : {}", districtDTO);
        District district = districtMapper.toEntity(districtDTO);
        district = districtRepository.save(district);
        DistrictDTO result = districtMapper.toDto(district);
        districtSearchRepository.save(district);
        return result;
    }

    /**
     * Get all the districts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DistrictDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Districts");
        return districtRepository.findAll(pageable)
            .map(districtMapper::toDto);
    }

    /**
     * Get one district by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DistrictDTO findOne(Long id) {
        log.debug("Request to get District : {}", id);
        District district = districtRepository.findOne(id);
        return districtMapper.toDto(district);
    }

    /**
     * Delete the district by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete District : {}", id);
        districtRepository.delete(id);
        districtSearchRepository.delete(id);
    }

    /**
     * Search for the district corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DistrictDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Districts for query {}", query);
        Page<District> result = districtSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(districtMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<DistrictDTO> searchExample(DistrictSearchDTO searchDto, Pageable pageable) {
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
            Page<District> districtPage= districtSearchRepository.search(query);
            List<DistrictDTO> districtList =  StreamSupport
            .stream(districtPage.spliterator(), false)
            .map(districtMapper::toDto)
            .collect(Collectors.toList());
            districtList.forEach(districtDto -> {
            if(districtDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(districtDto.getCompanyId());
                districtDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(districtDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(districtDto.getProvinceId());
                districtDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            });
            return new PageImpl<>(districtList,pageable,districtPage.getTotalElements());
        }
}
