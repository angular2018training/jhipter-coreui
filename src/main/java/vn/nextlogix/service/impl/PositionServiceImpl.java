package vn.nextlogix.service.impl;

import vn.nextlogix.service.PositionService;
import vn.nextlogix.domain.Position;


    import vn.nextlogix.repository.PositionRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.PositionSearchRepository;
import vn.nextlogix.service.dto.PositionDTO;
import vn.nextlogix.service.dto.PositionSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.PositionMapper;
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
 * Service Implementation for managing Position.
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    private final Logger log = LoggerFactory.getLogger(PositionServiceImpl.class);

    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    private final PositionSearchRepository positionSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public PositionServiceImpl(PositionRepository positionRepository, PositionMapper positionMapper, PositionSearchRepository positionSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
        this.positionSearchRepository = positionSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a position.
     *
     * @param positionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PositionDTO save(PositionDTO positionDTO) {
        log.debug("Request to save Position : {}", positionDTO);
        Position position = positionMapper.toEntity(positionDTO);
        position = positionRepository.save(position);
        PositionDTO result = positionMapper.toDto(position);
        positionSearchRepository.save(position);
        return result;
    }

    /**
     * Get all the positions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PositionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Positions");
        return positionRepository.findAll(pageable)
            .map(positionMapper::toDto);
    }

    /**
     * Get one position by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PositionDTO findOne(Long id) {
        log.debug("Request to get Position : {}", id);
        Position position = positionRepository.findOne(id);
        return positionMapper.toDto(position);
    }

    /**
     * Delete the position by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Position : {}", id);
        positionRepository.delete(id);
        positionSearchRepository.delete(id);
    }

    /**
     * Search for the position corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PositionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Positions for query {}", query);
        Page<Position> result = positionSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(positionMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<PositionDTO> searchExample(PositionSearchDTO searchDto, Pageable pageable) {
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
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.Id", searchDto.getCompanyId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<Position> positionPage= positionSearchRepository.search(query);
            List<PositionDTO> positionList =  StreamSupport
            .stream(positionPage.spliterator(), false)
            .map(positionMapper::toDto)
            .collect(Collectors.toList());
            positionList.forEach(positionDto -> {
            if(positionDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(positionDto.getCompanyId());
                positionDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(positionList,pageable,positionPage.getTotalElements());
        }
}
