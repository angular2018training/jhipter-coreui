package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerWarehouseDTO;
import vn.nextlogix.service.dto.CustomerWarehouseSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerWarehouse.
 */
public interface CustomerWarehouseService {

    /**
     * Save a customerWarehouse.
     *
     * @param customerWarehouseDTO the entity to save
     * @return the persisted entity
     */
    CustomerWarehouseDTO save(CustomerWarehouseDTO customerWarehouseDTO);

    /**
     * Get all the customerWarehouses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerWarehouseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerWarehouse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerWarehouseDTO findOne(Long id);

    /**
     * Delete the "id" customerWarehouse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerWarehouse corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerWarehouseDTO> search(String query, Pageable pageable);

    Page<CustomerWarehouseDTO> searchExample( CustomerWarehouseSearchDTO searchDTO, Pageable pageable);
    

    }
