package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerPostOfficeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerPostOfficeDTO;
import vn.nextlogix.service.dto.CustomerPostOfficeSearchDTO;
import vn.nextlogix.service.dto.CustomerPostOfficeCriteria;
import vn.nextlogix.service.CustomerPostOfficeQueryService;
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
 * REST controller for managing CustomerPostOffice.
 */
@RestController
@RequestMapping("/api")
public class CustomerPostOfficeResource {

    private final Logger log = LoggerFactory.getLogger(CustomerPostOfficeResource.class);

    private static final String ENTITY_NAME = "customerPostOffice";

    private final CustomerPostOfficeService customerPostOfficeService;

    private final CustomerPostOfficeQueryService customerPostOfficeQueryService;

    public CustomerPostOfficeResource(CustomerPostOfficeService customerPostOfficeService, CustomerPostOfficeQueryService customerPostOfficeQueryService     ) {
        this.customerPostOfficeService = customerPostOfficeService;
        this.customerPostOfficeQueryService = customerPostOfficeQueryService;


    }

    /**
     * POST  /customer-post-offices : Create a new customerPostOffice.
     *
     * @param customerPostOfficeDTO the customerPostOfficeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerPostOfficeDTO, or with status 400 (Bad Request) if the customerPostOffice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-post-offices")
    @Timed
    public ResponseEntity<CustomerPostOfficeDTO> createCustomerPostOffice(@Valid @RequestBody CustomerPostOfficeDTO customerPostOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerPostOffice : {}", customerPostOfficeDTO);
        if (customerPostOfficeDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerPostOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerPostOfficeDTO result = customerPostOfficeService.save(customerPostOfficeDTO);
        return ResponseEntity.created(new URI("/api/customer-post-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-post-offices : Updates an existing customerPostOffice.
     *
     * @param customerPostOfficeDTO the customerPostOfficeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerPostOfficeDTO,
     * or with status 400 (Bad Request) if the customerPostOfficeDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerPostOfficeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-post-offices")
    @Timed
    public ResponseEntity<CustomerPostOfficeDTO> updateCustomerPostOffice(@Valid @RequestBody CustomerPostOfficeDTO customerPostOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerPostOffice : {}", customerPostOfficeDTO);
        if (customerPostOfficeDTO.getId() == null) {
            return createCustomerPostOffice(customerPostOfficeDTO);
        }
        CustomerPostOfficeDTO result = customerPostOfficeService.save(customerPostOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerPostOfficeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-post-offices : get all the customerPostOffices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerPostOffices in body
     */
    @GetMapping("/customer-post-offices")
    @Timed
    public ResponseEntity<List<CustomerPostOfficeDTO>> getAllCustomerPostOffices(CustomerPostOfficeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerPostOffices by criteria: {}", criteria);
        Page<CustomerPostOfficeDTO> page = customerPostOfficeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-post-offices/:id : get the "id" customerPostOffice.
     *
     * @param id the id of the customerPostOfficeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerPostOfficeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-post-offices/{id}")
    @Timed
    public ResponseEntity<CustomerPostOfficeDTO> getCustomerPostOffice(@PathVariable Long id) {
        log.debug("REST request to get CustomerPostOffice : {}", id);
        CustomerPostOfficeDTO customerPostOfficeDTO = customerPostOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerPostOfficeDTO));
    }

    /**
     * DELETE  /customer-post-offices/:id : delete the "id" customerPostOffice.
     *
     * @param id the id of the customerPostOfficeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-post-offices/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerPostOffice(@PathVariable Long id) {
        log.debug("REST request to delete CustomerPostOffice : {}", id);
        customerPostOfficeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-post-offices?query=:query : search for the customerPostOffice corresponding
     * to the query.
     *
     * @param query the query of the customerPostOffice search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-post-offices")
    @Timed
    public ResponseEntity<List<CustomerPostOfficeDTO>> searchCustomerPostOffices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerPostOffices for query {}", query);
        Page<CustomerPostOfficeDTO> page = customerPostOfficeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-post-offices")
    @Timed
    public ResponseEntity<List<CustomerPostOfficeDTO>> searchExampleCustomerPostOffices(CustomerPostOfficeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerPostOffices for searchDTO {}", searchDTO);
        Page<CustomerPostOfficeDTO> page = customerPostOfficeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
