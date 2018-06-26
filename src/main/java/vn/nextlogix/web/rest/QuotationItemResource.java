package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationItemService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationItemDTO;
import vn.nextlogix.service.dto.QuotationItemSearchDTO;
import vn.nextlogix.service.dto.QuotationItemCriteria;
import vn.nextlogix.service.QuotationItemQueryService;
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
 * REST controller for managing QuotationItem.
 */
@RestController
@RequestMapping("/api")
public class QuotationItemResource {

    private final Logger log = LoggerFactory.getLogger(QuotationItemResource.class);

    private static final String ENTITY_NAME = "quotationItem";

    private final QuotationItemService quotationItemService;

    private final QuotationItemQueryService quotationItemQueryService;

    public QuotationItemResource(QuotationItemService quotationItemService, QuotationItemQueryService quotationItemQueryService     ) {
        this.quotationItemService = quotationItemService;
        this.quotationItemQueryService = quotationItemQueryService;


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
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationItems in body
     */
    @GetMapping("/quotation-items")
    @Timed
    public ResponseEntity<List<QuotationItemDTO>> getAllQuotationItems(QuotationItemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationItems by criteria: {}", criteria);
        Page<QuotationItemDTO> page = quotationItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-items")
    @Timed
    public ResponseEntity<List<QuotationItemDTO>> searchQuotationItems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationItems for query {}", query);
        Page<QuotationItemDTO> page = quotationItemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-items")
    @Timed
    public ResponseEntity<List<QuotationItemDTO>> searchExampleQuotationItems(QuotationItemSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationItems for searchDTO {}", searchDTO);
        Page<QuotationItemDTO> page = quotationItemService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
