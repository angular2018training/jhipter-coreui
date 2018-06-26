package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderStatusDTO;
import vn.nextlogix.service.dto.OrderStatusSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderStatusDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderStatusDTO> search(String query, Pageable pageable);

    Page<OrderStatusDTO> searchExample( OrderStatusSearchDTO searchDTO, Pageable pageable);
    

    }
