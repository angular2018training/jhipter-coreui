package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.MasterFormService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.MasterFormDTO;
import vn.nextlogix.service.dto.MasterFormSearchDTO;
import vn.nextlogix.service.dto.MasterFormCriteria;
import vn.nextlogix.service.MasterFormQueryService;
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
 * REST controller for managing MasterForm.
 */
@RestController
@RequestMapping("/api")
public class MasterFormResource {

    private final Logger log = LoggerFactory.getLogger(MasterFormResource.class);

    private static final String ENTITY_NAME = "masterForm";

    private final MasterFormService masterFormService;

    private final MasterFormQueryService masterFormQueryService;

    public MasterFormResource(MasterFormService masterFormService, MasterFormQueryService masterFormQueryService     ) {
        this.masterFormService = masterFormService;
        this.masterFormQueryService = masterFormQueryService;


    }

    /**
     * POST  /master-forms : Create a new masterForm.
     *
     * @param masterFormDTO the masterFormDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new masterFormDTO, or with status 400 (Bad Request) if the masterForm has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/master-forms")
    @Timed
    public ResponseEntity<MasterFormDTO> createMasterForm(@Valid @RequestBody MasterFormDTO masterFormDTO) throws URISyntaxException {
        log.debug("REST request to save MasterForm : {}", masterFormDTO);
        if (masterFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new masterForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MasterFormDTO result = masterFormService.save(masterFormDTO);
        return ResponseEntity.created(new URI("/api/master-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /master-forms : Updates an existing masterForm.
     *
     * @param masterFormDTO the masterFormDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated masterFormDTO,
     * or with status 400 (Bad Request) if the masterFormDTO is not valid,
     * or with status 500 (Internal Server Error) if the masterFormDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/master-forms")
    @Timed
    public ResponseEntity<MasterFormDTO> updateMasterForm(@Valid @RequestBody MasterFormDTO masterFormDTO) throws URISyntaxException {
        log.debug("REST request to update MasterForm : {}", masterFormDTO);
        if (masterFormDTO.getId() == null) {
            return createMasterForm(masterFormDTO);
        }
        MasterFormDTO result = masterFormService.save(masterFormDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, masterFormDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /master-forms : get all the masterForms.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of masterForms in body
     */
    @GetMapping("/master-forms")
    @Timed
    public ResponseEntity<List<MasterFormDTO>> getAllMasterForms(MasterFormCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MasterForms by criteria: {}", criteria);
        Page<MasterFormDTO> page = masterFormQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/master-forms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /master-forms/:id : get the "id" masterForm.
     *
     * @param id the id of the masterFormDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the masterFormDTO, or with status 404 (Not Found)
     */
    @GetMapping("/master-forms/{id}")
    @Timed
    public ResponseEntity<MasterFormDTO> getMasterForm(@PathVariable Long id) {
        log.debug("REST request to get MasterForm : {}", id);
        MasterFormDTO masterFormDTO = masterFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(masterFormDTO));
    }

    /**
     * DELETE  /master-forms/:id : delete the "id" masterForm.
     *
     * @param id the id of the masterFormDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/master-forms/{id}")
    @Timed
    public ResponseEntity<Void> deleteMasterForm(@PathVariable Long id) {
        log.debug("REST request to delete MasterForm : {}", id);
        masterFormService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/master-forms?query=:query : search for the masterForm corresponding
     * to the query.
     *
     * @param query the query of the masterForm search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/master-forms")
    @Timed
    public ResponseEntity<List<MasterFormDTO>> searchMasterForms(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of MasterForms for query {}", query);
        Page<MasterFormDTO> page = masterFormService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/master-forms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/master-forms")
    @Timed
    public ResponseEntity<List<MasterFormDTO>> searchExampleMasterForms(MasterFormSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of MasterForms for searchDTO {}", searchDTO);
        Page<MasterFormDTO> page = masterFormService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/master-forms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
