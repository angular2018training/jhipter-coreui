package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationPickupService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationPickupDTO;
import vn.nextlogix.service.dto.QuotationPickupSearchDTO;
import vn.nextlogix.service.dto.QuotationPickupCriteria;
import vn.nextlogix.service.QuotationPickupQueryService;
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
 * REST controller for managing QuotationPickup.
 */
@RestController
@RequestMapping("/api")
public class QuotationPickupResource {

    private final Logger log = LoggerFactory.getLogger(QuotationPickupResource.class);

    private static final String ENTITY_NAME = "quotationPickup";

    private final QuotationPickupService quotationPickupService;

    private final QuotationPickupQueryService quotationPickupQueryService;

    public QuotationPickupResource(QuotationPickupService quotationPickupService, QuotationPickupQueryService quotationPickupQueryService     ) {
        this.quotationPickupService = quotationPickupService;
        this.quotationPickupQueryService = quotationPickupQueryService;


    }

    /**
     * POST  /quotation-pickups : Create a new quotationPickup.
     *
     * @param quotationPickupDTO the quotationPickupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationPickupDTO, or with status 400 (Bad Request) if the quotationPickup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-pickups")
    @Timed
    public ResponseEntity<QuotationPickupDTO> createQuotationPickup(@Valid @RequestBody QuotationPickupDTO quotationPickupDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationPickup : {}", quotationPickupDTO);
        if (quotationPickupDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationPickup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationPickupDTO result = quotationPickupService.save(quotationPickupDTO);
        return ResponseEntity.created(new URI("/api/quotation-pickups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-pickups : Updates an existing quotationPickup.
     *
     * @param quotationPickupDTO the quotationPickupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationPickupDTO,
     * or with status 400 (Bad Request) if the quotationPickupDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationPickupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-pickups")
    @Timed
    public ResponseEntity<QuotationPickupDTO> updateQuotationPickup(@Valid @RequestBody QuotationPickupDTO quotationPickupDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationPickup : {}", quotationPickupDTO);
        if (quotationPickupDTO.getId() == null) {
            return createQuotationPickup(quotationPickupDTO);
        }
        QuotationPickupDTO result = quotationPickupService.save(quotationPickupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationPickupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-pickups : get all the quotationPickups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationPickups in body
     */
    @GetMapping("/quotation-pickups")
    @Timed
    public ResponseEntity<List<QuotationPickupDTO>> getAllQuotationPickups(QuotationPickupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationPickups by criteria: {}", criteria);
        Page<QuotationPickupDTO> page = quotationPickupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-pickups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-pickups/:id : get the "id" quotationPickup.
     *
     * @param id the id of the quotationPickupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationPickupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-pickups/{id}")
    @Timed
    public ResponseEntity<QuotationPickupDTO> getQuotationPickup(@PathVariable Long id) {
        log.debug("REST request to get QuotationPickup : {}", id);
        QuotationPickupDTO quotationPickupDTO = quotationPickupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationPickupDTO));
    }

    /**
     * DELETE  /quotation-pickups/:id : delete the "id" quotationPickup.
     *
     * @param id the id of the quotationPickupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-pickups/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationPickup(@PathVariable Long id) {
        log.debug("REST request to delete QuotationPickup : {}", id);
        quotationPickupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-pickups?query=:query : search for the quotationPickup corresponding
     * to the query.
     *
     * @param query the query of the quotationPickup search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-pickups")
    @Timed
    public ResponseEntity<List<QuotationPickupDTO>> searchQuotationPickups(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationPickups for query {}", query);
        Page<QuotationPickupDTO> page = quotationPickupService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-pickups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-pickups")
    @Timed
    public ResponseEntity<List<QuotationPickupDTO>> searchExampleQuotationPickups(QuotationPickupSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationPickups for searchDTO {}", searchDTO);
        Page<QuotationPickupDTO> page = quotationPickupService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-pickups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
