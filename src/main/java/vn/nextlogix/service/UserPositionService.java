package vn.nextlogix.service;

import vn.nextlogix.service.dto.UserPositionDTO;
import java.util.List;

/**
 * Service Interface for managing UserPosition.
 */
public interface UserPositionService {

    /**
     * Save a userPosition.
     *
     * @param userPositionDTO the entity to save
     * @return the persisted entity
     */
    UserPositionDTO save(UserPositionDTO userPositionDTO);

    /**
     * Get all the userPositions.
     *
     * @return the list of entities
     */
    List<UserPositionDTO> findAll();

    /**
     * Get the "id" userPosition.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserPositionDTO findOne(Long id);

    /**
     * Delete the "id" userPosition.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userPosition corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<UserPositionDTO> search(String query);
}
