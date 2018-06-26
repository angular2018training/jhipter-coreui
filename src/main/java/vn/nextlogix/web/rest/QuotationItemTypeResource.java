package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationItemTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationItemTypeDTO;
import vn.nextlogix.service.dto.QuotationItemTypeSearchDTO;
import vn.nextlogix.service.dto.QuotationItemTypeCriteria;
import vn.nextlogix.service.QuotationItemTypeQueryService;
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
 * REST controller for managing QuotationItemType.
 */
@RestController
@RequestMapping("/api")
public class QuotationItemTypeResource {

    private final Logger log = LoggerFactory.getLogger(QuotationItemTypeResource.class);

    private static final String ENTITY_NAME = "quotationItemType";

    private final QuotationItemTypeService quotationItemTypeService;

    private final QuotationItemTypeQueryService quotationItemTypeQueryService;

    public QuotationItemTypeResource(QuotationItemTypeService quotationItemTypeService, QuotationItemTypeQueryService quotationItemTypeQueryService     ) {
        this.quotationItemTypeService = quotationItemTypeService;
        this.quotationItemTypeQueryService = quotationItemTypeQueryService;


    }

    /**
     * POST  /quotation-item-types : Create a new quotationItemType.
     *
     * @param quotationItemTypeDTO the quotationItemTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationItemTypeDTO, or with status 400 (Bad Request) if the quotationItemType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-item-types")
    @Timed
    public ResponseEntity<QuotationItemTypeDTO> createQuotationItemType(@Valid @RequestBody QuotationItemTypeDTO quotationItemTypeDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationItemType : {}", quotationItemTypeDTO);
        if (quotationItemTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationItemType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationItemTypeDTO result = quotationItemTypeService.save(quotationItemTypeDTO);
        return ResponseEntity.created(new URI("/api/quotation-item-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-item-types : Updates an existing quotationItemType.
     *
     * @param quotationItemTypeDTO the quotationItemTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationItemTypeDTO,
     * or with status 400 (Bad Request) if the quotationItemTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationItemTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-item-types")
    @Timed
    public ResponseEntity<QuotationItemTypeDTO> updateQuotationItemType(@Valid @RequestBody QuotationItemTypeDTO quotationItemTypeDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationItemType : {}", quotationItemTypeDTO);
        if (quotationItemTypeDTO.getId() == null) {
            return createQuotationItemType(quotationItemTypeDTO);
        }
        QuotationItemTypeDTO result = quotationItemTypeService.save(quotationItemTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationItemTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-item-types : get all the quotationItemTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationItemTypes in body
     */
    @GetMapping("/quotation-item-types")
    @Timed
    public ResponseEntity<List<QuotationItemTypeDTO>> getAllQuotationItemTypes(QuotationItemTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationItemTypes by criteria: {}", criteria);
        Page<QuotationItemTypeDTO> page = quotationItemTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-item-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-item-types/:id : get the "id" quotationItemType.
     *
     * @param id the id of the quotationItemTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationItemTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-item-types/{id}")
    @Timed
    public ResponseEntity<QuotationItemTypeDTO> getQuotationItemType(@PathVariable Long id) {
        log.debug("REST request to get QuotationItemType : {}", id);
        QuotationItemTypeDTO quotationItemTypeDTO = quotationItemTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationItemTypeDTO));
    }

    /**
     * DELETE  /quotation-item-types/:id : delete the "id" quotationItemType.
     *
     * @param id the id of the quotationItemTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-item-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationItemType(@PathVariable Long id) {
        log.debug("REST request to delete QuotationItemType : {}", id);
        quotationItemTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-item-types?query=:query : search for the quotationItemType corresponding
     * to the query.
     *
     * @param query the query of the quotationItemType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-item-types")
    @Timed
    public ResponseEntity<List<QuotationItemTypeDTO>> searchQuotationItemTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationItemTypes for query {}", query);
        Page<QuotationItemTypeDTO> page = quotationItemTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-item-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-item-types")
    @Timed
    public ResponseEntity<List<QuotationItemTypeDTO>> searchExampleQuotationItemTypes(QuotationItemTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationItemTypes for searchDTO {}", searchDTO);
        Page<QuotationItemTypeDTO> page = quotationItemTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-item-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
