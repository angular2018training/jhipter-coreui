package vn.nextlogix.service.impl;

import vn.nextlogix.service.WardService;
import vn.nextlogix.domain.Ward;
import vn.nextlogix.repository.WardRepository;
import vn.nextlogix.repository.search.WardSearchRepository;
import vn.nextlogix.service.dto.WardDTO;
import vn.nextlogix.service.dto.WardSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.District;
import vn.nextlogix.service.mapper.WardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
 * Service Implementation for managing Ward.
 */
@Service
@Transactional
public class WardServiceImpl implements WardService {

    private final Logger log = LoggerFactory.getLogger(WardServiceImpl.class);

    private final WardRepository wardRepository;

    private final WardMapper wardMapper;

    private final WardSearchRepository wardSearchRepository;


        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public WardServiceImpl(WardRepository wardRepository, WardMapper wardMapper, WardSearchRepository wardSearchRepository     ,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.wardRepository = wardRepository;
        this.wardMapper = wardMapper;
        this.wardSearchRepository = wardSearchRepository;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Save a ward.
     *
     * @param wardDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WardDTO save(WardDTO wardDTO) {
        log.debug("Request to save Ward : {}", wardDTO);
        Ward ward = wardMapper.toEntity(wardDTO);
        ward = wardRepository.save(ward);
        WardDTO result = wardMapper.toDto(ward);
        wardSearchRepository.save(ward);
        return result;
    }

    /**
     * Get all the wards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Wards");
        return wardRepository.findAll(pageable)
            .map(wardMapper::toDto);
    }

    /**
     * Get one ward by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WardDTO findOne(Long id) {
        log.debug("Request to get Ward : {}", id);
        Ward ward = wardRepository.findOne(id);
        return wardMapper.toDto(ward);
    }

    /**
     * Delete the ward by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ward : {}", id);
        wardRepository.delete(id);
        wardSearchRepository.delete(id);
    }

    /**
     * Search for the ward corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WardDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Wards for query {}", query);
        Page<Ward> result = wardSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(wardMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<WardDTO> searchExample(WardSearchDTO searchDto, Pageable pageable) {
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
            Page<Ward> wardPage= wardSearchRepository.search(query);
            List<WardDTO> wardList =  StreamSupport
            .stream(wardPage.spliterator(), false)
            .map(wardMapper::toDto)
            .collect(Collectors.toList());
            wardList.forEach(wardDto -> {
            if(wardDto.getDistrictId()!=null) {
                District district= districtSearchRepository.findOne(wardDto.getDistrictId());
                wardDto.setDistrictDTO(districtMapper.toDto(district));
            }
            });
            return new PageImpl<>(wardList,pageable,wardPage.getTotalElements());
        }
}
