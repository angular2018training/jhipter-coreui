package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserPositionService;
import vn.nextlogix.domain.UserPosition;
import vn.nextlogix.repository.UserPositionRepository;
import vn.nextlogix.repository.search.UserPositionSearchRepository;
import vn.nextlogix.service.dto.UserPositionDTO;
import vn.nextlogix.service.mapper.UserPositionMapper;
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
 * Service Implementation for managing UserPosition.
 */
@Service
@Transactional
public class UserPositionServiceImpl implements UserPositionService {

    private final Logger log = LoggerFactory.getLogger(UserPositionServiceImpl.class);

    private final UserPositionRepository userPositionRepository;

    private final UserPositionMapper userPositionMapper;

    private final UserPositionSearchRepository userPositionSearchRepository;

    public UserPositionServiceImpl(UserPositionRepository userPositionRepository, UserPositionMapper userPositionMapper, UserPositionSearchRepository userPositionSearchRepository) {
        this.userPositionRepository = userPositionRepository;
        this.userPositionMapper = userPositionMapper;
        this.userPositionSearchRepository = userPositionSearchRepository;
    }

    /**
     * Save a userPosition.
     *
     * @param userPositionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserPositionDTO save(UserPositionDTO userPositionDTO) {
        log.debug("Request to save UserPosition : {}", userPositionDTO);
        UserPosition userPosition = userPositionMapper.toEntity(userPositionDTO);
        userPosition = userPositionRepository.save(userPosition);
        UserPositionDTO result = userPositionMapper.toDto(userPosition);
        userPositionSearchRepository.save(userPosition);
        return result;
    }

    /**
     * Get all the userPositions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserPositionDTO> findAll() {
        log.debug("Request to get all UserPositions");
        return userPositionRepository.findAll().stream()
            .map(userPositionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userPosition by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserPositionDTO findOne(Long id) {
        log.debug("Request to get UserPosition : {}", id);
        UserPosition userPosition = userPositionRepository.findOne(id);
        return userPositionMapper.toDto(userPosition);
    }

    /**
     * Delete the userPosition by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPosition : {}", id);
        userPositionRepository.delete(id);
        userPositionSearchRepository.delete(id);
    }

    /**
     * Search for the userPosition corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserPositionDTO> search(String query) {
        log.debug("Request to search UserPositions for query {}", query);
        return StreamSupport
            .stream(userPositionSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(userPositionMapper::toDto)
            .collect(Collectors.toList());
    }
}
