package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderConsigneeService;
import vn.nextlogix.domain.OrderConsignee;
import vn.nextlogix.repository.OrderConsigneeRepository;
import vn.nextlogix.repository.search.OrderConsigneeSearchRepository;
import vn.nextlogix.service.dto.OrderConsigneeDTO;
import vn.nextlogix.service.mapper.OrderConsigneeMapper;
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
 * Service Implementation for managing OrderConsignee.
 */
@Service
@Transactional
public class OrderConsigneeServiceImpl implements OrderConsigneeService {

    private final Logger log = LoggerFactory.getLogger(OrderConsigneeServiceImpl.class);

    private final OrderConsigneeRepository orderConsigneeRepository;

    private final OrderConsigneeMapper orderConsigneeMapper;

    private final OrderConsigneeSearchRepository orderConsigneeSearchRepository;

    public OrderConsigneeServiceImpl(OrderConsigneeRepository orderConsigneeRepository, OrderConsigneeMapper orderConsigneeMapper, OrderConsigneeSearchRepository orderConsigneeSearchRepository) {
        this.orderConsigneeRepository = orderConsigneeRepository;
        this.orderConsigneeMapper = orderConsigneeMapper;
        this.orderConsigneeSearchRepository = orderConsigneeSearchRepository;
    }

    /**
     * Save a orderConsignee.
     *
     * @param orderConsigneeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderConsigneeDTO save(OrderConsigneeDTO orderConsigneeDTO) {
        log.debug("Request to save OrderConsignee : {}", orderConsigneeDTO);
        OrderConsignee orderConsignee = orderConsigneeMapper.toEntity(orderConsigneeDTO);
        orderConsignee = orderConsigneeRepository.save(orderConsignee);
        OrderConsigneeDTO result = orderConsigneeMapper.toDto(orderConsignee);
        orderConsigneeSearchRepository.save(orderConsignee);
        return result;
    }

    /**
     * Get all the orderConsignees.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderConsigneeDTO> findAll() {
        log.debug("Request to get all OrderConsignees");
        return orderConsigneeRepository.findAll().stream()
            .map(orderConsigneeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderConsignee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderConsigneeDTO findOne(Long id) {
        log.debug("Request to get OrderConsignee : {}", id);
        OrderConsignee orderConsignee = orderConsigneeRepository.findOne(id);
        return orderConsigneeMapper.toDto(orderConsignee);
    }

    /**
     * Delete the orderConsignee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderConsignee : {}", id);
        orderConsigneeRepository.delete(id);
        orderConsigneeSearchRepository.delete(id);
    }

    /**
     * Search for the orderConsignee corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderConsigneeDTO> search(String query) {
        log.debug("Request to search OrderConsignees for query {}", query);
        return StreamSupport
            .stream(orderConsigneeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(orderConsigneeMapper::toDto)
            .collect(Collectors.toList());
    }
}
