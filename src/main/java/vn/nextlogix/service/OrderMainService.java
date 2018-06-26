package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderMainDTO;
import vn.nextlogix.service.dto.OrderMainSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderMain.
 */
public interface OrderMainService {

    /**
     * Save a orderMain.
     *
     * @param orderMainDTO the entity to save
     * @return the persisted entity
     */
    OrderMainDTO save(OrderMainDTO orderMainDTO);

    /**
     * Get all the orderMains.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderMainDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderMain.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderMainDTO findOne(Long id);

    /**
     * Delete the "id" orderMain.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderMain corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderMainDTO> search(String query, Pageable pageable);

    Page<OrderMainDTO> searchExample( OrderMainSearchDTO searchDTO, Pageable pageable);
    

    }
