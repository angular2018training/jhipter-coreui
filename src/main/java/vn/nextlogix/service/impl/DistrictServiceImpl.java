package vn.nextlogix.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.elasticsearch.index.query.QueryBuilders.wildcardQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.nextlogix.domain.District;
import vn.nextlogix.domain.Province;
import vn.nextlogix.repository.DistrictRepository;
import vn.nextlogix.repository.ProvinceRepository;
import vn.nextlogix.repository.search.DistrictSearchRepository;
import vn.nextlogix.repository.search.ProvinceSearchRepository;
import vn.nextlogix.service.DistrictService;
import vn.nextlogix.service.dto.DistrictDTO;
import vn.nextlogix.service.dto.DistrictSearchDTO;
import vn.nextlogix.service.mapper.DistrictMapper;
import vn.nextlogix.service.mapper.ProvinceMapper;

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
    
    private final ProvinceSearchRepository provinceSearchRepository;
    
    private final ProvinceMapper provinceMapper;
    
    private final ProvinceRepository provinceRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper, DistrictSearchRepository districtSearchRepository,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper provinceMapper,ProvinceRepository provinceRepository) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
        this.districtSearchRepository = districtSearchRepository;
        this.provinceSearchRepository = provinceSearchRepository;
        this.provinceMapper = provinceMapper;
        this.provinceRepository = provinceRepository;
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
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DistrictDTO> findAll() {
        log.debug("Request to get all Districts");
        return districtRepository.findAll().stream()
            .map(districtMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
        DistrictDTO districtDTO =  districtMapper.toDto(district);
        if(district.getProvince()!=null) {
        	Province province = provinceRepository.findOne(district.getProvince().getId());
        	districtDTO.setProvinceDTO(provinceMapper.toDto(province));
        }
        return districtDTO;
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
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DistrictDTO> search(String query) {
        log.debug("Request to search Districts for query {}", query);
        return StreamSupport
            .stream(districtSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(districtMapper::toDto)
            .collect(Collectors.toList());
    }

	@Override
	public Page<DistrictDTO> searchByExample(DistrictSearchDTO searchDto, Pageable pageable) {
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if(StringUtils.isNotBlank(searchDto.getCode())) {

			boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
		}
		if(StringUtils.isNotBlank(searchDto.getName())) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
		}
		SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
		Page<District> districtPage= districtSearchRepository.search(query);
		List<DistrictDTO> districtList =  StreamSupport
         .stream(districtPage.spliterator(), false)
         .map(districtMapper::toDto)
         .collect(Collectors.toList());
		districtList.forEach(districtDto -> {
			if(districtDto.getProvinceId()!=null) {
				Province province= provinceSearchRepository.findOne(districtDto.getProvinceId());
				districtDto.setProvinceDTO(provinceMapper.toDto(province));
			}
		});
		return new PageImpl<>(districtList,pageable,districtPage.getTotalElements());
		
	}
}
