package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerLegalService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerLegalDTO;
import vn.nextlogix.service.dto.CustomerLegalSearchDTO;
import vn.nextlogix.service.dto.CustomerLegalCriteria;
import vn.nextlogix.service.CustomerLegalQueryService;
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
 * REST controller for managing CustomerLegal.
 */
@RestController
@RequestMapping("/api")
public class CustomerLegalResource {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalResource.class);

    private static final String ENTITY_NAME = "customerLegal";

    private final CustomerLegalService customerLegalService;

    private final CustomerLegalQueryService customerLegalQueryService;

    public CustomerLegalResource(CustomerLegalService customerLegalService, CustomerLegalQueryService customerLegalQueryService     ) {
        this.customerLegalService = customerLegalService;
        this.customerLegalQueryService = customerLegalQueryService;


    }

    /**
     * POST  /customer-legals : Create a new customerLegal.
     *
     * @param customerLegalDTO the customerLegalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerLegalDTO, or with status 400 (Bad Request) if the customerLegal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-legals")
    @Timed
    public ResponseEntity<CustomerLegalDTO> createCustomerLegal(@Valid @RequestBody CustomerLegalDTO customerLegalDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerLegal : {}", customerLegalDTO);
        if (customerLegalDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerLegal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerLegalDTO result = customerLegalService.save(customerLegalDTO);
        return ResponseEntity.created(new URI("/api/customer-legals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-legals : Updates an existing customerLegal.
     *
     * @param customerLegalDTO the customerLegalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerLegalDTO,
     * or with status 400 (Bad Request) if the customerLegalDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerLegalDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-legals")
    @Timed
    public ResponseEntity<CustomerLegalDTO> updateCustomerLegal(@Valid @RequestBody CustomerLegalDTO customerLegalDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerLegal : {}", customerLegalDTO);
        if (customerLegalDTO.getId() == null) {
            return createCustomerLegal(customerLegalDTO);
        }
        CustomerLegalDTO result = customerLegalService.save(customerLegalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerLegalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-legals : get all the customerLegals.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerLegals in body
     */
    @GetMapping("/customer-legals")
    @Timed
    public ResponseEntity<List<CustomerLegalDTO>> getAllCustomerLegals(CustomerLegalCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerLegals by criteria: {}", criteria);
        Page<CustomerLegalDTO> page = customerLegalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-legals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-legals/:id : get the "id" customerLegal.
     *
     * @param id the id of the customerLegalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerLegalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-legals/{id}")
    @Timed
    public ResponseEntity<CustomerLegalDTO> getCustomerLegal(@PathVariable Long id) {
        log.debug("REST request to get CustomerLegal : {}", id);
        CustomerLegalDTO customerLegalDTO = customerLegalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerLegalDTO));
    }

    /**
     * DELETE  /customer-legals/:id : delete the "id" customerLegal.
     *
     * @param id the id of the customerLegalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-legals/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerLegal(@PathVariable Long id) {
        log.debug("REST request to delete CustomerLegal : {}", id);
        customerLegalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-legals?query=:query : search for the customerLegal corresponding
     * to the query.
     *
     * @param query the query of the customerLegal search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-legals")
    @Timed
    public ResponseEntity<List<CustomerLegalDTO>> searchCustomerLegals(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerLegals for query {}", query);
        Page<CustomerLegalDTO> page = customerLegalService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-legals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-legals")
    @Timed
    public ResponseEntity<List<CustomerLegalDTO>> searchExampleCustomerLegals(CustomerLegalSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerLegals for searchDTO {}", searchDTO);
        Page<CustomerLegalDTO> page = customerLegalService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-legals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
