package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerSourceService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerSourceDTO;
import vn.nextlogix.service.dto.CustomerSourceSearchDTO;
import vn.nextlogix.service.dto.CustomerSourceCriteria;
import vn.nextlogix.service.CustomerSourceQueryService;
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
 * REST controller for managing CustomerSource.
 */
@RestController
@RequestMapping("/api")
public class CustomerSourceResource {

    private final Logger log = LoggerFactory.getLogger(CustomerSourceResource.class);

    private static final String ENTITY_NAME = "customerSource";

    private final CustomerSourceService customerSourceService;

    private final CustomerSourceQueryService customerSourceQueryService;

    public CustomerSourceResource(CustomerSourceService customerSourceService, CustomerSourceQueryService customerSourceQueryService     ) {
        this.customerSourceService = customerSourceService;
        this.customerSourceQueryService = customerSourceQueryService;


    }

    /**
     * POST  /customer-sources : Create a new customerSource.
     *
     * @param customerSourceDTO the customerSourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerSourceDTO, or with status 400 (Bad Request) if the customerSource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-sources")
    @Timed
    public ResponseEntity<CustomerSourceDTO> createCustomerSource(@Valid @RequestBody CustomerSourceDTO customerSourceDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerSource : {}", customerSourceDTO);
        if (customerSourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerSource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerSourceDTO result = customerSourceService.save(customerSourceDTO);
        return ResponseEntity.created(new URI("/api/customer-sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-sources : Updates an existing customerSource.
     *
     * @param customerSourceDTO the customerSourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerSourceDTO,
     * or with status 400 (Bad Request) if the customerSourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerSourceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-sources")
    @Timed
    public ResponseEntity<CustomerSourceDTO> updateCustomerSource(@Valid @RequestBody CustomerSourceDTO customerSourceDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerSource : {}", customerSourceDTO);
        if (customerSourceDTO.getId() == null) {
            return createCustomerSource(customerSourceDTO);
        }
        CustomerSourceDTO result = customerSourceService.save(customerSourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerSourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-sources : get all the customerSources.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerSources in body
     */
    @GetMapping("/customer-sources")
    @Timed
    public ResponseEntity<List<CustomerSourceDTO>> getAllCustomerSources(CustomerSourceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerSources by criteria: {}", criteria);
        Page<CustomerSourceDTO> page = customerSourceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-sources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-sources/:id : get the "id" customerSource.
     *
     * @param id the id of the customerSourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerSourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-sources/{id}")
    @Timed
    public ResponseEntity<CustomerSourceDTO> getCustomerSource(@PathVariable Long id) {
        log.debug("REST request to get CustomerSource : {}", id);
        CustomerSourceDTO customerSourceDTO = customerSourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerSourceDTO));
    }

    /**
     * DELETE  /customer-sources/:id : delete the "id" customerSource.
     *
     * @param id the id of the customerSourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-sources/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerSource(@PathVariable Long id) {
        log.debug("REST request to delete CustomerSource : {}", id);
        customerSourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-sources?query=:query : search for the customerSource corresponding
     * to the query.
     *
     * @param query the query of the customerSource search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-sources")
    @Timed
    public ResponseEntity<List<CustomerSourceDTO>> searchCustomerSources(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerSources for query {}", query);
        Page<CustomerSourceDTO> page = customerSourceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-sources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-sources")
    @Timed
    public ResponseEntity<List<CustomerSourceDTO>> searchExampleCustomerSources(CustomerSourceSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerSources for searchDTO {}", searchDTO);
        Page<CustomerSourceDTO> page = customerSourceService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-sources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
