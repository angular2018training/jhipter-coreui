package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationInsuranceService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationInsuranceDTO;
import vn.nextlogix.service.dto.QuotationInsuranceSearchDTO;
import vn.nextlogix.service.dto.QuotationInsuranceCriteria;
import vn.nextlogix.service.QuotationInsuranceQueryService;
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
 * REST controller for managing QuotationInsurance.
 */
@RestController
@RequestMapping("/api")
public class QuotationInsuranceResource {

    private final Logger log = LoggerFactory.getLogger(QuotationInsuranceResource.class);

    private static final String ENTITY_NAME = "quotationInsurance";

    private final QuotationInsuranceService quotationInsuranceService;

    private final QuotationInsuranceQueryService quotationInsuranceQueryService;

    public QuotationInsuranceResource(QuotationInsuranceService quotationInsuranceService, QuotationInsuranceQueryService quotationInsuranceQueryService     ) {
        this.quotationInsuranceService = quotationInsuranceService;
        this.quotationInsuranceQueryService = quotationInsuranceQueryService;


    }

    /**
     * POST  /quotation-insurances : Create a new quotationInsurance.
     *
     * @param quotationInsuranceDTO the quotationInsuranceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationInsuranceDTO, or with status 400 (Bad Request) if the quotationInsurance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-insurances")
    @Timed
    public ResponseEntity<QuotationInsuranceDTO> createQuotationInsurance(@Valid @RequestBody QuotationInsuranceDTO quotationInsuranceDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationInsurance : {}", quotationInsuranceDTO);
        if (quotationInsuranceDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationInsurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationInsuranceDTO result = quotationInsuranceService.save(quotationInsuranceDTO);
        return ResponseEntity.created(new URI("/api/quotation-insurances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-insurances : Updates an existing quotationInsurance.
     *
     * @param quotationInsuranceDTO the quotationInsuranceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationInsuranceDTO,
     * or with status 400 (Bad Request) if the quotationInsuranceDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationInsuranceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-insurances")
    @Timed
    public ResponseEntity<QuotationInsuranceDTO> updateQuotationInsurance(@Valid @RequestBody QuotationInsuranceDTO quotationInsuranceDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationInsurance : {}", quotationInsuranceDTO);
        if (quotationInsuranceDTO.getId() == null) {
            return createQuotationInsurance(quotationInsuranceDTO);
        }
        QuotationInsuranceDTO result = quotationInsuranceService.save(quotationInsuranceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationInsuranceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-insurances : get all the quotationInsurances.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationInsurances in body
     */
    @GetMapping("/quotation-insurances")
    @Timed
    public ResponseEntity<List<QuotationInsuranceDTO>> getAllQuotationInsurances(QuotationInsuranceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationInsurances by criteria: {}", criteria);
        Page<QuotationInsuranceDTO> page = quotationInsuranceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-insurances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-insurances/:id : get the "id" quotationInsurance.
     *
     * @param id the id of the quotationInsuranceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationInsuranceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-insurances/{id}")
    @Timed
    public ResponseEntity<QuotationInsuranceDTO> getQuotationInsurance(@PathVariable Long id) {
        log.debug("REST request to get QuotationInsurance : {}", id);
        QuotationInsuranceDTO quotationInsuranceDTO = quotationInsuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationInsuranceDTO));
    }

    /**
     * DELETE  /quotation-insurances/:id : delete the "id" quotationInsurance.
     *
     * @param id the id of the quotationInsuranceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-insurances/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationInsurance(@PathVariable Long id) {
        log.debug("REST request to delete QuotationInsurance : {}", id);
        quotationInsuranceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-insurances?query=:query : search for the quotationInsurance corresponding
     * to the query.
     *
     * @param query the query of the quotationInsurance search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-insurances")
    @Timed
    public ResponseEntity<List<QuotationInsuranceDTO>> searchQuotationInsurances(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationInsurances for query {}", query);
        Page<QuotationInsuranceDTO> page = quotationInsuranceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-insurances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-insurances")
    @Timed
    public ResponseEntity<List<QuotationInsuranceDTO>> searchExampleQuotationInsurances(QuotationInsuranceSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationInsurances for searchDTO {}", searchDTO);
        Page<QuotationInsuranceDTO> page = quotationInsuranceService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-insurances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
