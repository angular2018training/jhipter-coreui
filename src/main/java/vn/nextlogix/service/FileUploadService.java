package vn.nextlogix.service;

import vn.nextlogix.service.dto.FileUploadDTO;
import vn.nextlogix.service.dto.FileUploadSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing FileUpload.
 */
public interface FileUploadService {

    /**
     * Save a fileUpload.
     *
     * @param fileUploadDTO the entity to save
     * @return the persisted entity
     */
    FileUploadDTO save(FileUploadDTO fileUploadDTO);

    /**
     * Get all the fileUploads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FileUploadDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileUpload.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FileUploadDTO findOne(Long id);

    /**
     * Delete the "id" fileUpload.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the fileUpload corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FileUploadDTO> search(String query, Pageable pageable);

    Page<FileUploadDTO> searchExample( FileUploadSearchDTO searchDTO, Pageable pageable);

	FileUploadDTO findByHashedId(String hashedId);

	void deleteByHashedId(String hashedId);
    

    }
