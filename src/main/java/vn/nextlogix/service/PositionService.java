package vn.nextlogix.service;

import vn.nextlogix.service.dto.PositionDTO;
import vn.nextlogix.service.dto.PositionSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Position.
 */
public interface PositionService {

    /**
     * Save a position.
     *
     * @param positionDTO the entity to save
     * @return the persisted entity
     */
    PositionDTO save(PositionDTO positionDTO);

    /**
     * Get all the positions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PositionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" position.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PositionDTO findOne(Long id);

    /**
     * Delete the "id" position.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the position corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PositionDTO> search(String query, Pageable pageable);

    Page<PositionDTO> searchExample( PositionSearchDTO searchDTO, Pageable pageable);
    

    }
