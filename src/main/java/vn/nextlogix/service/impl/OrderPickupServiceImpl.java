package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderPickupService;
import vn.nextlogix.domain.OrderPickup;
import vn.nextlogix.repository.OrderPickupRepository;
import vn.nextlogix.repository.search.OrderPickupSearchRepository;
import vn.nextlogix.service.dto.OrderPickupDTO;
import vn.nextlogix.service.mapper.OrderPickupMapper;
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
 * Service Implementation for managing OrderPickup.
 */
@Service
@Transactional
public class OrderPickupServiceImpl implements OrderPickupService {

    private final Logger log = LoggerFactory.getLogger(OrderPickupServiceImpl.class);

    private final OrderPickupRepository orderPickupRepository;

    private final OrderPickupMapper orderPickupMapper;

    private final OrderPickupSearchRepository orderPickupSearchRepository;

    public OrderPickupServiceImpl(OrderPickupRepository orderPickupRepository, OrderPickupMapper orderPickupMapper, OrderPickupSearchRepository orderPickupSearchRepository) {
        this.orderPickupRepository = orderPickupRepository;
        this.orderPickupMapper = orderPickupMapper;
        this.orderPickupSearchRepository = orderPickupSearchRepository;
    }

    /**
     * Save a orderPickup.
     *
     * @param orderPickupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderPickupDTO save(OrderPickupDTO orderPickupDTO) {
        log.debug("Request to save OrderPickup : {}", orderPickupDTO);
        OrderPickup orderPickup = orderPickupMapper.toEntity(orderPickupDTO);
        orderPickup = orderPickupRepository.save(orderPickup);
        OrderPickupDTO result = orderPickupMapper.toDto(orderPickup);
        orderPickupSearchRepository.save(orderPickup);
        return result;
    }

    /**
     * Get all the orderPickups.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderPickupDTO> findAll() {
        log.debug("Request to get all OrderPickups");
        return orderPickupRepository.findAll().stream()
            .map(orderPickupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderPickup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderPickupDTO findOne(Long id) {
        log.debug("Request to get OrderPickup : {}", id);
        OrderPickup orderPickup = orderPickupRepository.findOne(id);
        return orderPickupMapper.toDto(orderPickup);
    }

    /**
     * Delete the orderPickup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderPickup : {}", id);
        orderPickupRepository.delete(id);
        orderPickupSearchRepository.delete(id);
    }

    /**
     * Search for the orderPickup corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderPickupDTO> search(String query) {
        log.debug("Request to search OrderPickups for query {}", query);
        return StreamSupport
            .stream(orderPickupSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderPickupMapper::toDto)
            .collect(Collectors.toList());
    }
}
