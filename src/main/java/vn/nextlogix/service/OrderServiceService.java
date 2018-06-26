package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderServiceDTO;
import vn.nextlogix.service.dto.OrderServiceSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderServiceDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderServiceDTO> search(String query, Pageable pageable);

    Page<OrderServiceDTO> searchExample( OrderServiceSearchDTO searchDTO, Pageable pageable);
    

    }
