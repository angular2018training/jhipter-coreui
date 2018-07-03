package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerServicesDTO;
import vn.nextlogix.service.dto.CustomerServicesSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerServices.
 */
public interface CustomerServicesService {

    /**
     * Save a customerServices.
     *
     * @param customerServicesDTO the entity to save
     * @return the persisted entity
     */
    CustomerServicesDTO save(CustomerServicesDTO customerServicesDTO);

    /**
     * Get all the customerServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerServices.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerServicesDTO findOne(Long id);

    /**
     * Delete the "id" customerServices.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerServices corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerServicesDTO> search(String query, Pageable pageable);

    Page<CustomerServicesDTO> searchExample( CustomerServicesSearchDTO searchDTO, Pageable pageable);
    

    }
