package vn.nextlogix.service.impl;

import vn.nextlogix.service.WardService;
import vn.nextlogix.domain.Ward;
import vn.nextlogix.repository.WardRepository;
import vn.nextlogix.repository.search.WardSearchRepository;
import vn.nextlogix.service.dto.WardDTO;
import vn.nextlogix.service.mapper.WardMapper;
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
 * Service Implementation for managing Ward.
 */
@Service
@Transactional
public class WardServiceImpl implements WardService {

    private final Logger log = LoggerFactory.getLogger(WardServiceImpl.class);

    private final WardRepository wardRepository;

    private final WardMapper wardMapper;

    private final WardSearchRepository wardSearchRepository;

    public WardServiceImpl(WardRepository wardRepository, WardMapper wardMapper, WardSearchRepository wardSearchRepository) {
        this.wardRepository = wardRepository;
        this.wardMapper = wardMapper;
        this.wardSearchRepository = wardSearchRepository;
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
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WardDTO> findAll() {
        log.debug("Request to get all Wards");
        return wardRepository.findAll().stream()
            .map(wardMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WardDTO> search(String query) {
        log.debug("Request to search Wards for query {}", query);
        return StreamSupport
            .stream(wardSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(wardMapper::toDto)
            .collect(Collectors.toList());
    }
}
