package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderConsigneeDTO;
import java.util.List;

/**
 * Service Interface for managing OrderConsignee.
 */
public interface OrderConsigneeService {

    /**
     * Save a orderConsignee.
     *
     * @param orderConsigneeDTO the entity to save
     * @return the persisted entity
     */
    OrderConsigneeDTO save(OrderConsigneeDTO orderConsigneeDTO);

    /**
     * Get all the orderConsignees.
     *
     * @return the list of entities
     */
    List<OrderConsigneeDTO> findAll();

    /**
     * Get the "id" orderConsignee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderConsigneeDTO findOne(Long id);

    /**
     * Delete the "id" orderConsignee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderConsignee corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderConsigneeDTO> search(String query);
}
