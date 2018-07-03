package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerWarehouseService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerWarehouseDTO;
import vn.nextlogix.service.dto.CustomerWarehouseSearchDTO;
import vn.nextlogix.service.dto.CustomerWarehouseCriteria;
import vn.nextlogix.service.CustomerWarehouseQueryService;
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
 * REST controller for managing CustomerWarehouse.
 */
@RestController
@RequestMapping("/api")
public class CustomerWarehouseResource {

    private final Logger log = LoggerFactory.getLogger(CustomerWarehouseResource.class);

    private static final String ENTITY_NAME = "customerWarehouse";

    private final CustomerWarehouseService customerWarehouseService;

    private final CustomerWarehouseQueryService customerWarehouseQueryService;

    public CustomerWarehouseResource(CustomerWarehouseService customerWarehouseService, CustomerWarehouseQueryService customerWarehouseQueryService     ) {
        this.customerWarehouseService = customerWarehouseService;
        this.customerWarehouseQueryService = customerWarehouseQueryService;


    }

    /**
     * POST  /customer-warehouses : Create a new customerWarehouse.
     *
     * @param customerWarehouseDTO the customerWarehouseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerWarehouseDTO, or with status 400 (Bad Request) if the customerWarehouse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-warehouses")
    @Timed
    public ResponseEntity<CustomerWarehouseDTO> createCustomerWarehouse(@Valid @RequestBody CustomerWarehouseDTO customerWarehouseDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerWarehouse : {}", customerWarehouseDTO);
        if (customerWarehouseDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerWarehouse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerWarehouseDTO result = customerWarehouseService.save(customerWarehouseDTO);
        return ResponseEntity.created(new URI("/api/customer-warehouses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-warehouses : Updates an existing customerWarehouse.
     *
     * @param customerWarehouseDTO the customerWarehouseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerWarehouseDTO,
     * or with status 400 (Bad Request) if the customerWarehouseDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerWarehouseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-warehouses")
    @Timed
    public ResponseEntity<CustomerWarehouseDTO> updateCustomerWarehouse(@Valid @RequestBody CustomerWarehouseDTO customerWarehouseDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerWarehouse : {}", customerWarehouseDTO);
        if (customerWarehouseDTO.getId() == null) {
            return createCustomerWarehouse(customerWarehouseDTO);
        }
        CustomerWarehouseDTO result = customerWarehouseService.save(customerWarehouseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerWarehouseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-warehouses : get all the customerWarehouses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerWarehouses in body
     */
    @GetMapping("/customer-warehouses")
    @Timed
    public ResponseEntity<List<CustomerWarehouseDTO>> getAllCustomerWarehouses(CustomerWarehouseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerWarehouses by criteria: {}", criteria);
        Page<CustomerWarehouseDTO> page = customerWarehouseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-warehouses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-warehouses/:id : get the "id" customerWarehouse.
     *
     * @param id the id of the customerWarehouseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerWarehouseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-warehouses/{id}")
    @Timed
    public ResponseEntity<CustomerWarehouseDTO> getCustomerWarehouse(@PathVariable Long id) {
        log.debug("REST request to get CustomerWarehouse : {}", id);
        CustomerWarehouseDTO customerWarehouseDTO = customerWarehouseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerWarehouseDTO));
    }

    /**
     * DELETE  /customer-warehouses/:id : delete the "id" customerWarehouse.
     *
     * @param id the id of the customerWarehouseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-warehouses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerWarehouse(@PathVariable Long id) {
        log.debug("REST request to delete CustomerWarehouse : {}", id);
        customerWarehouseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-warehouses?query=:query : search for the customerWarehouse corresponding
     * to the query.
     *
     * @param query the query of the customerWarehouse search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-warehouses")
    @Timed
    public ResponseEntity<List<CustomerWarehouseDTO>> searchCustomerWarehouses(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerWarehouses for query {}", query);
        Page<CustomerWarehouseDTO> page = customerWarehouseService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-warehouses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-warehouses")
    @Timed
    public ResponseEntity<List<CustomerWarehouseDTO>> searchExampleCustomerWarehouses(CustomerWarehouseSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerWarehouses for searchDTO {}", searchDTO);
        Page<CustomerWarehouseDTO> page = customerWarehouseService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-warehouses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
