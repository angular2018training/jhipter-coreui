package vn.nextlogix.service;

import vn.nextlogix.service.dto.WardDTO;
import vn.nextlogix.service.dto.WardSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WardDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WardDTO> search(String query, Pageable pageable);

    Page<WardDTO> searchExample( WardSearchDTO searchDTO, Pageable pageable);
    

    }
