package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderFeeDTO;
import java.util.List;

/**
 * Service Interface for managing OrderFee.
 */
public interface OrderFeeService {

    /**
     * Save a orderFee.
     *
     * @param orderFeeDTO the entity to save
     * @return the persisted entity
     */
    OrderFeeDTO save(OrderFeeDTO orderFeeDTO);

    /**
     * Get all the orderFees.
     *
     * @return the list of entities
     */
    List<OrderFeeDTO> findAll();

    /**
     * Get the "id" orderFee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderFeeDTO findOne(Long id);

    /**
     * Delete the "id" orderFee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderFee corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OrderFeeDTO> search(String query);
}
