package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationCodService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationCodDTO;
import vn.nextlogix.service.dto.QuotationCodSearchDTO;
import vn.nextlogix.service.dto.QuotationCodCriteria;
import vn.nextlogix.service.QuotationCodQueryService;
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
 * REST controller for managing QuotationCod.
 */
@RestController
@RequestMapping("/api")
public class QuotationCodResource {

    private final Logger log = LoggerFactory.getLogger(QuotationCodResource.class);

    private static final String ENTITY_NAME = "quotationCod";

    private final QuotationCodService quotationCodService;

    private final QuotationCodQueryService quotationCodQueryService;

    public QuotationCodResource(QuotationCodService quotationCodService, QuotationCodQueryService quotationCodQueryService     ) {
        this.quotationCodService = quotationCodService;
        this.quotationCodQueryService = quotationCodQueryService;


    }

    /**
     * POST  /quotation-cods : Create a new quotationCod.
     *
     * @param quotationCodDTO the quotationCodDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationCodDTO, or with status 400 (Bad Request) if the quotationCod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-cods")
    @Timed
    public ResponseEntity<QuotationCodDTO> createQuotationCod(@Valid @RequestBody QuotationCodDTO quotationCodDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationCod : {}", quotationCodDTO);
        if (quotationCodDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationCod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationCodDTO result = quotationCodService.save(quotationCodDTO);
        return ResponseEntity.created(new URI("/api/quotation-cods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-cods : Updates an existing quotationCod.
     *
     * @param quotationCodDTO the quotationCodDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationCodDTO,
     * or with status 400 (Bad Request) if the quotationCodDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationCodDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-cods")
    @Timed
    public ResponseEntity<QuotationCodDTO> updateQuotationCod(@Valid @RequestBody QuotationCodDTO quotationCodDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationCod : {}", quotationCodDTO);
        if (quotationCodDTO.getId() == null) {
            return createQuotationCod(quotationCodDTO);
        }
        QuotationCodDTO result = quotationCodService.save(quotationCodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationCodDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-cods : get all the quotationCods.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationCods in body
     */
    @GetMapping("/quotation-cods")
    @Timed
    public ResponseEntity<List<QuotationCodDTO>> getAllQuotationCods(QuotationCodCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationCods by criteria: {}", criteria);
        Page<QuotationCodDTO> page = quotationCodQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-cods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-cods/:id : get the "id" quotationCod.
     *
     * @param id the id of the quotationCodDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationCodDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-cods/{id}")
    @Timed
    public ResponseEntity<QuotationCodDTO> getQuotationCod(@PathVariable Long id) {
        log.debug("REST request to get QuotationCod : {}", id);
        QuotationCodDTO quotationCodDTO = quotationCodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationCodDTO));
    }

    /**
     * DELETE  /quotation-cods/:id : delete the "id" quotationCod.
     *
     * @param id the id of the quotationCodDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-cods/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationCod(@PathVariable Long id) {
        log.debug("REST request to delete QuotationCod : {}", id);
        quotationCodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-cods?query=:query : search for the quotationCod corresponding
     * to the query.
     *
     * @param query the query of the quotationCod search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-cods")
    @Timed
    public ResponseEntity<List<QuotationCodDTO>> searchQuotationCods(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationCods for query {}", query);
        Page<QuotationCodDTO> page = quotationCodService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-cods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-cods")
    @Timed
    public ResponseEntity<List<QuotationCodDTO>> searchExampleQuotationCods(QuotationCodSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationCods for searchDTO {}", searchDTO);
        Page<QuotationCodDTO> page = quotationCodService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-cods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
