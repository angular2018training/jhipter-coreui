package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerServicesService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerServicesDTO;
import vn.nextlogix.service.dto.CustomerServicesSearchDTO;
import vn.nextlogix.service.dto.CustomerServicesCriteria;
import vn.nextlogix.service.CustomerServicesQueryService;
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
 * REST controller for managing CustomerServices.
 */
@RestController
@RequestMapping("/api")
public class CustomerServicesResource {

    private final Logger log = LoggerFactory.getLogger(CustomerServicesResource.class);

    private static final String ENTITY_NAME = "customerServices";

    private final CustomerServicesService customerServicesService;

    private final CustomerServicesQueryService customerServicesQueryService;

    public CustomerServicesResource(CustomerServicesService customerServicesService, CustomerServicesQueryService customerServicesQueryService     ) {
        this.customerServicesService = customerServicesService;
        this.customerServicesQueryService = customerServicesQueryService;


    }

    /**
     * POST  /customer-services : Create a new customerServices.
     *
     * @param customerServicesDTO the customerServicesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerServicesDTO, or with status 400 (Bad Request) if the customerServices has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-services")
    @Timed
    public ResponseEntity<CustomerServicesDTO> createCustomerServices(@Valid @RequestBody CustomerServicesDTO customerServicesDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerServices : {}", customerServicesDTO);
        if (customerServicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerServicesDTO result = customerServicesService.save(customerServicesDTO);
        return ResponseEntity.created(new URI("/api/customer-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-services : Updates an existing customerServices.
     *
     * @param customerServicesDTO the customerServicesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerServicesDTO,
     * or with status 400 (Bad Request) if the customerServicesDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerServicesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-services")
    @Timed
    public ResponseEntity<CustomerServicesDTO> updateCustomerServices(@Valid @RequestBody CustomerServicesDTO customerServicesDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerServices : {}", customerServicesDTO);
        if (customerServicesDTO.getId() == null) {
            return createCustomerServices(customerServicesDTO);
        }
        CustomerServicesDTO result = customerServicesService.save(customerServicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerServicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-services : get all the customerServices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerServices in body
     */
    @GetMapping("/customer-services")
    @Timed
    public ResponseEntity<List<CustomerServicesDTO>> getAllCustomerServices(CustomerServicesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerServices by criteria: {}", criteria);
        Page<CustomerServicesDTO> page = customerServicesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-services/:id : get the "id" customerServices.
     *
     * @param id the id of the customerServicesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerServicesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-services/{id}")
    @Timed
    public ResponseEntity<CustomerServicesDTO> getCustomerServices(@PathVariable Long id) {
        log.debug("REST request to get CustomerServices : {}", id);
        CustomerServicesDTO customerServicesDTO = customerServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerServicesDTO));
    }

    /**
     * DELETE  /customer-services/:id : delete the "id" customerServices.
     *
     * @param id the id of the customerServicesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerServices(@PathVariable Long id) {
        log.debug("REST request to delete CustomerServices : {}", id);
        customerServicesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-services?query=:query : search for the customerServices corresponding
     * to the query.
     *
     * @param query the query of the customerServices search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-services")
    @Timed
    public ResponseEntity<List<CustomerServicesDTO>> searchCustomerServices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerServices for query {}", query);
        Page<CustomerServicesDTO> page = customerServicesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-services")
    @Timed
    public ResponseEntity<List<CustomerServicesDTO>> searchExampleCustomerServices(CustomerServicesSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerServices for searchDTO {}", searchDTO);
        Page<CustomerServicesDTO> page = customerServicesService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
