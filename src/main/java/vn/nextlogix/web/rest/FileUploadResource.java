package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.FileUploadService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.FileUploadDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing FileUpload.
 */
@RestController
@RequestMapping("/api")
public class FileUploadResource {

    private final Logger log = LoggerFactory.getLogger(FileUploadResource.class);

    private static final String ENTITY_NAME = "fileUpload";

    private final FileUploadService fileUploadService;

    public FileUploadResource(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    /**
     * POST  /file-uploads : Create a new fileUpload.
     *
     * @param fileUploadDTO the fileUploadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fileUploadDTO, or with status 400 (Bad Request) if the fileUpload has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/file-uploads")
    @Timed
    public ResponseEntity<FileUploadDTO> createFileUpload(@Valid @RequestBody FileUploadDTO fileUploadDTO) throws URISyntaxException {
        log.debug("REST request to save FileUpload : {}", fileUploadDTO);
        if (fileUploadDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileUpload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileUploadDTO result = fileUploadService.save(fileUploadDTO);
        return ResponseEntity.created(new URI("/api/file-uploads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /file-uploads : Updates an existing fileUpload.
     *
     * @param fileUploadDTO the fileUploadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fileUploadDTO,
     * or with status 400 (Bad Request) if the fileUploadDTO is not valid,
     * or with status 500 (Internal Server Error) if the fileUploadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/file-uploads")
    @Timed
    public ResponseEntity<FileUploadDTO> updateFileUpload(@Valid @RequestBody FileUploadDTO fileUploadDTO) throws URISyntaxException {
        log.debug("REST request to update FileUpload : {}", fileUploadDTO);
        if (fileUploadDTO.getId() == null) {
            return createFileUpload(fileUploadDTO);
        }
        FileUploadDTO result = fileUploadService.save(fileUploadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fileUploadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /file-uploads : get all the fileUploads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fileUploads in body
     */
    @GetMapping("/file-uploads")
    @Timed
    public List<FileUploadDTO> getAllFileUploads() {
        log.debug("REST request to get all FileUploads");
        return fileUploadService.findAll();
        }

    /**
     * GET  /file-uploads/:id : get the "id" fileUpload.
     *
     * @param id the id of the fileUploadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fileUploadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/file-uploads/{id}")
    @Timed
    public ResponseEntity<FileUploadDTO> getFileUpload(@PathVariable Long id) {
        log.debug("REST request to get FileUpload : {}", id);
        FileUploadDTO fileUploadDTO = fileUploadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fileUploadDTO));
    }

    /**
     * DELETE  /file-uploads/:id : delete the "id" fileUpload.
     *
     * @param id the id of the fileUploadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/file-uploads/{id}")
    @Timed
    public ResponseEntity<Void> deleteFileUpload(@PathVariable Long id) {
        log.debug("REST request to delete FileUpload : {}", id);
        fileUploadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/file-uploads?query=:query : search for the fileUpload corresponding
     * to the query.
     *
     * @param query the query of the fileUpload search
     * @return the result of the search
     */
    @GetMapping("/_search/file-uploads")
    @Timed
    public List<FileUploadDTO> searchFileUploads(@RequestParam String query) {
        log.debug("REST request to search FileUploads for query {}", query);
        return fileUploadService.search(query);
    }

}
