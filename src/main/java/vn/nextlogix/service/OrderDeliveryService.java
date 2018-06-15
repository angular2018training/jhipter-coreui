package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderDeliveryDTO;
import java.util.List;

/**
 * Service Interface for managing OrderDelivery.
 */
public interface OrderDeliveryService {

    /**
     * Save a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save
     * @return the persisted entity
     */
    OrderDeliveryDTO save(OrderDeliveryDTO orderDeliveryDTO);

    /**
     * Get all the orderDeliveries.
     *
     * @return the list of entities
     */
    List<OrderDeliveryDTO> findAll();

    /**
     * Get the "id" orderDelivery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderDeliveryDTO findOne(Long id);

    /**
     * Delete the "id" orderDelivery.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderDelivery corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderDeliveryDTO> search(String query);
}
