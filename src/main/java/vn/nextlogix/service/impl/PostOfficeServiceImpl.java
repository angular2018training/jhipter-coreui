package vn.nextlogix.service.impl;

import vn.nextlogix.service.PostOfficeService;
import vn.nextlogix.domain.PostOffice;
import vn.nextlogix.repository.PostOfficeRepository;
import vn.nextlogix.repository.search.PostOfficeSearchRepository;
import vn.nextlogix.service.dto.PostOfficeDTO;
import vn.nextlogix.service.mapper.PostOfficeMapper;
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
 * Service Implementation for managing PostOffice.
 */
@Service
@Transactional
public class PostOfficeServiceImpl implements PostOfficeService {

    private final Logger log = LoggerFactory.getLogger(PostOfficeServiceImpl.class);

    private final PostOfficeRepository postOfficeRepository;

    private final PostOfficeMapper postOfficeMapper;

    private final PostOfficeSearchRepository postOfficeSearchRepository;

    public PostOfficeServiceImpl(PostOfficeRepository postOfficeRepository, PostOfficeMapper postOfficeMapper, PostOfficeSearchRepository postOfficeSearchRepository) {
        this.postOfficeRepository = postOfficeRepository;
        this.postOfficeMapper = postOfficeMapper;
        this.postOfficeSearchRepository = postOfficeSearchRepository;
    }

    /**
     * Save a postOffice.
     *
     * @param postOfficeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PostOfficeDTO save(PostOfficeDTO postOfficeDTO) {
        log.debug("Request to save PostOffice : {}", postOfficeDTO);
        PostOffice postOffice = postOfficeMapper.toEntity(postOfficeDTO);
        postOffice = postOfficeRepository.save(postOffice);
        PostOfficeDTO result = postOfficeMapper.toDto(postOffice);
        postOfficeSearchRepository.save(postOffice);
        return result;
    }

    /**
     * Get all the postOffices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PostOfficeDTO> findAll() {
        log.debug("Request to get all PostOffices");
        return postOfficeRepository.findAll().stream()
            .map(postOfficeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one postOffice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PostOfficeDTO findOne(Long id) {
        log.debug("Request to get PostOffice : {}", id);
        PostOffice postOffice = postOfficeRepository.findOne(id);
        return postOfficeMapper.toDto(postOffice);
    }

    /**
     * Delete the postOffice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PostOffice : {}", id);
        postOfficeRepository.delete(id);
        postOfficeSearchRepository.delete(id);
    }

    /**
     * Search for the postOffice corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PostOfficeDTO> search(String query) {
        log.debug("Request to search PostOffices for query {}", query);
        return StreamSupport
            .stream(postOfficeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(postOfficeMapper::toDto)
            .collect(Collectors.toList());
    }
}
