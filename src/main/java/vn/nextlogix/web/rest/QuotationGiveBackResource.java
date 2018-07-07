package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationGiveBackService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationGiveBackDTO;
import vn.nextlogix.service.dto.QuotationGiveBackSearchDTO;
import vn.nextlogix.service.dto.QuotationGiveBackCriteria;
import vn.nextlogix.service.QuotationGiveBackQueryService;
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
 * REST controller for managing QuotationGiveBack.
 */
@RestController
@RequestMapping("/api")
public class QuotationGiveBackResource {

    private final Logger log = LoggerFactory.getLogger(QuotationGiveBackResource.class);

    private static final String ENTITY_NAME = "quotationGiveBack";

    private final QuotationGiveBackService quotationGiveBackService;

    private final QuotationGiveBackQueryService quotationGiveBackQueryService;

    public QuotationGiveBackResource(QuotationGiveBackService quotationGiveBackService, QuotationGiveBackQueryService quotationGiveBackQueryService     ) {
        this.quotationGiveBackService = quotationGiveBackService;
        this.quotationGiveBackQueryService = quotationGiveBackQueryService;


    }

    /**
     * POST  /quotation-give-backs : Create a new quotationGiveBack.
     *
     * @param quotationGiveBackDTO the quotationGiveBackDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationGiveBackDTO, or with status 400 (Bad Request) if the quotationGiveBack has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-give-backs")
    @Timed
    public ResponseEntity<QuotationGiveBackDTO> createQuotationGiveBack(@Valid @RequestBody QuotationGiveBackDTO quotationGiveBackDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationGiveBack : {}", quotationGiveBackDTO);
        if (quotationGiveBackDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationGiveBack cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationGiveBackDTO result = quotationGiveBackService.save(quotationGiveBackDTO);
        return ResponseEntity.created(new URI("/api/quotation-give-backs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-give-backs : Updates an existing quotationGiveBack.
     *
     * @param quotationGiveBackDTO the quotationGiveBackDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationGiveBackDTO,
     * or with status 400 (Bad Request) if the quotationGiveBackDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationGiveBackDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-give-backs")
    @Timed
    public ResponseEntity<QuotationGiveBackDTO> updateQuotationGiveBack(@Valid @RequestBody QuotationGiveBackDTO quotationGiveBackDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationGiveBack : {}", quotationGiveBackDTO);
        if (quotationGiveBackDTO.getId() == null) {
            return createQuotationGiveBack(quotationGiveBackDTO);
        }
        QuotationGiveBackDTO result = quotationGiveBackService.save(quotationGiveBackDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationGiveBackDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-give-backs : get all the quotationGiveBacks.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationGiveBacks in body
     */
    @GetMapping("/quotation-give-backs")
    @Timed
    public ResponseEntity<List<QuotationGiveBackDTO>> getAllQuotationGiveBacks(QuotationGiveBackCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationGiveBacks by criteria: {}", criteria);
        Page<QuotationGiveBackDTO> page = quotationGiveBackQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-give-backs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-give-backs/:id : get the "id" quotationGiveBack.
     *
     * @param id the id of the quotationGiveBackDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationGiveBackDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-give-backs/{id}")
    @Timed
    public ResponseEntity<QuotationGiveBackDTO> getQuotationGiveBack(@PathVariable Long id) {
        log.debug("REST request to get QuotationGiveBack : {}", id);
        QuotationGiveBackDTO quotationGiveBackDTO = quotationGiveBackService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationGiveBackDTO));
    }

    /**
     * DELETE  /quotation-give-backs/:id : delete the "id" quotationGiveBack.
     *
     * @param id the id of the quotationGiveBackDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-give-backs/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationGiveBack(@PathVariable Long id) {
        log.debug("REST request to delete QuotationGiveBack : {}", id);
        quotationGiveBackService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-give-backs?query=:query : search for the quotationGiveBack corresponding
     * to the query.
     *
     * @param query the query of the quotationGiveBack search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-give-backs")
    @Timed
    public ResponseEntity<List<QuotationGiveBackDTO>> searchQuotationGiveBacks(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationGiveBacks for query {}", query);
        Page<QuotationGiveBackDTO> page = quotationGiveBackService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-give-backs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-give-backs")
    @Timed
    public ResponseEntity<List<QuotationGiveBackDTO>> searchExampleQuotationGiveBacks(QuotationGiveBackSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationGiveBacks for searchDTO {}", searchDTO);
        Page<QuotationGiveBackDTO> page = quotationGiveBackService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-give-backs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
