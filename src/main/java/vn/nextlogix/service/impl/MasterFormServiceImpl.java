package vn.nextlogix.service.impl;

import vn.nextlogix.service.MasterFormService;
import vn.nextlogix.domain.MasterForm;


    import vn.nextlogix.repository.MasterFormRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.MasterFormSearchRepository;
import vn.nextlogix.service.dto.MasterFormDTO;
import vn.nextlogix.service.dto.MasterFormSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
import vn.nextlogix.service.mapper.MasterFormMapper;
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
 * Service Implementation for managing MasterForm.
 */
@Service
@Transactional
public class MasterFormServiceImpl implements MasterFormService {

    private final Logger log = LoggerFactory.getLogger(MasterFormServiceImpl.class);

    private final MasterFormRepository masterFormRepository;

    private final MasterFormMapper masterFormMapper;

    private final MasterFormSearchRepository masterFormSearchRepository;



        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public MasterFormServiceImpl(MasterFormRepository masterFormRepository, MasterFormMapper masterFormMapper, MasterFormSearchRepository masterFormSearchRepository     ,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.masterFormRepository = masterFormRepository;
        this.masterFormMapper = masterFormMapper;
        this.masterFormSearchRepository = masterFormSearchRepository;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Save a masterForm.
     *
     * @param masterFormDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MasterFormDTO save(MasterFormDTO masterFormDTO) {
        log.debug("Request to save MasterForm : {}", masterFormDTO);
        MasterForm masterForm = masterFormMapper.toEntity(masterFormDTO);
        masterForm = masterFormRepository.save(masterForm);
        MasterFormDTO result = masterFormMapper.toDto(masterForm);
        masterFormSearchRepository.save(masterForm);
        return result;
    }

    /**
     * Get all the masterForms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MasterFormDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MasterForms");
        return masterFormRepository.findAll(pageable)
            .map(masterFormMapper::toDto);
    }

    /**
     * Get one masterForm by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MasterFormDTO findOne(Long id) {
        log.debug("Request to get MasterForm : {}", id);
        MasterForm masterForm = masterFormRepository.findOne(id);
        return masterFormMapper.toDto(masterForm);
    }

    /**
     * Delete the masterForm by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MasterForm : {}", id);
        masterFormRepository.delete(id);
        masterFormSearchRepository.delete(id);
    }

    /**
     * Search for the masterForm corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MasterFormDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MasterForms for query {}", query);
        Page<MasterForm> result = masterFormSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(masterFormMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<MasterFormDTO> searchExample(MasterFormSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getNote())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("note", "*"+searchDto.getNote()+"*"));
            }
            if(searchDto.getProvinceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("province.Id", searchDto.getProvinceId()));
            }
            if(searchDto.getDistrictId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("district.Id", searchDto.getDistrictId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<MasterForm> masterFormPage= masterFormSearchRepository.search(query);
            List<MasterFormDTO> masterFormList =  StreamSupport
            .stream(masterFormPage.spliterator(), false)
            .map(masterFormMapper::toDto)
            .collect(Collectors.toList());
            masterFormList.forEach(masterFormDto -> {
            if(masterFormDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(masterFormDto.getProvinceId());
                masterFormDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(masterFormDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(masterFormDto.getDistrictId());
                masterFormDto.setDistrictDTO(districtMapper.toDto(district));
            }
            });
            return new PageImpl<>(masterFormList,pageable,masterFormPage.getTotalElements());
        }
}
