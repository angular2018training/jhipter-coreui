package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderMainDTO;
import java.util.List;

/**
 * Service Interface for managing OrderMain.
 */
public interface OrderMainService {

    /**
     * Save a orderMain.
     *
     * @param orderMainDTO the entity to save
     * @return the persisted entity
     */
    OrderMainDTO save(OrderMainDTO orderMainDTO);

    /**
     * Get all the orderMains.
     *
     * @return the list of entities
     */
    List<OrderMainDTO> findAll();

    /**
     * Get the "id" orderMain.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderMainDTO findOne(Long id);

    /**
     * Delete the "id" orderMain.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderMain corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderMainDTO> search(String query);
}
