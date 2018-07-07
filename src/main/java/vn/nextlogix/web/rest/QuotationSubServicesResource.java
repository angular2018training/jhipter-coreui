package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.QuotationSubServicesService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.QuotationSubServicesDTO;
import vn.nextlogix.service.dto.QuotationSubServicesSearchDTO;
import vn.nextlogix.service.dto.QuotationSubServicesCriteria;
import vn.nextlogix.service.QuotationSubServicesQueryService;
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
 * REST controller for managing QuotationSubServices.
 */
@RestController
@RequestMapping("/api")
public class QuotationSubServicesResource {

    private final Logger log = LoggerFactory.getLogger(QuotationSubServicesResource.class);

    private static final String ENTITY_NAME = "quotationSubServices";

    private final QuotationSubServicesService quotationSubServicesService;

    private final QuotationSubServicesQueryService quotationSubServicesQueryService;

    public QuotationSubServicesResource(QuotationSubServicesService quotationSubServicesService, QuotationSubServicesQueryService quotationSubServicesQueryService     ) {
        this.quotationSubServicesService = quotationSubServicesService;
        this.quotationSubServicesQueryService = quotationSubServicesQueryService;


    }

    /**
     * POST  /quotation-sub-services : Create a new quotationSubServices.
     *
     * @param quotationSubServicesDTO the quotationSubServicesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationSubServicesDTO, or with status 400 (Bad Request) if the quotationSubServices has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-sub-services")
    @Timed
    public ResponseEntity<QuotationSubServicesDTO> createQuotationSubServices(@Valid @RequestBody QuotationSubServicesDTO quotationSubServicesDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationSubServices : {}", quotationSubServicesDTO);
        if (quotationSubServicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationSubServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationSubServicesDTO result = quotationSubServicesService.save(quotationSubServicesDTO);
        return ResponseEntity.created(new URI("/api/quotation-sub-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-sub-services : Updates an existing quotationSubServices.
     *
     * @param quotationSubServicesDTO the quotationSubServicesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationSubServicesDTO,
     * or with status 400 (Bad Request) if the quotationSubServicesDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationSubServicesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-sub-services")
    @Timed
    public ResponseEntity<QuotationSubServicesDTO> updateQuotationSubServices(@Valid @RequestBody QuotationSubServicesDTO quotationSubServicesDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationSubServices : {}", quotationSubServicesDTO);
        if (quotationSubServicesDTO.getId() == null) {
            return createQuotationSubServices(quotationSubServicesDTO);
        }
        QuotationSubServicesDTO result = quotationSubServicesService.save(quotationSubServicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationSubServicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-sub-services : get all the quotationSubServices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quotationSubServices in body
     */
    @GetMapping("/quotation-sub-services")
    @Timed
    public ResponseEntity<List<QuotationSubServicesDTO>> getAllQuotationSubServices(QuotationSubServicesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QuotationSubServices by criteria: {}", criteria);
        Page<QuotationSubServicesDTO> page = quotationSubServicesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-sub-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-sub-services/:id : get the "id" quotationSubServices.
     *
     * @param id the id of the quotationSubServicesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationSubServicesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-sub-services/{id}")
    @Timed
    public ResponseEntity<QuotationSubServicesDTO> getQuotationSubServices(@PathVariable Long id) {
        log.debug("REST request to get QuotationSubServices : {}", id);
        QuotationSubServicesDTO quotationSubServicesDTO = quotationSubServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationSubServicesDTO));
    }

    /**
     * DELETE  /quotation-sub-services/:id : delete the "id" quotationSubServices.
     *
     * @param id the id of the quotationSubServicesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-sub-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationSubServices(@PathVariable Long id) {
        log.debug("REST request to delete QuotationSubServices : {}", id);
        quotationSubServicesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotation-sub-services?query=:query : search for the quotationSubServices corresponding
     * to the query.
     *
     * @param query the query of the quotationSubServices search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotation-sub-services")
    @Timed
    public ResponseEntity<List<QuotationSubServicesDTO>> searchQuotationSubServices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuotationSubServices for query {}", query);
        Page<QuotationSubServicesDTO> page = quotationSubServicesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotation-sub-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/quotation-sub-services")
    @Timed
    public ResponseEntity<List<QuotationSubServicesDTO>> searchExampleQuotationSubServices(QuotationSubServicesSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of QuotationSubServices for searchDTO {}", searchDTO);
        Page<QuotationSubServicesDTO> page = quotationSubServicesService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/quotation-sub-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
