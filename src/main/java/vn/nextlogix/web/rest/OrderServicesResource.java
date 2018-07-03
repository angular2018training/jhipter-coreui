package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderServicesService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderServicesDTO;
import vn.nextlogix.service.dto.OrderServicesSearchDTO;
import vn.nextlogix.service.dto.OrderServicesCriteria;
import vn.nextlogix.service.OrderServicesQueryService;
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
 * REST controller for managing OrderServices.
 */
@RestController
@RequestMapping("/api")
public class OrderServicesResource {

    private final Logger log = LoggerFactory.getLogger(OrderServicesResource.class);

    private static final String ENTITY_NAME = "orderServices";

    private final OrderServicesService orderServicesService;

    private final OrderServicesQueryService orderServicesQueryService;

    public OrderServicesResource(OrderServicesService orderServicesService, OrderServicesQueryService orderServicesQueryService     ) {
        this.orderServicesService = orderServicesService;
        this.orderServicesQueryService = orderServicesQueryService;


    }

    /**
     * POST  /order-services : Create a new orderServices.
     *
     * @param orderServicesDTO the orderServicesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderServicesDTO, or with status 400 (Bad Request) if the orderServices has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-services")
    @Timed
    public ResponseEntity<OrderServicesDTO> createOrderServices(@Valid @RequestBody OrderServicesDTO orderServicesDTO) throws URISyntaxException {
        log.debug("REST request to save OrderServices : {}", orderServicesDTO);
        if (orderServicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderServicesDTO result = orderServicesService.save(orderServicesDTO);
        return ResponseEntity.created(new URI("/api/order-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-services : Updates an existing orderServices.
     *
     * @param orderServicesDTO the orderServicesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderServicesDTO,
     * or with status 400 (Bad Request) if the orderServicesDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderServicesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-services")
    @Timed
    public ResponseEntity<OrderServicesDTO> updateOrderServices(@Valid @RequestBody OrderServicesDTO orderServicesDTO) throws URISyntaxException {
        log.debug("REST request to update OrderServices : {}", orderServicesDTO);
        if (orderServicesDTO.getId() == null) {
            return createOrderServices(orderServicesDTO);
        }
        OrderServicesDTO result = orderServicesService.save(orderServicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderServicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-services : get all the orderServices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderServices in body
     */
    @GetMapping("/order-services")
    @Timed
    public ResponseEntity<List<OrderServicesDTO>> getAllOrderServices(OrderServicesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderServices by criteria: {}", criteria);
        Page<OrderServicesDTO> page = orderServicesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-services/:id : get the "id" orderServices.
     *
     * @param id the id of the orderServicesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderServicesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-services/{id}")
    @Timed
    public ResponseEntity<OrderServicesDTO> getOrderServices(@PathVariable Long id) {
        log.debug("REST request to get OrderServices : {}", id);
        OrderServicesDTO orderServicesDTO = orderServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderServicesDTO));
    }

    /**
     * DELETE  /order-services/:id : delete the "id" orderServices.
     *
     * @param id the id of the orderServicesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderServices(@PathVariable Long id) {
        log.debug("REST request to delete OrderServices : {}", id);
        orderServicesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-services?query=:query : search for the orderServices corresponding
     * to the query.
     *
     * @param query the query of the orderServices search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-services")
    @Timed
    public ResponseEntity<List<OrderServicesDTO>> searchOrderServices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderServices for query {}", query);
        Page<OrderServicesDTO> page = orderServicesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-services")
    @Timed
    public ResponseEntity<List<OrderServicesDTO>> searchExampleOrderServices(OrderServicesSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderServices for searchDTO {}", searchDTO);
        Page<OrderServicesDTO> page = orderServicesService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
