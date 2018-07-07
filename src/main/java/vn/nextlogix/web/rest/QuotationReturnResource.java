package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationReturnService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationReturnDTO;
import vn.nextlogix.service.dto.QuotationReturnSearchDTO;
import vn.nextlogix.service.dto.QuotationReturnCriteria;
import vn.nextlogix.service.QuotationReturnQueryService;
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
 * REST controller for managing QuotationReturn.
 */
@RestController
@RequestMapping("/api")
public class QuotationReturnResource {

    private final Logger log = LoggerFactory.getLogger(QuotationReturnResource.class);

    private static final String ENTITY_NAME = "quotationReturn";

    private final QuotationReturnService quotationReturnService;

    private final QuotationReturnQueryService quotationReturnQueryService;

    public QuotationReturnResource(QuotationReturnService quotationReturnService, QuotationReturnQueryService quotationReturnQueryService     ) {
        this.quotationReturnService = quotationReturnService;
        this.quotationReturnQueryService = quotationReturnQueryService;


    }

    /**
     * POST  /quotation-returns : Create a new quotationReturn.
     *
     * @param quotationReturnDTO the quotationReturnDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationReturnDTO, or with status 400 (Bad Request) if the quotationReturn has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-returns")
    @Timed
    public ResponseEntity<QuotationReturnDTO> createQuotationReturn(@Valid @RequestBody QuotationReturnDTO quotationReturnDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationReturn : {}", quotationReturnDTO);
        if (quotationReturnDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationReturn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationReturnDTO result = quotationReturnService.save(quotationReturnDTO);
        return ResponseEntity.created(new URI("/api/quotation-returns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-returns : Updates an existing quotationReturn.
     *
     * @param quotationReturnDTO the quotationReturnDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationReturnDTO,
     * or with status 400 (Bad Request) if the quotationReturnDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationReturnDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-returns")
    @Timed
    public ResponseEntity<QuotationReturnDTO> updateQuotationReturn(@Valid @RequestBody QuotationReturnDTO quotationReturnDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationReturn : {}", quotationReturnDTO);
        if (quotationReturnDTO.getId() == null) {
            return createQuotationReturn(quotationReturnDTO);
        }
        QuotationReturnDTO result = quotationReturnService.save(quotationReturnDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationReturnDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-returns : get all the quotationReturns.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationReturns in body
     */
    @GetMapping("/quotation-returns")
    @Timed
    public ResponseEntity<List<QuotationReturnDTO>> getAllQuotationReturns(QuotationReturnCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationReturns by criteria: {}", criteria);
        Page<QuotationReturnDTO> page = quotationReturnQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-returns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-returns/:id : get the "id" quotationReturn.
     *
     * @param id the id of the quotationReturnDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationReturnDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-returns/{id}")
    @Timed
    public ResponseEntity<QuotationReturnDTO> getQuotationReturn(@PathVariable Long id) {
        log.debug("REST request to get QuotationReturn : {}", id);
        QuotationReturnDTO quotationReturnDTO = quotationReturnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationReturnDTO));
    }

    /**
     * DELETE  /quotation-returns/:id : delete the "id" quotationReturn.
     *
     * @param id the id of the quotationReturnDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-returns/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationReturn(@PathVariable Long id) {
        log.debug("REST request to delete QuotationReturn : {}", id);
        quotationReturnService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-returns?query=:query : search for the quotationReturn corresponding
     * to the query.
     *
     * @param query the query of the quotationReturn search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-returns")
    @Timed
    public ResponseEntity<List<QuotationReturnDTO>> searchQuotationReturns(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationReturns for query {}", query);
        Page<QuotationReturnDTO> page = quotationReturnService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-returns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-returns")
    @Timed
    public ResponseEntity<List<QuotationReturnDTO>> searchExampleQuotationReturns(QuotationReturnSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationReturns for searchDTO {}", searchDTO);
        Page<QuotationReturnDTO> page = quotationReturnService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-returns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
