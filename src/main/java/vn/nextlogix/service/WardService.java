package vn.nextlogix.service;

import vn.nextlogix.service.dto.WardDTO;
import java.util.List;

/**
 * Service Interface for managing Ward.
 */
public interface WardService {

    /**
     * Save a ward.
     *
     * @param wardDTO the entity to save
     * @return the persisted entity
     */
    WardDTO save(WardDTO wardDTO);

    /**
     * Get all the wards.
     *
     * @return the list of entities
     */
    List<WardDTO> findAll();

    /**
     * Get the "id" ward.
     *
     * @param id the id of the entity
     * @return the entity
     */
    WardDTO findOne(Long id);

    /**
     * Delete the "id" ward.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the ward corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<WardDTO> search(String query);
}
