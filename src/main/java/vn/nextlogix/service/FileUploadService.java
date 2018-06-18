package vn.nextlogix.service;

import vn.nextlogix.service.dto.FileUploadDTO;
import java.util.List;

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
     * @return the list of entities
     */
    List<FileUploadDTO> findAll();

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
     * @return the list of entities
     */
    List<FileUploadDTO> search(String query);
}
