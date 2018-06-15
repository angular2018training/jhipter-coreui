package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderPickupDTO;
import java.util.List;

/**
 * Service Interface for managing OrderPickup.
 */
public interface OrderPickupService {

    /**
     * Save a orderPickup.
     *
     * @param orderPickupDTO the entity to save
     * @return the persisted entity
     */
    OrderPickupDTO save(OrderPickupDTO orderPickupDTO);

    /**
     * Get all the orderPickups.
     *
     * @return the list of entities
     */
    List<OrderPickupDTO> findAll();

    /**
     * Get the "id" orderPickup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderPickupDTO findOne(Long id);

    /**
     * Delete the "id" orderPickup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderPickup corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderPickupDTO> search(String query);
}
