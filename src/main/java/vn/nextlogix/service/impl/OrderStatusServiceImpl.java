package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderStatusService;
import vn.nextlogix.domain.OrderStatus;
import vn.nextlogix.repository.OrderStatusRepository;
import vn.nextlogix.repository.search.OrderStatusSearchRepository;
import vn.nextlogix.service.dto.OrderStatusDTO;
import vn.nextlogix.service.mapper.OrderStatusMapper;
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
 * Service Implementation for managing OrderStatus.
 */
@Service
@Transactional
public class OrderStatusServiceImpl implements OrderStatusService {

    private final Logger log = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusMapper orderStatusMapper;

    private final OrderStatusSearchRepository orderStatusSearchRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository, OrderStatusMapper orderStatusMapper, OrderStatusSearchRepository orderStatusSearchRepository) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapper = orderStatusMapper;
        this.orderStatusSearchRepository = orderStatusSearchRepository;
    }

    /**
     * Save a orderStatus.
     *
     * @param orderStatusDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderStatusDTO save(OrderStatusDTO orderStatusDTO) {
        log.debug("Request to save OrderStatus : {}", orderStatusDTO);
        OrderStatus orderStatus = orderStatusMapper.toEntity(orderStatusDTO);
        orderStatus = orderStatusRepository.save(orderStatus);
        OrderStatusDTO result = orderStatusMapper.toDto(orderStatus);
        orderStatusSearchRepository.save(orderStatus);
        return result;
    }

    /**
     * Get all the orderStatuses.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderStatusDTO> findAll() {
        log.debug("Request to get all OrderStatuses");
        return orderStatusRepository.findAll().stream()
            .map(orderStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderStatus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderStatusDTO findOne(Long id) {
        log.debug("Request to get OrderStatus : {}", id);
        OrderStatus orderStatus = orderStatusRepository.findOne(id);
        return orderStatusMapper.toDto(orderStatus);
    }

    /**
     * Delete the orderStatus by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderStatus : {}", id);
        orderStatusRepository.delete(id);
        orderStatusSearchRepository.delete(id);
    }

    /**
     * Search for the orderStatus corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderStatusDTO> search(String query) {
        log.debug("Request to search OrderStatuses for query {}", query);
        return StreamSupport
            .stream(orderStatusSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderStatusMapper::toDto)
            .collect(Collectors.toList());
    }
}
