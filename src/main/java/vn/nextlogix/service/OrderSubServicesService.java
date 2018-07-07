package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderSubServicesDTO;
import vn.nextlogix.service.dto.OrderSubServicesSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderSubServices.
 */
public interface OrderSubServicesService {

    /**
     * Save a orderSubServices.
     *
     * @param orderSubServicesDTO the entity to save
     * @return the persisted entity
     */
    OrderSubServicesDTO save(OrderSubServicesDTO orderSubServicesDTO);

    /**
     * Get all the orderSubServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderSubServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderSubServices.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderSubServicesDTO findOne(Long id);

    /**
     * Delete the "id" orderSubServices.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderSubServices corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderSubServicesDTO> search(String query, Pageable pageable);

    Page<OrderSubServicesDTO> searchExample( OrderSubServicesSearchDTO searchDTO, Pageable pageable);
    

    }
