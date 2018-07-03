package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerLegalFileUploadDTO;
import vn.nextlogix.service.dto.CustomerLegalFileUploadSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerLegalFileUpload.
 */
public interface CustomerLegalFileUploadService {

    /**
     * Save a customerLegalFileUpload.
     *
     * @param customerLegalFileUploadDTO the entity to save
     * @return the persisted entity
     */
    CustomerLegalFileUploadDTO save(CustomerLegalFileUploadDTO customerLegalFileUploadDTO);

    /**
     * Get all the customerLegalFileUploads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerLegalFileUploadDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerLegalFileUpload.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerLegalFileUploadDTO findOne(Long id);

    /**
     * Delete the "id" customerLegalFileUpload.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerLegalFileUpload corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerLegalFileUploadDTO> search(String query, Pageable pageable);

    Page<CustomerLegalFileUploadDTO> searchExample( CustomerLegalFileUploadSearchDTO searchDTO, Pageable pageable);
    

    }
