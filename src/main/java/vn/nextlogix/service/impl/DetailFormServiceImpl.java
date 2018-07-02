package vn.nextlogix.service.impl;

import vn.nextlogix.service.DetailFormService;
import vn.nextlogix.domain.DetailForm;


    import vn.nextlogix.repository.DetailFormRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.DetailFormSearchRepository;
import vn.nextlogix.service.dto.DetailFormDTO;
import vn.nextlogix.service.dto.DetailFormSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.MasterForm;
import vn.nextlogix.service.mapper.DetailFormMapper;
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

    import vn.nextlogix.repository.search.MasterFormSearchRepository;
    import vn.nextlogix.service.mapper.MasterFormMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing DetailForm.
 */
@Service
@Transactional
public class DetailFormServiceImpl implements DetailFormService {

    private final Logger log = LoggerFactory.getLogger(DetailFormServiceImpl.class);

    private final DetailFormRepository detailFormRepository;

    private final DetailFormMapper detailFormMapper;

    private final DetailFormSearchRepository detailFormSearchRepository;


        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final MasterFormSearchRepository masterFormSearchRepository;
        private final MasterFormMapper masterFormMapper;


    public DetailFormServiceImpl(DetailFormRepository detailFormRepository, DetailFormMapper detailFormMapper, DetailFormSearchRepository detailFormSearchRepository     ,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,MasterFormSearchRepository masterFormSearchRepository,MasterFormMapper  masterFormMapper
) {
        this.detailFormRepository = detailFormRepository;
        this.detailFormMapper = detailFormMapper;
        this.detailFormSearchRepository = detailFormSearchRepository;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.masterFormSearchRepository = masterFormSearchRepository;
                                     this.masterFormMapper = masterFormMapper;

    }

    /**
     * Save a detailForm.
     *
     * @param detailFormDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DetailFormDTO save(DetailFormDTO detailFormDTO) {
        log.debug("Request to save DetailForm : {}", detailFormDTO);
        DetailForm detailForm = detailFormMapper.toEntity(detailFormDTO);
        detailForm = detailFormRepository.save(detailForm);
        DetailFormDTO result = detailFormMapper.toDto(detailForm);
        detailFormSearchRepository.save(detailForm);
        return result;
    }

    /**
     * Get all the detailForms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetailFormDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailForms");
        return detailFormRepository.findAll(pageable)
            .map(detailFormMapper::toDto);
    }

    /**
     * Get one detailForm by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DetailFormDTO findOne(Long id) {
        log.debug("Request to get DetailForm : {}", id);
        DetailForm detailForm = detailFormRepository.findOne(id);
        return detailFormMapper.toDto(detailForm);
    }

    /**
     * Delete the detailForm by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailForm : {}", id);
        detailFormRepository.delete(id);
        detailFormSearchRepository.delete(id);
    }

    /**
     * Search for the detailForm corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetailFormDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of DetailForms for query {}", query);
        Page<DetailForm> result = detailFormSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(detailFormMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<DetailFormDTO> searchExample(DetailFormSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            if(searchDto.getProvinceId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("province.Id", searchDto.getProvinceId()));
            }
            if(searchDto.getDistrictId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("district.Id", searchDto.getDistrictId()));
            }
            if(searchDto.getMasterFormParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("masterFormParent.Id", searchDto.getMasterFormParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<DetailForm> detailFormPage= detailFormSearchRepository.search(query);
            List<DetailFormDTO> detailFormList =  StreamSupport
            .stream(detailFormPage.spliterator(), false)
            .map(detailFormMapper::toDto)
            .collect(Collectors.toList());
            detailFormList.forEach(detailFormDto -> {
            if(detailFormDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(detailFormDto.getProvinceId());
                detailFormDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(detailFormDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(detailFormDto.getDistrictId());
                detailFormDto.setDistrictDTO(districtMapper.toDto(district));
            }
            if(detailFormDto.getMasterFormParentId()!=null){
                MasterForm masterForm= masterFormSearchRepository.findOne(detailFormDto.getMasterFormParentId());
                detailFormDto.setMasterFormParentDTO(masterFormMapper.toDto(masterForm));
            }
            });
            return new PageImpl<>(detailFormList,pageable,detailFormPage.getTotalElements());
        }
}
