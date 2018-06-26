package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderFeeDTO;
import vn.nextlogix.service.dto.OrderFeeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderFeeDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderFeeDTO> search(String query, Pageable pageable);

    Page<OrderFeeDTO> searchExample( OrderFeeSearchDTO searchDTO, Pageable pageable);
    

    }
