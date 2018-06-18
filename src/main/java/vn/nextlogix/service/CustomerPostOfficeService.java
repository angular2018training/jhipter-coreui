package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerPostOfficeDTO;
import java.util.List;

/**
 * Service Interface for managing CustomerPostOffice.
 */
public interface CustomerPostOfficeService {

    /**
     * Save a customerPostOffice.
     *
     * @param customerPostOfficeDTO the entity to save
     * @return the persisted entity
     */
    CustomerPostOfficeDTO save(CustomerPostOfficeDTO customerPostOfficeDTO);

    /**
     * Get all the customerPostOffices.
     *
     * @return the list of entities
     */
    List<CustomerPostOfficeDTO> findAll();

    /**
     * Get the "id" customerPostOffice.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerPostOfficeDTO findOne(Long id);

    /**
     * Delete the "id" customerPostOffice.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerPostOffice corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CustomerPostOfficeDTO> search(String query);
}
