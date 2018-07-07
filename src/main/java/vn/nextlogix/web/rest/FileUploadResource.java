package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.FileUploadService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.FileUploadDTO;
import vn.nextlogix.service.dto.FileUploadSearchDTO;
import vn.nextlogix.service.dto.FileUploadCriteria;
import vn.nextlogix.service.FileUploadQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public FileUploadResource(FileUploadService fileUploadService   ) {
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
     * GET  /file-uploads/:hashedId : get the "id" fileUpload.
     *
     * @param id the id of the fileUploadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fileUploadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/file-uploads/{hashedId}")
    @Timed
    public ResponseEntity<FileUploadDTO> getFileUpload(@PathVariable String hashedId) {
        log.debug("REST request to get FileUpload : {}", hashedId);
        FileUploadDTO fileUploadDTO = fileUploadService.findByHashedId(hashedId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fileUploadDTO));
    }

    /**
     * DELETE  /file-uploads/:id : delete the "id" fileUpload.
     *
     * @param id the id of the fileUploadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/file-uploads/{hashedId}")
    @Timed
    public ResponseEntity<Void> deleteFileUpload(@PathVariable String hashedId) {
        log.debug("REST request to delete FileUpload : {}", hashedId);
        fileUploadService.deleteByHashedId(hashedId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, hashedId.toString())).build();
    }

   


}
