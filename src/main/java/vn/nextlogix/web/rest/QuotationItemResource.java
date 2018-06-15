package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.QuotationItemService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.QuotationItemDTO;
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
 * REST controller for managing QuotationItem.
 */
@RestController
@RequestMapping("/api")
public class QuotationItemResource {

    private final Logger log = LoggerFactory.getLogger(QuotationItemResource.class);

    private static final String ENTITY_NAME = "quotationItem";

    private final QuotationItemService quotationItemService;

    public QuotationItemResource(QuotationItemService quotationItemService) {
        this.quotationItemService = quotationItemService;
    }

    /**
     * POST  /quotation-items : Create a new quotationItem.
     *
     * @param quotationItemDTO the quotationItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationItemDTO, or with status 400 (Bad Request) if the quotationItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-items")
    @Timed
    public ResponseEntity<QuotationItemDTO> createQuotationItem(@Valid @RequestBody QuotationItemDTO quotationItemDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationItem : {}", quotationItemDTO);
        if (quotationItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationItemDTO result = quotationItemService.save(quotationItemDTO);
        return ResponseEntity.created(new URI("/api/quotation-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-items : Updates an existing quotationItem.
     *
     * @param quotationItemDTO the quotationItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationItemDTO,
     * or with status 400 (Bad Request) if the quotationItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-items")
    @Timed
    public ResponseEntity<QuotationItemDTO> updateQuotationItem(@Valid @RequestBody QuotationItemDTO quotationItemDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationItem : {}", quotationItemDTO);
        if (quotationItemDTO.getId() == null) {
            return createQuotationItem(quotationItemDTO);
        }
        QuotationItemDTO result = quotationItemService.save(quotationItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-items : get all the quotationItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quotationItems in body
     */
    @GetMapping("/quotation-items")
    @Timed
    public List<QuotationItemDTO> getAllQuotationItems() {
        log.debug("REST request to get all QuotationItems");
        return quotationItemService.findAll();
        }

    /**
     * GET  /quotation-items/:id : get the "id" quotationItem.
     *
     * @param id the id of the quotationItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-items/{id}")
    @Timed
    public ResponseEntity<QuotationItemDTO> getQuotationItem(@PathVariable Long id) {
        log.debug("REST request to get QuotationItem : {}", id);
        QuotationItemDTO quotationItemDTO = quotationItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationItemDTO));
    }

    /**
     * DELETE  /quotation-items/:id : delete the "id" quotationItem.
     *
     * @param id the id of the quotationItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationItem(@PathVariable Long id) {
        log.debug("REST request to delete QuotationItem : {}", id);
        quotationItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-items?query=:query : search for the quotationItem corresponding
     * to the query.
     *
     * @param query the query of the quotationItem search
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-items")
    @Timed
    public List<QuotationItemDTO> searchQuotationItems(@RequestParam String query) {
        log.debug("REST request to search QuotationItems for query {}", query);
        return quotationItemService.search(query);
    }

}
