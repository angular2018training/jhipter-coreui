package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderDeliveryService;
import vn.nextlogix.domain.OrderDelivery;
import vn.nextlogix.repository.OrderDeliveryRepository;
import vn.nextlogix.repository.search.OrderDeliverySearchRepository;
import vn.nextlogix.service.dto.OrderDeliveryDTO;
import vn.nextlogix.service.mapper.OrderDeliveryMapper;
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
 * Service Implementation for managing OrderDelivery.
 */
@Service
@Transactional
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryServiceImpl.class);

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryMapper orderDeliveryMapper;

    private final OrderDeliverySearchRepository orderDeliverySearchRepository;

    public OrderDeliveryServiceImpl(OrderDeliveryRepository orderDeliveryRepository, OrderDeliveryMapper orderDeliveryMapper, OrderDeliverySearchRepository orderDeliverySearchRepository) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliveryMapper = orderDeliveryMapper;
        this.orderDeliverySearchRepository = orderDeliverySearchRepository;
    }

    /**
     * Save a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderDeliveryDTO save(OrderDeliveryDTO orderDeliveryDTO) {
        log.debug("Request to save OrderDelivery : {}", orderDeliveryDTO);
        OrderDelivery orderDelivery = orderDeliveryMapper.toEntity(orderDeliveryDTO);
        orderDelivery = orderDeliveryRepository.save(orderDelivery);
        OrderDeliveryDTO result = orderDeliveryMapper.toDto(orderDelivery);
        orderDeliverySearchRepository.save(orderDelivery);
        return result;
    }

    /**
     * Get all the orderDeliveries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderDeliveryDTO> findAll() {
        log.debug("Request to get all OrderDeliveries");
        return orderDeliveryRepository.findAll().stream()
            .map(orderDeliveryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderDelivery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderDeliveryDTO findOne(Long id) {
        log.debug("Request to get OrderDelivery : {}", id);
        OrderDelivery orderDelivery = orderDeliveryRepository.findOne(id);
        return orderDeliveryMapper.toDto(orderDelivery);
    }

    /**
     * Delete the orderDelivery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderDelivery : {}", id);
        orderDeliveryRepository.delete(id);
        orderDeliverySearchRepository.delete(id);
    }

    /**
     * Search for the orderDelivery corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderDeliveryDTO> search(String query) {
        log.debug("Request to search OrderDeliveries for query {}", query);
        return StreamSupport
            .stream(orderDeliverySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderDeliveryMapper::toDto)
            .collect(Collectors.toList());
    }
}
