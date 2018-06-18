package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerPostOfficeService;
import vn.nextlogix.domain.CustomerPostOffice;
import vn.nextlogix.repository.CustomerPostOfficeRepository;
import vn.nextlogix.repository.search.CustomerPostOfficeSearchRepository;
import vn.nextlogix.service.dto.CustomerPostOfficeDTO;
import vn.nextlogix.service.mapper.CustomerPostOfficeMapper;
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
 * Service Implementation for managing CustomerPostOffice.
 */
@Service
@Transactional
public class CustomerPostOfficeServiceImpl implements CustomerPostOfficeService {

    private final Logger log = LoggerFactory.getLogger(CustomerPostOfficeServiceImpl.class);

    private final CustomerPostOfficeRepository customerPostOfficeRepository;

    private final CustomerPostOfficeMapper customerPostOfficeMapper;

    private final CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository;

    public CustomerPostOfficeServiceImpl(CustomerPostOfficeRepository customerPostOfficeRepository, CustomerPostOfficeMapper customerPostOfficeMapper, CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository) {
        this.customerPostOfficeRepository = customerPostOfficeRepository;
        this.customerPostOfficeMapper = customerPostOfficeMapper;
        this.customerPostOfficeSearchRepository = customerPostOfficeSearchRepository;
    }

    /**
     * Save a customerPostOffice.
     *
     * @param customerPostOfficeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerPostOfficeDTO save(CustomerPostOfficeDTO customerPostOfficeDTO) {
        log.debug("Request to save CustomerPostOffice : {}", customerPostOfficeDTO);
        CustomerPostOffice customerPostOffice = customerPostOfficeMapper.toEntity(customerPostOfficeDTO);
        customerPostOffice = customerPostOfficeRepository.save(customerPostOffice);
        CustomerPostOfficeDTO result = customerPostOfficeMapper.toDto(customerPostOffice);
        customerPostOfficeSearchRepository.save(customerPostOffice);
        return result;
    }

    /**
     * Get all the customerPostOffices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerPostOfficeDTO> findAll() {
        log.debug("Request to get all CustomerPostOffices");
        return customerPostOfficeRepository.findAll().stream()
            .map(customerPostOfficeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one customerPostOffice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerPostOfficeDTO findOne(Long id) {
        log.debug("Request to get CustomerPostOffice : {}", id);
        CustomerPostOffice customerPostOffice = customerPostOfficeRepository.findOne(id);
        return customerPostOfficeMapper.toDto(customerPostOffice);
    }

    /**
     * Delete the customerPostOffice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerPostOffice : {}", id);
        customerPostOfficeRepository.delete(id);
        customerPostOfficeSearchRepository.delete(id);
    }

    /**
     * Search for the customerPostOffice corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerPostOfficeDTO> search(String query) {
        log.debug("Request to search CustomerPostOffices for query {}", query);
        return StreamSupport
            .stream(customerPostOfficeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerPostOfficeMapper::toDto)
            .collect(Collectors.toList());
    }
}
