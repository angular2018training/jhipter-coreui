package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderDeliveryDTO;
import vn.nextlogix.service.dto.OrderDeliverySearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderDeliveryDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderDeliveryDTO> search(String query, Pageable pageable);

    Page<OrderDeliveryDTO> searchExample( OrderDeliverySearchDTO searchDTO, Pageable pageable);
    

    }
