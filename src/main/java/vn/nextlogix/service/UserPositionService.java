package vn.nextlogix.service;

import vn.nextlogix.service.dto.UserPositionDTO;
import vn.nextlogix.service.dto.UserPositionSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserPositionDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserPositionDTO> search(String query, Pageable pageable);

    Page<UserPositionDTO> searchExample( UserPositionSearchDTO searchDTO, Pageable pageable);
    

    }
