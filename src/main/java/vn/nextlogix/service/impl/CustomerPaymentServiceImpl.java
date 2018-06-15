package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerPaymentService;
import vn.nextlogix.domain.CustomerPayment;
import vn.nextlogix.repository.CustomerPaymentRepository;
import vn.nextlogix.repository.search.CustomerPaymentSearchRepository;
import vn.nextlogix.service.dto.CustomerPaymentDTO;
import vn.nextlogix.service.mapper.CustomerPaymentMapper;
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
 * Service Implementation for managing CustomerPayment.
 */
@Service
@Transactional
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    private final Logger log = LoggerFactory.getLogger(CustomerPaymentServiceImpl.class);

    private final CustomerPaymentRepository customerPaymentRepository;

    private final CustomerPaymentMapper customerPaymentMapper;

    private final CustomerPaymentSearchRepository customerPaymentSearchRepository;

    public CustomerPaymentServiceImpl(CustomerPaymentRepository customerPaymentRepository, CustomerPaymentMapper customerPaymentMapper, CustomerPaymentSearchRepository customerPaymentSearchRepository) {
        this.customerPaymentRepository = customerPaymentRepository;
        this.customerPaymentMapper = customerPaymentMapper;
        this.customerPaymentSearchRepository = customerPaymentSearchRepository;
    }

    /**
     * Save a customerPayment.
     *
     * @param customerPaymentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerPaymentDTO save(CustomerPaymentDTO customerPaymentDTO) {
        log.debug("Request to save CustomerPayment : {}", customerPaymentDTO);
        CustomerPayment customerPayment = customerPaymentMapper.toEntity(customerPaymentDTO);
        customerPayment = customerPaymentRepository.save(customerPayment);
        CustomerPaymentDTO result = customerPaymentMapper.toDto(customerPayment);
        customerPaymentSearchRepository.save(customerPayment);
        return result;
    }

    /**
     * Get all the customerPayments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerPaymentDTO> findAll() {
        log.debug("Request to get all CustomerPayments");
        return customerPaymentRepository.findAll().stream()
            .map(customerPaymentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one customerPayment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerPaymentDTO findOne(Long id) {
        log.debug("Request to get CustomerPayment : {}", id);
        CustomerPayment customerPayment = customerPaymentRepository.findOne(id);
        return customerPaymentMapper.toDto(customerPayment);
    }

    /**
     * Delete the customerPayment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerPayment : {}", id);
        customerPaymentRepository.delete(id);
        customerPaymentSearchRepository.delete(id);
    }

    /**
     * Search for the customerPayment corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerPaymentDTO> search(String query) {
        log.debug("Request to search CustomerPayments for query {}", query);
        return StreamSupport
            .stream(customerPaymentSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerPaymentMapper::toDto)
            .collect(Collectors.toList());
    }
}
