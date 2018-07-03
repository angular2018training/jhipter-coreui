package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderServicesTypeDTO;
import vn.nextlogix.service.dto.OrderServicesTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderServicesType.
 */
public interface OrderServicesTypeService {

    /**
     * Save a orderServicesType.
     *
     * @param orderServicesTypeDTO the entity to save
     * @return the persisted entity
     */
    OrderServicesTypeDTO save(OrderServicesTypeDTO orderServicesTypeDTO);

    /**
     * Get all the orderServicesTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderServicesTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderServicesType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderServicesTypeDTO findOne(Long id);

    /**
     * Delete the "id" orderServicesType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderServicesType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderServicesTypeDTO> search(String query, Pageable pageable);

    Page<OrderServicesTypeDTO> searchExample( OrderServicesTypeSearchDTO searchDTO, Pageable pageable);
    

    }
