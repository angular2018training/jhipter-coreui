package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderServiceDTO;
import java.util.List;

/**
 * Service Interface for managing OrderService.
 */
public interface OrderServiceService {

    /**
     * Save a orderService.
     *
     * @param orderServiceDTO the entity to save
     * @return the persisted entity
     */
    OrderServiceDTO save(OrderServiceDTO orderServiceDTO);

    /**
     * Get all the orderServices.
     *
     * @return the list of entities
     */
    List<OrderServiceDTO> findAll();

    /**
     * Get the "id" orderService.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderServiceDTO findOne(Long id);

    /**
     * Delete the "id" orderService.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderService corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderServiceDTO> search(String query);
}
