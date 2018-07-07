package vn.nextlogix.service;

import vn.nextlogix.service.dto.RegionDTO;
import vn.nextlogix.service.dto.RegionSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Region.
 */
public interface RegionService {

    /**
     * Save a region.
     *
     * @param regionDTO the entity to save
     * @return the persisted entity
     */
    RegionDTO save(RegionDTO regionDTO);

    /**
     * Get all the regions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RegionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" region.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RegionDTO findOne(Long id);

    /**
     * Delete the "id" region.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the region corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RegionDTO> search(String query, Pageable pageable);

    Page<RegionDTO> searchExample( RegionSearchDTO searchDTO, Pageable pageable);
    

    }
