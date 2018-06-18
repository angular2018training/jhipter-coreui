package vn.nextlogix.service.impl;

import vn.nextlogix.service.FileUploadService;
import vn.nextlogix.domain.FileUpload;
import vn.nextlogix.repository.FileUploadRepository;
import vn.nextlogix.repository.search.FileUploadSearchRepository;
import vn.nextlogix.service.dto.FileUploadDTO;
import vn.nextlogix.service.mapper.FileUploadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FileUpload.
 */
@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    private final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private final FileUploadRepository fileUploadRepository;

    private final FileUploadMapper fileUploadMapper;

    private final FileUploadSearchRepository fileUploadSearchRepository;

    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository, FileUploadMapper fileUploadMapper, FileUploadSearchRepository fileUploadSearchRepository) {
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadMapper = fileUploadMapper;
        this.fileUploadSearchRepository = fileUploadSearchRepository;
    }

    /**
     * Save a fileUpload.
     *
     * @param fileUploadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FileUploadDTO save(FileUploadDTO fileUploadDTO) {
        log.debug("Request to save FileUpload : {}", fileUploadDTO);
        FileUpload fileUpload = fileUploadMapper.toEntity(fileUploadDTO);
        fileUpload = fileUploadRepository.save(fileUpload);
        FileUploadDTO result = fileUploadMapper.toDto(fileUpload);
        fileUploadSearchRepository.save(fileUpload);
        return result;
    }

    /**
     * Get all the fileUploads.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FileUploadDTO> findAll() {
        log.debug("Request to get all FileUploads");
        return fileUploadRepository.findAll().stream()
            .map(fileUploadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fileUpload by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FileUploadDTO findOne(Long id) {
        log.debug("Request to get FileUpload : {}", id);
        FileUpload fileUpload = fileUploadRepository.findOne(id);
        return fileUploadMapper.toDto(fileUpload);
    }

    /**
     * Delete the fileUpload by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileUpload : {}", id);
        fileUploadRepository.delete(id);
        fileUploadSearchRepository.delete(id);
    }

    /**
     * Search for the fileUpload corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FileUploadDTO> search(String query) {
        log.debug("Request to search FileUploads for query {}", query);
        return StreamSupport
            .stream(fileUploadSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(fileUploadMapper::toDto)
            .collect(Collectors.toList());
    }
}
