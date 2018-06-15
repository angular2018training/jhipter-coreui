package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderMainService;
import vn.nextlogix.domain.OrderMain;
import vn.nextlogix.repository.OrderMainRepository;
import vn.nextlogix.repository.search.OrderMainSearchRepository;
import vn.nextlogix.service.dto.OrderMainDTO;
import vn.nextlogix.service.mapper.OrderMainMapper;
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
 * Service Implementation for managing OrderMain.
 */
@Service
@Transactional
public class OrderMainServiceImpl implements OrderMainService {

    private final Logger log = LoggerFactory.getLogger(OrderMainServiceImpl.class);

    private final OrderMainRepository orderMainRepository;

    private final OrderMainMapper orderMainMapper;

    private final OrderMainSearchRepository orderMainSearchRepository;

    public OrderMainServiceImpl(OrderMainRepository orderMainRepository, OrderMainMapper orderMainMapper, OrderMainSearchRepository orderMainSearchRepository) {
        this.orderMainRepository = orderMainRepository;
        this.orderMainMapper = orderMainMapper;
        this.orderMainSearchRepository = orderMainSearchRepository;
    }

    /**
     * Save a orderMain.
     *
     * @param orderMainDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderMainDTO save(OrderMainDTO orderMainDTO) {
        log.debug("Request to save OrderMain : {}", orderMainDTO);
        OrderMain orderMain = orderMainMapper.toEntity(orderMainDTO);
        orderMain = orderMainRepository.save(orderMain);
        OrderMainDTO result = orderMainMapper.toDto(orderMain);
        orderMainSearchRepository.save(orderMain);
        return result;
    }

    /**
     * Get all the orderMains.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderMainDTO> findAll() {
        log.debug("Request to get all OrderMains");
        return orderMainRepository.findAll().stream()
            .map(orderMainMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderMain by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderMainDTO findOne(Long id) {
        log.debug("Request to get OrderMain : {}", id);
        OrderMain orderMain = orderMainRepository.findOne(id);
        return orderMainMapper.toDto(orderMain);
    }

    /**
     * Delete the orderMain by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderMain : {}", id);
        orderMainRepository.delete(id);
        orderMainSearchRepository.delete(id);
    }

    /**
     * Search for the orderMain corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderMainDTO> search(String query) {
        log.debug("Request to search OrderMains for query {}", query);
        return StreamSupport
            .stream(orderMainSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderMainMapper::toDto)
            .collect(Collectors.toList());
    }
}
