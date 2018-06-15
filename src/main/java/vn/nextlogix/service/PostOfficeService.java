package vn.nextlogix.service;

import vn.nextlogix.service.dto.PostOfficeDTO;
import java.util.List;

/**
 * Service Interface for managing PostOffice.
 */
public interface PostOfficeService {

    /**
     * Save a postOffice.
     *
     * @param postOfficeDTO the entity to save
     * @return the persisted entity
     */
    PostOfficeDTO save(PostOfficeDTO postOfficeDTO);

    /**
     * Get all the postOffices.
     *
     * @return the list of entities
     */
    List<PostOfficeDTO> findAll();

    /**
     * Get the "id" postOffice.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PostOfficeDTO findOne(Long id);

    /**
     * Delete the "id" postOffice.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the postOffice corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<PostOfficeDTO> search(String query);
}
