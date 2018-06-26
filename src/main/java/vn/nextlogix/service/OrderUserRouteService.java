package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderUserRouteDTO;
import vn.nextlogix.service.dto.OrderUserRouteSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderUserRoute.
 */
public interface OrderUserRouteService {

    /**
     * Save a orderUserRoute.
     *
     * @param orderUserRouteDTO the entity to save
     * @return the persisted entity
     */
    OrderUserRouteDTO save(OrderUserRouteDTO orderUserRouteDTO);

    /**
     * Get all the orderUserRoutes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderUserRouteDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderUserRoute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderUserRouteDTO findOne(Long id);

    /**
     * Delete the "id" orderUserRoute.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderUserRoute corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderUserRouteDTO> search(String query, Pageable pageable);

    Page<OrderUserRouteDTO> searchExample( OrderUserRouteSearchDTO searchDTO, Pageable pageable);
    

    }
