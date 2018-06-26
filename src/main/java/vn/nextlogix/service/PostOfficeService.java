package vn.nextlogix.service;

import vn.nextlogix.service.dto.PostOfficeDTO;
import vn.nextlogix.service.dto.PostOfficeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PostOfficeDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PostOfficeDTO> search(String query, Pageable pageable);

    Page<PostOfficeDTO> searchExample( PostOfficeSearchDTO searchDTO, Pageable pageable);
    

    }
