package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationDomesticDeliveryService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationDomesticDeliveryDTO;
import vn.nextlogix.service.dto.QuotationDomesticDeliverySearchDTO;
import vn.nextlogix.service.dto.QuotationDomesticDeliveryCriteria;
import vn.nextlogix.service.QuotationDomesticDeliveryQueryService;
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
 * REST controller for managing QuotationDomesticDelivery.
 */
@RestController
@RequestMapping("/api")
public class QuotationDomesticDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(QuotationDomesticDeliveryResource.class);

    private static final String ENTITY_NAME = "quotationDomesticDelivery";

    private final QuotationDomesticDeliveryService quotationDomesticDeliveryService;

    private final QuotationDomesticDeliveryQueryService quotationDomesticDeliveryQueryService;

    public QuotationDomesticDeliveryResource(QuotationDomesticDeliveryService quotationDomesticDeliveryService, QuotationDomesticDeliveryQueryService quotationDomesticDeliveryQueryService     ) {
        this.quotationDomesticDeliveryService = quotationDomesticDeliveryService;
        this.quotationDomesticDeliveryQueryService = quotationDomesticDeliveryQueryService;


    }

    /**
     * POST  /quotation-domestic-deliveries : Create a new quotationDomesticDelivery.
     *
     * @param quotationDomesticDeliveryDTO the quotationDomesticDeliveryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationDomesticDeliveryDTO, or with status 400 (Bad Request) if the quotationDomesticDelivery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-domestic-deliveries")
    @Timed
    public ResponseEntity<QuotationDomesticDeliveryDTO> createQuotationDomesticDelivery(@Valid @RequestBody QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationDomesticDelivery : {}", quotationDomesticDeliveryDTO);
        if (quotationDomesticDeliveryDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationDomesticDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationDomesticDeliveryDTO result = quotationDomesticDeliveryService.save(quotationDomesticDeliveryDTO);
        return ResponseEntity.created(new URI("/api/quotation-domestic-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-domestic-deliveries : Updates an existing quotationDomesticDelivery.
     *
     * @param quotationDomesticDeliveryDTO the quotationDomesticDeliveryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationDomesticDeliveryDTO,
     * or with status 400 (Bad Request) if the quotationDomesticDeliveryDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationDomesticDeliveryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-domestic-deliveries")
    @Timed
    public ResponseEntity<QuotationDomesticDeliveryDTO> updateQuotationDomesticDelivery(@Valid @RequestBody QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationDomesticDelivery : {}", quotationDomesticDeliveryDTO);
        if (quotationDomesticDeliveryDTO.getId() == null) {
            return createQuotationDomesticDelivery(quotationDomesticDeliveryDTO);
        }
        QuotationDomesticDeliveryDTO result = quotationDomesticDeliveryService.save(quotationDomesticDeliveryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationDomesticDeliveryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-domestic-deliveries : get all the quotationDomesticDeliveries.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationDomesticDeliveries in body
     */
    @GetMapping("/quotation-domestic-deliveries")
    @Timed
    public ResponseEntity<List<QuotationDomesticDeliveryDTO>> getAllQuotationDomesticDeliveries(QuotationDomesticDeliveryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationDomesticDeliveries by criteria: {}", criteria);
        Page<QuotationDomesticDeliveryDTO> page = quotationDomesticDeliveryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-domestic-deliveries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-domestic-deliveries/:id : get the "id" quotationDomesticDelivery.
     *
     * @param id the id of the quotationDomesticDeliveryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationDomesticDeliveryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-domestic-deliveries/{id}")
    @Timed
    public ResponseEntity<QuotationDomesticDeliveryDTO> getQuotationDomesticDelivery(@PathVariable Long id) {
        log.debug("REST request to get QuotationDomesticDelivery : {}", id);
        QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO = quotationDomesticDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationDomesticDeliveryDTO));
    }

    /**
     * DELETE  /quotation-domestic-deliveries/:id : delete the "id" quotationDomesticDelivery.
     *
     * @param id the id of the quotationDomesticDeliveryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-domestic-deliveries/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationDomesticDelivery(@PathVariable Long id) {
        log.debug("REST request to delete QuotationDomesticDelivery : {}", id);
        quotationDomesticDeliveryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-domestic-deliveries?query=:query : search for the quotationDomesticDelivery corresponding
     * to the query.
     *
     * @param query the query of the quotationDomesticDelivery search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-domestic-deliveries")
    @Timed
    public ResponseEntity<List<QuotationDomesticDeliveryDTO>> searchQuotationDomesticDeliveries(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationDomesticDeliveries for query {}", query);
        Page<QuotationDomesticDeliveryDTO> page = quotationDomesticDeliveryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-domestic-deliveries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-domestic-deliveries")
    @Timed
    public ResponseEntity<List<QuotationDomesticDeliveryDTO>> searchExampleQuotationDomesticDeliveries(QuotationDomesticDeliverySearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationDomesticDeliveries for searchDTO {}", searchDTO);
        Page<QuotationDomesticDeliveryDTO> page = quotationDomesticDeliveryService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-domestic-deliveries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
