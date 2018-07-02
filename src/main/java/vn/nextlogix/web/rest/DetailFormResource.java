package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.DetailFormService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.DetailFormDTO;
import vn.nextlogix.service.dto.DetailFormSearchDTO;
import vn.nextlogix.service.dto.DetailFormCriteria;
import vn.nextlogix.service.DetailFormQueryService;
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
 * REST controller for managing DetailForm.
 */
@RestController
@RequestMapping("/api")
public class DetailFormResource {

    private final Logger log = LoggerFactory.getLogger(DetailFormResource.class);

    private static final String ENTITY_NAME = "detailForm";

    private final DetailFormService detailFormService;

    private final DetailFormQueryService detailFormQueryService;

    public DetailFormResource(DetailFormService detailFormService, DetailFormQueryService detailFormQueryService     ) {
        this.detailFormService = detailFormService;
        this.detailFormQueryService = detailFormQueryService;


    }

    /**
     * POST  /detail-forms : Create a new detailForm.
     *
     * @param detailFormDTO the detailFormDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new detailFormDTO, or with status 400 (Bad Request) if the detailForm has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/detail-forms")
    @Timed
    public ResponseEntity<DetailFormDTO> createDetailForm(@Valid @RequestBody DetailFormDTO detailFormDTO) throws URISyntaxException {
        log.debug("REST request to save DetailForm : {}", detailFormDTO);
        if (detailFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailFormDTO result = detailFormService.save(detailFormDTO);
        return ResponseEntity.created(new URI("/api/detail-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /detail-forms : Updates an existing detailForm.
     *
     * @param detailFormDTO the detailFormDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated detailFormDTO,
     * or with status 400 (Bad Request) if the detailFormDTO is not valid,
     * or with status 500 (Internal Server Error) if the detailFormDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/detail-forms")
    @Timed
    public ResponseEntity<DetailFormDTO> updateDetailForm(@Valid @RequestBody DetailFormDTO detailFormDTO) throws URISyntaxException {
        log.debug("REST request to update DetailForm : {}", detailFormDTO);
        if (detailFormDTO.getId() == null) {
            return createDetailForm(detailFormDTO);
        }
        DetailFormDTO result = detailFormService.save(detailFormDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, detailFormDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /detail-forms : get all the detailForms.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of detailForms in body
     */
    @GetMapping("/detail-forms")
    @Timed
    public ResponseEntity<List<DetailFormDTO>> getAllDetailForms(DetailFormCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DetailForms by criteria: {}", criteria);
        Page<DetailFormDTO> page = detailFormQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/detail-forms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /detail-forms/:id : get the "id" detailForm.
     *
     * @param id the id of the detailFormDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the detailFormDTO, or with status 404 (Not Found)
     */
    @GetMapping("/detail-forms/{id}")
    @Timed
    public ResponseEntity<DetailFormDTO> getDetailForm(@PathVariable Long id) {
        log.debug("REST request to get DetailForm : {}", id);
        DetailFormDTO detailFormDTO = detailFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(detailFormDTO));
    }

    /**
     * DELETE  /detail-forms/:id : delete the "id" detailForm.
     *
     * @param id the id of the detailFormDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/detail-forms/{id}")
    @Timed
    public ResponseEntity<Void> deleteDetailForm(@PathVariable Long id) {
        log.debug("REST request to delete DetailForm : {}", id);
        detailFormService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/detail-forms?query=:query : search for the detailForm corresponding
     * to the query.
     *
     * @param query the query of the detailForm search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/detail-forms")
    @Timed
    public ResponseEntity<List<DetailFormDTO>> searchDetailForms(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of DetailForms for query {}", query);
        Page<DetailFormDTO> page = detailFormService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/detail-forms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/detail-forms")
    @Timed
    public ResponseEntity<List<DetailFormDTO>> searchExampleDetailForms(DetailFormSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of DetailForms for searchDTO {}", searchDTO);
        Page<DetailFormDTO> page = detailFormService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/detail-forms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
