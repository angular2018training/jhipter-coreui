package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerLegalService;
import vn.nextlogix.domain.CustomerLegal;
import vn.nextlogix.repository.CustomerLegalRepository;
import vn.nextlogix.repository.search.CustomerLegalSearchRepository;
import vn.nextlogix.service.dto.CustomerLegalDTO;
import vn.nextlogix.service.mapper.CustomerLegalMapper;
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
 * Service Implementation for managing CustomerLegal.
 */
@Service
@Transactional
public class CustomerLegalServiceImpl implements CustomerLegalService {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalServiceImpl.class);

    private final CustomerLegalRepository customerLegalRepository;

    private final CustomerLegalMapper customerLegalMapper;

    private final CustomerLegalSearchRepository customerLegalSearchRepository;

    public CustomerLegalServiceImpl(CustomerLegalRepository customerLegalRepository, CustomerLegalMapper customerLegalMapper, CustomerLegalSearchRepository customerLegalSearchRepository) {
        this.customerLegalRepository = customerLegalRepository;
        this.customerLegalMapper = customerLegalMapper;
        this.customerLegalSearchRepository = customerLegalSearchRepository;
    }

    /**
     * Save a customerLegal.
     *
     * @param customerLegalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerLegalDTO save(CustomerLegalDTO customerLegalDTO) {
        log.debug("Request to save CustomerLegal : {}", customerLegalDTO);
        CustomerLegal customerLegal = customerLegalMapper.toEntity(customerLegalDTO);
        customerLegal = customerLegalRepository.save(customerLegal);
        CustomerLegalDTO result = customerLegalMapper.toDto(customerLegal);
        customerLegalSearchRepository.save(customerLegal);
        return result;
    }

    /**
     * Get all the customerLegals.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerLegalDTO> findAll() {
        log.debug("Request to get all CustomerLegals");
        return customerLegalRepository.findAllWithEagerRelationships().stream()
            .map(customerLegalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one customerLegal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerLegalDTO findOne(Long id) {
        log.debug("Request to get CustomerLegal : {}", id);
        CustomerLegal customerLegal = customerLegalRepository.findOneWithEagerRelationships(id);
        return customerLegalMapper.toDto(customerLegal);
    }

    /**
     * Delete the customerLegal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerLegal : {}", id);
        customerLegalRepository.delete(id);
        customerLegalSearchRepository.delete(id);
    }

    /**
     * Search for the customerLegal corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerLegalDTO> search(String query) {
        log.debug("Request to search CustomerLegals for query {}", query);
        return StreamSupport
            .stream(customerLegalSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerLegalMapper::toDto)
            .collect(Collectors.toList());
    }
}
