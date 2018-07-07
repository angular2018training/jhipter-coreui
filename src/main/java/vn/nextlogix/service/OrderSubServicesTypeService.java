package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderSubServicesTypeDTO;
import vn.nextlogix.service.dto.OrderSubServicesTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderSubServicesType.
 */
public interface OrderSubServicesTypeService {

    /**
     * Save a orderSubServicesType.
     *
     * @param orderSubServicesTypeDTO the entity to save
     * @return the persisted entity
     */
    OrderSubServicesTypeDTO save(OrderSubServicesTypeDTO orderSubServicesTypeDTO);

    /**
     * Get all the orderSubServicesTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderSubServicesTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderSubServicesType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderSubServicesTypeDTO findOne(Long id);

    /**
     * Delete the "id" orderSubServicesType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderSubServicesType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderSubServicesTypeDTO> search(String query, Pageable pageable);

    Page<OrderSubServicesTypeDTO> searchExample( OrderSubServicesTypeSearchDTO searchDTO, Pageable pageable);
    

    }
