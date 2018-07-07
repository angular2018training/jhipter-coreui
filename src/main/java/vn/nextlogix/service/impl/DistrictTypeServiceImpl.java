package vn.nextlogix.service.impl;

import vn.nextlogix.service.DistrictTypeService;
import vn.nextlogix.domain.DistrictType;


    import vn.nextlogix.repository.DistrictTypeRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.DistrictTypeSearchRepository;
import vn.nextlogix.service.dto.DistrictTypeDTO;
import vn.nextlogix.service.dto.DistrictTypeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.DistrictTypeMapper;
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
 * Service Implementation for managing DistrictType.
 */
@Service
@Transactional
public class DistrictTypeServiceImpl implements DistrictTypeService {

    private final Logger log = LoggerFactory.getLogger(DistrictTypeServiceImpl.class);

    private final DistrictTypeRepository districtTypeRepository;

    private final DistrictTypeMapper districtTypeMapper;

    private final DistrictTypeSearchRepository districtTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public DistrictTypeServiceImpl(DistrictTypeRepository districtTypeRepository, DistrictTypeMapper districtTypeMapper, DistrictTypeSearchRepository districtTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.districtTypeRepository = districtTypeRepository;
        this.districtTypeMapper = districtTypeMapper;
        this.districtTypeSearchRepository = districtTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a districtType.
     *
     * @param districtTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DistrictTypeDTO save(DistrictTypeDTO districtTypeDTO) {
        log.debug("Request to save DistrictType : {}", districtTypeDTO);
        DistrictType districtType = districtTypeMapper.toEntity(districtTypeDTO);
        districtType = districtTypeRepository.save(districtType);
        DistrictTypeDTO result = districtTypeMapper.toDto(districtType);
        districtTypeSearchRepository.save(districtType);
        return result;
    }

    /**
     * Get all the districtTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DistrictTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DistrictTypes");
        return districtTypeRepository.findAll(pageable)
            .map(districtTypeMapper::toDto);
    }

    /**
     * Get one districtType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DistrictTypeDTO findOne(Long id) {
        log.debug("Request to get DistrictType : {}", id);
        DistrictType districtType = districtTypeRepository.findOne(id);
        return districtTypeMapper.toDto(districtType);
    }

    /**
     * Delete the districtType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DistrictType : {}", id);
        districtTypeRepository.delete(id);
        districtTypeSearchRepository.delete(id);
    }

    /**
     * Search for the districtType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DistrictTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of DistrictTypes for query {}", query);
        Page<DistrictType> result = districtTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(districtTypeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<DistrictTypeDTO> searchExample(DistrictTypeSearchDTO searchDto, Pageable pageable) {
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
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<DistrictType> districtTypePage= districtTypeSearchRepository.search(query);
            List<DistrictTypeDTO> districtTypeList =  StreamSupport
            .stream(districtTypePage.spliterator(), false)
            .map(districtTypeMapper::toDto)
            .collect(Collectors.toList());
            districtTypeList.forEach(districtTypeDto -> {
            if(districtTypeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(districtTypeDto.getCompanyId());
                districtTypeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(districtTypeList,pageable,districtTypePage.getTotalElements());
        }
}
