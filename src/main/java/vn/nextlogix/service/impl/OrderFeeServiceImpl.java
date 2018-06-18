package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderFeeService;
import vn.nextlogix.domain.OrderFee;
import vn.nextlogix.repository.OrderFeeRepository;
import vn.nextlogix.repository.search.OrderFeeSearchRepository;
import vn.nextlogix.service.dto.OrderFeeDTO;
import vn.nextlogix.service.mapper.OrderFeeMapper;
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
 * Service Implementation for managing OrderFee.
 */
@Service
@Transactional
public class OrderFeeServiceImpl implements OrderFeeService {

    private final Logger log = LoggerFactory.getLogger(OrderFeeServiceImpl.class);

    private final OrderFeeRepository orderFeeRepository;

    private final OrderFeeMapper orderFeeMapper;

    private final OrderFeeSearchRepository orderFeeSearchRepository;

    public OrderFeeServiceImpl(OrderFeeRepository orderFeeRepository, OrderFeeMapper orderFeeMapper, OrderFeeSearchRepository orderFeeSearchRepository) {
        this.orderFeeRepository = orderFeeRepository;
        this.orderFeeMapper = orderFeeMapper;
        this.orderFeeSearchRepository = orderFeeSearchRepository;
    }

    /**
     * Save a orderFee.
     *
     * @param orderFeeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderFeeDTO save(OrderFeeDTO orderFeeDTO) {
        log.debug("Request to save OrderFee : {}", orderFeeDTO);
        OrderFee orderFee = orderFeeMapper.toEntity(orderFeeDTO);
        orderFee = orderFeeRepository.save(orderFee);
        OrderFeeDTO result = orderFeeMapper.toDto(orderFee);
        orderFeeSearchRepository.save(orderFee);
        return result;
    }

    /**
     * Get all the orderFees.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderFeeDTO> findAll() {
        log.debug("Request to get all OrderFees");
        return orderFeeRepository.findAll().stream()
            .map(orderFeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderFee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderFeeDTO findOne(Long id) {
        log.debug("Request to get OrderFee : {}", id);
        OrderFee orderFee = orderFeeRepository.findOne(id);
        return orderFeeMapper.toDto(orderFee);
    }

    /**
     * Delete the orderFee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderFee : {}", id);
        orderFeeRepository.delete(id);
        orderFeeSearchRepository.delete(id);
    }

    /**
     * Search for the orderFee corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderFeeDTO> search(String query) {
        log.debug("Request to search OrderFees for query {}", query);
        return StreamSupport
            .stream(orderFeeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderFeeMapper::toDto)
            .collect(Collectors.toList());
    }
}
