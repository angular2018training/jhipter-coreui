package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderServicesDTO;
import vn.nextlogix.service.dto.OrderServicesSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderServices.
 */
public interface OrderServicesService {

    /**
     * Save a orderServices.
     *
     * @param orderServicesDTO the entity to save
     * @return the persisted entity
     */
    OrderServicesDTO save(OrderServicesDTO orderServicesDTO);

    /**
     * Get all the orderServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderServices.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderServicesDTO findOne(Long id);

    /**
     * Delete the "id" orderServices.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderServices corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderServicesDTO> search(String query, Pageable pageable);

    Page<OrderServicesDTO> searchExample( OrderServicesSearchDTO searchDTO, Pageable pageable);
    

    }
