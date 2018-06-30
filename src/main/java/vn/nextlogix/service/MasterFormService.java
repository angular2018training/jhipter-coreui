package vn.nextlogix.service;

import vn.nextlogix.service.dto.MasterFormDTO;
import vn.nextlogix.service.dto.MasterFormSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MasterForm.
 */
public interface MasterFormService {

    /**
     * Save a masterForm.
     *
     * @param masterFormDTO the entity to save
     * @return the persisted entity
     */
    MasterFormDTO save(MasterFormDTO masterFormDTO);

    /**
     * Get all the masterForms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MasterFormDTO> findAll(Pageable pageable);

    /**
     * Get the "id" masterForm.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MasterFormDTO findOne(Long id);

    /**
     * Delete the "id" masterForm.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the masterForm corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MasterFormDTO> search(String query, Pageable pageable);

    Page<MasterFormDTO> searchExample( MasterFormSearchDTO searchDTO, Pageable pageable);
    

    }
