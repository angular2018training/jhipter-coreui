package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderUserRouteTypeDTO;
import vn.nextlogix.service.dto.OrderUserRouteTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderUserRouteType.
 */
public interface OrderUserRouteTypeService {

    /**
     * Save a orderUserRouteType.
     *
     * @param orderUserRouteTypeDTO the entity to save
     * @return the persisted entity
     */
    OrderUserRouteTypeDTO save(OrderUserRouteTypeDTO orderUserRouteTypeDTO);

    /**
     * Get all the orderUserRouteTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderUserRouteTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderUserRouteType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderUserRouteTypeDTO findOne(Long id);

    /**
     * Delete the "id" orderUserRouteType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderUserRouteType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderUserRouteTypeDTO> search(String query, Pageable pageable);

    Page<OrderUserRouteTypeDTO> searchExample( OrderUserRouteTypeSearchDTO searchDTO, Pageable pageable);
    

    }
