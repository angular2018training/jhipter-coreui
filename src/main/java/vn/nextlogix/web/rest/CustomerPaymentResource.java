package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.CustomerPaymentService;
import vn.nextlogix.service.CustomerService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.CustomerPaymentDTO;
import vn.nextlogix.service.dto.CustomerPaymentSearchDTO;
import vn.nextlogix.service.dto.CustomerDTO;
import vn.nextlogix.service.dto.CustomerPaymentCriteria;
import vn.nextlogix.service.CustomerPaymentQueryService;
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
 * REST controller for managing CustomerPayment.
 */
@RestController
@RequestMapping("/api")
public class CustomerPaymentResource {

	private final Logger log = LoggerFactory.getLogger(CustomerPaymentResource.class);

	private static final String ENTITY_NAME = "customerPayment";

	private final CustomerPaymentService customerPaymentService;

	private final CustomerPaymentQueryService customerPaymentQueryService;

	private final CustomerService customerService;

	public CustomerPaymentResource(CustomerPaymentService customerPaymentService,
			CustomerPaymentQueryService customerPaymentQueryService, CustomerService customerService) {
		this.customerPaymentService = customerPaymentService;
		this.customerPaymentQueryService = customerPaymentQueryService;
		this.customerService = customerService;

	}

	/**
	 * POST /customer-payments : Create a new customerPayment.
	 *
	 * @param customerPaymentDTO the customerPaymentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         customerPaymentDTO, or with status 400 (Bad Request) if the
	 *         customerPayment has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/customer-payments")
	@Timed
	public ResponseEntity<CustomerPaymentDTO> createCustomerPayment(
			@Valid @RequestBody CustomerPaymentDTO customerPaymentDTO,
			@RequestParam(value = "customerId") Long customerId) throws URISyntaxException {
		log.debug("REST request to save CustomerPayment : {}", customerPaymentDTO);
		if (customerPaymentDTO.getId() != null) {
			throw new BadRequestAlertException("A new customerPayment cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		CustomerPaymentDTO result = customerPaymentService.save(customerPaymentDTO);
		CustomerDTO customerDTO = customerService.findOne(customerId);
		customerDTO.setPaymentId(result.getId());
		customerService.save(customerDTO);
		return ResponseEntity.created(new URI("/api/customer-payments/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /customer-payments : Updates an existing customerPayment.
	 *
	 * @param customerPaymentDTO the customerPaymentDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         customerPaymentDTO, or with status 400 (Bad Request) if the
	 *         customerPaymentDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the customerPaymentDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/customer-payments")
	@Timed
	public ResponseEntity<CustomerPaymentDTO> updateCustomerPayment(
			@Valid @RequestBody CustomerPaymentDTO customerPaymentDTO,
			@RequestParam(value = "customerId") Long customerId) throws URISyntaxException {
		log.debug("REST request to update CustomerPayment : {}", customerPaymentDTO);
		if (customerPaymentDTO.getId() == null) {
			return createCustomerPayment(customerPaymentDTO, customerId);
		}
		CustomerPaymentDTO result = customerPaymentService.save(customerPaymentDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerPaymentDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /customer-payments : get all the customerPayments.
	 *
	 * @param pageable the pagination information
	 * @param criteria the criterias which the requested entities should match
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         customerPayments in body
	 */
	@GetMapping("/customer-payments")
	@Timed
	public ResponseEntity<List<CustomerPaymentDTO>> getAllCustomerPayments(CustomerPaymentCriteria criteria,
			Pageable pageable) {
		log.debug("REST request to get CustomerPayments by criteria: {}", criteria);
		Page<CustomerPaymentDTO> page = customerPaymentQueryService.findByCriteria(criteria, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-payments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /customer-payments/:id : get the "id" customerPayment.
	 *
	 * @param id the id of the customerPaymentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         customerPaymentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/customer-payments/{id}")
	@Timed
	public ResponseEntity<CustomerPaymentDTO> getCustomerPayment(@PathVariable Long id) {
		log.debug("REST request to get CustomerPayment : {}", id);
		CustomerPaymentDTO customerPaymentDTO = customerPaymentService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerPaymentDTO));
	}

	/**
	 * DELETE /customer-payments/:id : delete the "id" customerPayment.
	 *
	 * @param id the id of the customerPaymentDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/customer-payments/{id}")
	@Timed
	public ResponseEntity<Void> deleteCustomerPayment(@PathVariable Long id) {
		log.debug("REST request to delete CustomerPayment : {}", id);
		customerPaymentService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * SEARCH /_search/customer-payments?query=:query : search for the
	 * customerPayment corresponding to the query.
	 *
	 * @param query    the query of the customerPayment search
	 * @param pageable the pagination information
	 * @return the result of the search
	 */
	@GetMapping("/_search/customer-payments")
	@Timed
	public ResponseEntity<List<CustomerPaymentDTO>> searchCustomerPayments(@RequestParam String query,
			Pageable pageable) {
		log.debug("REST request to search for a page of CustomerPayments for query {}", query);
		Page<CustomerPaymentDTO> page = customerPaymentService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page,
				"/api/_search/customer-payments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/_search_example/customer-payments")
	@Timed
	public ResponseEntity<List<CustomerPaymentDTO>> searchExampleCustomerPayments(CustomerPaymentSearchDTO searchDTO,
			Pageable pageable) throws ApplicationException {
		log.debug("REST request to search example for a page of CustomerPayments for searchDTO {}", searchDTO);
		Page<CustomerPaymentDTO> page = customerPaymentService.searchExample(searchDTO, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page,
				"/api/_search_example/customer-payments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

}
