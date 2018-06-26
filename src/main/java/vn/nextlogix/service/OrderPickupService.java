package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderPickupDTO;
import vn.nextlogix.service.dto.OrderPickupSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderPickupDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderPickupDTO> search(String query, Pageable pageable);

    Page<OrderPickupDTO> searchExample( OrderPickupSearchDTO searchDTO, Pageable pageable);
    

    }
