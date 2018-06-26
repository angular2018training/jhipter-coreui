package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerTypeDTO;
import vn.nextlogix.service.dto.CustomerTypeSearchDTO;
import vn.nextlogix.service.dto.CustomerTypeCriteria;
import vn.nextlogix.service.CustomerTypeQueryService;
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
 * REST controller for managing CustomerType.
 */
@RestController
@RequestMapping("/api")
public class CustomerTypeResource {

    private final Logger log = LoggerFactory.getLogger(CustomerTypeResource.class);

    private static final String ENTITY_NAME = "customerType";

    private final CustomerTypeService customerTypeService;

    private final CustomerTypeQueryService customerTypeQueryService;

    public CustomerTypeResource(CustomerTypeService customerTypeService, CustomerTypeQueryService customerTypeQueryService     ) {
        this.customerTypeService = customerTypeService;
        this.customerTypeQueryService = customerTypeQueryService;


    }

    /**
     * POST  /customer-types : Create a new customerType.
     *
     * @param customerTypeDTO the customerTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerTypeDTO, or with status 400 (Bad Request) if the customerType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-types")
    @Timed
    public ResponseEntity<CustomerTypeDTO> createCustomerType(@Valid @RequestBody CustomerTypeDTO customerTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerType : {}", customerTypeDTO);
        if (customerTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerTypeDTO result = customerTypeService.save(customerTypeDTO);
        return ResponseEntity.created(new URI("/api/customer-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-types : Updates an existing customerType.
     *
     * @param customerTypeDTO the customerTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerTypeDTO,
     * or with status 400 (Bad Request) if the customerTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-types")
    @Timed
    public ResponseEntity<CustomerTypeDTO> updateCustomerType(@Valid @RequestBody CustomerTypeDTO customerTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerType : {}", customerTypeDTO);
        if (customerTypeDTO.getId() == null) {
            return createCustomerType(customerTypeDTO);
        }
        CustomerTypeDTO result = customerTypeService.save(customerTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-types : get all the customerTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerTypes in body
     */
    @GetMapping("/customer-types")
    @Timed
    public ResponseEntity<List<CustomerTypeDTO>> getAllCustomerTypes(CustomerTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerTypes by criteria: {}", criteria);
        Page<CustomerTypeDTO> page = customerTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-types/:id : get the "id" customerType.
     *
     * @param id the id of the customerTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-types/{id}")
    @Timed
    public ResponseEntity<CustomerTypeDTO> getCustomerType(@PathVariable Long id) {
        log.debug("REST request to get CustomerType : {}", id);
        CustomerTypeDTO customerTypeDTO = customerTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerTypeDTO));
    }

    /**
     * DELETE  /customer-types/:id : delete the "id" customerType.
     *
     * @param id the id of the customerTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerType(@PathVariable Long id) {
        log.debug("REST request to delete CustomerType : {}", id);
        customerTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-types?query=:query : search for the customerType corresponding
     * to the query.
     *
     * @param query the query of the customerType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-types")
    @Timed
    public ResponseEntity<List<CustomerTypeDTO>> searchCustomerTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerTypes for query {}", query);
        Page<CustomerTypeDTO> page = customerTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-types")
    @Timed
    public ResponseEntity<List<CustomerTypeDTO>> searchExampleCustomerTypes(CustomerTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerTypes for searchDTO {}", searchDTO);
        Page<CustomerTypeDTO> page = customerTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
