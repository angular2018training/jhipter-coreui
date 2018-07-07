package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationTypeDTO;
import vn.nextlogix.service.dto.QuotationTypeSearchDTO;
import vn.nextlogix.service.dto.QuotationTypeCriteria;
import vn.nextlogix.service.QuotationTypeQueryService;
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
 * REST controller for managing QuotationType.
 */
@RestController
@RequestMapping("/api")
public class QuotationTypeResource {

    private final Logger log = LoggerFactory.getLogger(QuotationTypeResource.class);

    private static final String ENTITY_NAME = "quotationType";

    private final QuotationTypeService quotationTypeService;

    private final QuotationTypeQueryService quotationTypeQueryService;

    public QuotationTypeResource(QuotationTypeService quotationTypeService, QuotationTypeQueryService quotationTypeQueryService     ) {
        this.quotationTypeService = quotationTypeService;
        this.quotationTypeQueryService = quotationTypeQueryService;


    }

    /**
     * POST  /quotation-types : Create a new quotationType.
     *
     * @param quotationTypeDTO the quotationTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationTypeDTO, or with status 400 (Bad Request) if the quotationType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-types")
    @Timed
    public ResponseEntity<QuotationTypeDTO> createQuotationType(@Valid @RequestBody QuotationTypeDTO quotationTypeDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationType : {}", quotationTypeDTO);
        if (quotationTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationTypeDTO result = quotationTypeService.save(quotationTypeDTO);
        return ResponseEntity.created(new URI("/api/quotation-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-types : Updates an existing quotationType.
     *
     * @param quotationTypeDTO the quotationTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationTypeDTO,
     * or with status 400 (Bad Request) if the quotationTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-types")
    @Timed
    public ResponseEntity<QuotationTypeDTO> updateQuotationType(@Valid @RequestBody QuotationTypeDTO quotationTypeDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationType : {}", quotationTypeDTO);
        if (quotationTypeDTO.getId() == null) {
            return createQuotationType(quotationTypeDTO);
        }
        QuotationTypeDTO result = quotationTypeService.save(quotationTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-types : get all the quotationTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationTypes in body
     */
    @GetMapping("/quotation-types")
    @Timed
    public ResponseEntity<List<QuotationTypeDTO>> getAllQuotationTypes(QuotationTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationTypes by criteria: {}", criteria);
        Page<QuotationTypeDTO> page = quotationTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-types/:id : get the "id" quotationType.
     *
     * @param id the id of the quotationTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-types/{id}")
    @Timed
    public ResponseEntity<QuotationTypeDTO> getQuotationType(@PathVariable Long id) {
        log.debug("REST request to get QuotationType : {}", id);
        QuotationTypeDTO quotationTypeDTO = quotationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationTypeDTO));
    }

    /**
     * DELETE  /quotation-types/:id : delete the "id" quotationType.
     *
     * @param id the id of the quotationTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationType(@PathVariable Long id) {
        log.debug("REST request to delete QuotationType : {}", id);
        quotationTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-types?query=:query : search for the quotationType corresponding
     * to the query.
     *
     * @param query the query of the quotationType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-types")
    @Timed
    public ResponseEntity<List<QuotationTypeDTO>> searchQuotationTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationTypes for query {}", query);
        Page<QuotationTypeDTO> page = quotationTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-types")
    @Timed
    public ResponseEntity<List<QuotationTypeDTO>> searchExampleQuotationTypes(QuotationTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationTypes for searchDTO {}", searchDTO);
        Page<QuotationTypeDTO> page = quotationTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
