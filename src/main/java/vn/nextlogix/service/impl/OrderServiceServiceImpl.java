package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderServiceService;
import vn.nextlogix.domain.OrderService;
import vn.nextlogix.repository.OrderServiceRepository;
import vn.nextlogix.repository.search.OrderServiceSearchRepository;
import vn.nextlogix.service.dto.OrderServiceDTO;
import vn.nextlogix.service.mapper.OrderServiceMapper;
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
 * Service Implementation for managing OrderService.
 */
@Service
@Transactional
public class OrderServiceServiceImpl implements OrderServiceService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceServiceImpl.class);

    private final OrderServiceRepository orderServiceRepository;

    private final OrderServiceMapper orderServiceMapper;

    private final OrderServiceSearchRepository orderServiceSearchRepository;

    public OrderServiceServiceImpl(OrderServiceRepository orderServiceRepository, OrderServiceMapper orderServiceMapper, OrderServiceSearchRepository orderServiceSearchRepository) {
        this.orderServiceRepository = orderServiceRepository;
        this.orderServiceMapper = orderServiceMapper;
        this.orderServiceSearchRepository = orderServiceSearchRepository;
    }

    /**
     * Save a orderService.
     *
     * @param orderServiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderServiceDTO save(OrderServiceDTO orderServiceDTO) {
        log.debug("Request to save OrderService : {}", orderServiceDTO);
        OrderService orderService = orderServiceMapper.toEntity(orderServiceDTO);
        orderService = orderServiceRepository.save(orderService);
        OrderServiceDTO result = orderServiceMapper.toDto(orderService);
        orderServiceSearchRepository.save(orderService);
        return result;
    }

    /**
     * Get all the orderServices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceDTO> findAll() {
        log.debug("Request to get all OrderServices");
        return orderServiceRepository.findAll().stream()
            .map(orderServiceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderServiceDTO findOne(Long id) {
        log.debug("Request to get OrderService : {}", id);
        OrderService orderService = orderServiceRepository.findOne(id);
        return orderServiceMapper.toDto(orderService);
    }

    /**
     * Delete the orderService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderService : {}", id);
        orderServiceRepository.delete(id);
        orderServiceSearchRepository.delete(id);
    }

    /**
     * Search for the orderService corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceDTO> search(String query) {
        log.debug("Request to search OrderServices for query {}", query);
        return StreamSupport
            .stream(orderServiceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderServiceMapper::toDto)
            .collect(Collectors.toList());
    }
}
