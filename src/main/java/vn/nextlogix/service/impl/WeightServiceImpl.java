package vn.nextlogix.service.impl;

import vn.nextlogix.service.WeightService;
import vn.nextlogix.domain.Weight;


    import vn.nextlogix.repository.WeightRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.WeightSearchRepository;
import vn.nextlogix.service.dto.WeightDTO;
import vn.nextlogix.service.dto.WeightSearchDTO;
import org.springframework.data.domain.PageImpl;
import vn.nextlogix.service.mapper.WeightMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Weight.
 */
@Service
@Transactional
public class WeightServiceImpl implements WeightService {

    private final Logger log = LoggerFactory.getLogger(WeightServiceImpl.class);

    private final WeightRepository weightRepository;

    private final WeightMapper weightMapper;

    private final WeightSearchRepository weightSearchRepository;



    public WeightServiceImpl(WeightRepository weightRepository, WeightMapper weightMapper, WeightSearchRepository weightSearchRepository     ) {
        this.weightRepository = weightRepository;
        this.weightMapper = weightMapper;
        this.weightSearchRepository = weightSearchRepository;

    }

    /**
     * Save a weight.
     *
     * @param weightDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WeightDTO save(WeightDTO weightDTO) {
        log.debug("Request to save Weight : {}", weightDTO);
        Weight weight = weightMapper.toEntity(weightDTO);
        weight = weightRepository.save(weight);
        WeightDTO result = weightMapper.toDto(weight);
        weightSearchRepository.save(weight);
        return result;
    }

    /**
     * Get all the weights.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WeightDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Weights");
        return weightRepository.findAll(pageable)
            .map(weightMapper::toDto);
    }

    /**
     * Get one weight by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WeightDTO findOne(Long id) {
        log.debug("Request to get Weight : {}", id);
        Weight weight = weightRepository.findOne(id);
        return weightMapper.toDto(weight);
    }

    /**
     * Delete the weight by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Weight : {}", id);
        weightRepository.delete(id);
        weightSearchRepository.delete(id);
    }

    /**
     * Search for the weight corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WeightDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Weights for query {}", query);
        Page<Weight> result = weightSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(weightMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<WeightDTO> searchExample(WeightSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getName())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<Weight> weightPage= weightSearchRepository.search(query);
            List<WeightDTO> weightList =  StreamSupport
            .stream(weightPage.spliterator(), false)
            .map(weightMapper::toDto)
            .collect(Collectors.toList());
            weightList.forEach(weightDto -> {
            });
            return new PageImpl<>(weightList,pageable,weightPage.getTotalElements());
        }
}
