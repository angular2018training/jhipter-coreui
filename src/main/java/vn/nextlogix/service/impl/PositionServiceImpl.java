package vn.nextlogix.service.impl;

import vn.nextlogix.service.PositionService;
import vn.nextlogix.domain.Position;
import vn.nextlogix.repository.PositionRepository;
import vn.nextlogix.repository.search.PositionSearchRepository;
import vn.nextlogix.service.dto.PositionDTO;
import vn.nextlogix.service.mapper.PositionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public PositionServiceImpl(PositionRepository positionRepository, PositionMapper positionMapper, PositionSearchRepository positionSearchRepository) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
        this.positionSearchRepository = positionSearchRepository;
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
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PositionDTO> findAll() {
        log.debug("Request to get all Positions");
        return positionRepository.findAll().stream()
            .map(positionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PositionDTO> search(String query) {
        log.debug("Request to search Positions for query {}", query);
        return StreamSupport
            .stream(positionSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(positionMapper::toDto)
            .collect(Collectors.toList());
    }
}
