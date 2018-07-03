package vn.nextlogix.service;

import vn.nextlogix.service.dto.UserPostOfficeDTO;
import vn.nextlogix.service.dto.UserPostOfficeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UserPostOffice.
 */
public interface UserPostOfficeService {

    /**
     * Save a userPostOffice.
     *
     * @param userPostOfficeDTO the entity to save
     * @return the persisted entity
     */
    UserPostOfficeDTO save(UserPostOfficeDTO userPostOfficeDTO);

    /**
     * Get all the userPostOffices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserPostOfficeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userPostOffice.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserPostOfficeDTO findOne(Long id);

    /**
     * Delete the "id" userPostOffice.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userPostOffice corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserPostOfficeDTO> search(String query, Pageable pageable);

    Page<UserPostOfficeDTO> searchExample( UserPostOfficeSearchDTO searchDTO, Pageable pageable);
    

    }
