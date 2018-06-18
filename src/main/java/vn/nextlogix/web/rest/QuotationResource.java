package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.QuotationService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.QuotationDTO;
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
 * REST controller for managing Quotation.
 */
@RestController
@RequestMapping("/api")
public class QuotationResource {

    private final Logger log = LoggerFactory.getLogger(QuotationResource.class);

    private static final String ENTITY_NAME = "quotation";

    private final QuotationService quotationService;

    public QuotationResource(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    /**
     * POST  /quotations : Create a new quotation.
     *
     * @param quotationDTO the quotationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationDTO, or with status 400 (Bad Request) if the quotation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotations")
    @Timed
    public ResponseEntity<QuotationDTO> createQuotation(@Valid @RequestBody QuotationDTO quotationDTO) throws URISyntaxException {
        log.debug("REST request to save Quotation : {}", quotationDTO);
        if (quotationDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationDTO result = quotationService.save(quotationDTO);
        return ResponseEntity.created(new URI("/api/quotations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotations : Updates an existing quotation.
     *
     * @param quotationDTO the quotationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationDTO,
     * or with status 400 (Bad Request) if the quotationDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotations")
    @Timed
    public ResponseEntity<QuotationDTO> updateQuotation(@Valid @RequestBody QuotationDTO quotationDTO) throws URISyntaxException {
        log.debug("REST request to update Quotation : {}", quotationDTO);
        if (quotationDTO.getId() == null) {
            return createQuotation(quotationDTO);
        }
        QuotationDTO result = quotationService.save(quotationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotations : get all the quotations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quotations in body
     */
    @GetMapping("/quotations")
    @Timed
    public List<QuotationDTO> getAllQuotations() {
        log.debug("REST request to get all Quotations");
        return quotationService.findAll();
        }

    /**
     * GET  /quotations/:id : get the "id" quotation.
     *
     * @param id the id of the quotationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotations/{id}")
    @Timed
    public ResponseEntity<QuotationDTO> getQuotation(@PathVariable Long id) {
        log.debug("REST request to get Quotation : {}", id);
        QuotationDTO quotationDTO = quotationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationDTO));
    }

    /**
     * DELETE  /quotations/:id : delete the "id" quotation.
     *
     * @param id the id of the quotationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotations/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotation(@PathVariable Long id) {
        log.debug("REST request to delete Quotation : {}", id);
        quotationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotations?query=:query : search for the quotation corresponding
     * to the query.
     *
     * @param query the query of the quotation search
     * @return the result of the search
     */
    @GetMapping("/_search/quotations")
    @Timed
    public List<QuotationDTO> searchQuotations(@RequestParam String query) {
        log.debug("REST request to search Quotations for query {}", query);
        return quotationService.search(query);
    }

}
