package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerLegalFileUploadService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerLegalFileUploadDTO;
import vn.nextlogix.service.dto.CustomerLegalFileUploadSearchDTO;
import vn.nextlogix.service.dto.CustomerLegalFileUploadCriteria;
import vn.nextlogix.service.CustomerLegalFileUploadQueryService;
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
 * REST controller for managing CustomerLegalFileUpload.
 */
@RestController
@RequestMapping("/api")
public class CustomerLegalFileUploadResource {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalFileUploadResource.class);

    private static final String ENTITY_NAME = "customerLegalFileUpload";

    private final CustomerLegalFileUploadService customerLegalFileUploadService;

    private final CustomerLegalFileUploadQueryService customerLegalFileUploadQueryService;

    public CustomerLegalFileUploadResource(CustomerLegalFileUploadService customerLegalFileUploadService, CustomerLegalFileUploadQueryService customerLegalFileUploadQueryService     ) {
        this.customerLegalFileUploadService = customerLegalFileUploadService;
        this.customerLegalFileUploadQueryService = customerLegalFileUploadQueryService;


    }

    /**
     * POST  /customer-legal-file-uploads : Create a new customerLegalFileUpload.
     *
     * @param customerLegalFileUploadDTO the customerLegalFileUploadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerLegalFileUploadDTO, or with status 400 (Bad Request) if the customerLegalFileUpload has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-legal-file-uploads")
    @Timed
    public ResponseEntity<CustomerLegalFileUploadDTO> createCustomerLegalFileUpload(@Valid @RequestBody CustomerLegalFileUploadDTO customerLegalFileUploadDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerLegalFileUpload : {}", customerLegalFileUploadDTO);
        if (customerLegalFileUploadDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerLegalFileUpload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerLegalFileUploadDTO result = customerLegalFileUploadService.save(customerLegalFileUploadDTO);
        return ResponseEntity.created(new URI("/api/customer-legal-file-uploads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-legal-file-uploads : Updates an existing customerLegalFileUpload.
     *
     * @param customerLegalFileUploadDTO the customerLegalFileUploadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerLegalFileUploadDTO,
     * or with status 400 (Bad Request) if the customerLegalFileUploadDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerLegalFileUploadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-legal-file-uploads")
    @Timed
    public ResponseEntity<CustomerLegalFileUploadDTO> updateCustomerLegalFileUpload(@Valid @RequestBody CustomerLegalFileUploadDTO customerLegalFileUploadDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerLegalFileUpload : {}", customerLegalFileUploadDTO);
        if (customerLegalFileUploadDTO.getId() == null) {
            return createCustomerLegalFileUpload(customerLegalFileUploadDTO);
        }
        CustomerLegalFileUploadDTO result = customerLegalFileUploadService.save(customerLegalFileUploadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerLegalFileUploadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-legal-file-uploads : get all the customerLegalFileUploads.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of customerLegalFileUploads in body
     */
    @GetMapping("/customer-legal-file-uploads")
    @Timed
    public ResponseEntity<List<CustomerLegalFileUploadDTO>> getAllCustomerLegalFileUploads(CustomerLegalFileUploadCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerLegalFileUploads by criteria: {}", criteria);
        Page<CustomerLegalFileUploadDTO> page = customerLegalFileUploadQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-legal-file-uploads");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-legal-file-uploads/:id : get the "id" customerLegalFileUpload.
     *
     * @param id the id of the customerLegalFileUploadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerLegalFileUploadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-legal-file-uploads/{id}")
    @Timed
    public ResponseEntity<CustomerLegalFileUploadDTO> getCustomerLegalFileUpload(@PathVariable Long id) {
        log.debug("REST request to get CustomerLegalFileUpload : {}", id);
        CustomerLegalFileUploadDTO customerLegalFileUploadDTO = customerLegalFileUploadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerLegalFileUploadDTO));
    }

    /**
     * DELETE  /customer-legal-file-uploads/:id : delete the "id" customerLegalFileUpload.
     *
     * @param id the id of the customerLegalFileUploadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-legal-file-uploads/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerLegalFileUpload(@PathVariable Long id) {
        log.debug("REST request to delete CustomerLegalFileUpload : {}", id);
        customerLegalFileUploadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-legal-file-uploads?query=:query : search for the customerLegalFileUpload corresponding
     * to the query.
     *
     * @param query the query of the customerLegalFileUpload search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/customer-legal-file-uploads")
    @Timed
    public ResponseEntity<List<CustomerLegalFileUploadDTO>> searchCustomerLegalFileUploads(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CustomerLegalFileUploads for query {}", query);
        Page<CustomerLegalFileUploadDTO> page = customerLegalFileUploadService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customer-legal-file-uploads");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/customer-legal-file-uploads")
    @Timed
    public ResponseEntity<List<CustomerLegalFileUploadDTO>> searchExampleCustomerLegalFileUploads(CustomerLegalFileUploadSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of CustomerLegalFileUploads for searchDTO {}", searchDTO);
        Page<CustomerLegalFileUploadDTO> page = customerLegalFileUploadService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/customer-legal-file-uploads");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
