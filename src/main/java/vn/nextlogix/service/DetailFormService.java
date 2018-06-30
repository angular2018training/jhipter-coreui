package vn.nextlogix.service;

import vn.nextlogix.service.dto.DetailFormDTO;
import vn.nextlogix.service.dto.DetailFormSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DetailForm.
 */
public interface DetailFormService {

    /**
     * Save a detailForm.
     *
     * @param detailFormDTO the entity to save
     * @return the persisted entity
     */
    DetailFormDTO save(DetailFormDTO detailFormDTO);

    /**
     * Get all the detailForms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DetailFormDTO> findAll(Pageable pageable);

    /**
     * Get the "id" detailForm.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DetailFormDTO findOne(Long id);

    /**
     * Delete the "id" detailForm.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the detailForm corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DetailFormDTO> search(String query, Pageable pageable);

    Page<DetailFormDTO> searchExample( DetailFormSearchDTO searchDTO, Pageable pageable);
    

    }
