package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderStatusDTO;
import java.util.List;

/**
 * Service Interface for managing OrderStatus.
 */
public interface OrderStatusService {

    /**
     * Save a orderStatus.
     *
     * @param orderStatusDTO the entity to save
     * @return the persisted entity
     */
    OrderStatusDTO save(OrderStatusDTO orderStatusDTO);

    /**
     * Get all the orderStatuses.
     *
     * @return the list of entities
     */
    List<OrderStatusDTO> findAll();

    /**
     * Get the "id" orderStatus.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderStatusDTO findOne(Long id);

    /**
     * Delete the "id" orderStatus.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderStatus corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderStatusDTO> search(String query);
}
