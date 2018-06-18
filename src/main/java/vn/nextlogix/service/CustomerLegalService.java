package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerLegalDTO;
import java.util.List;

/**
 * Service Interface for managing CustomerLegal.
 */
public interface CustomerLegalService {

    /**
     * Save a customerLegal.
     *
     * @param customerLegalDTO the entity to save
     * @return the persisted entity
     */
    CustomerLegalDTO save(CustomerLegalDTO customerLegalDTO);

    /**
     * Get all the customerLegals.
     *
     * @return the list of entities
     */
    List<CustomerLegalDTO> findAll();

    /**
     * Get the "id" customerLegal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerLegalDTO findOne(Long id);

    /**
     * Delete the "id" customerLegal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerLegal corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CustomerLegalDTO> search(String query);
}
