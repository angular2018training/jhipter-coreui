package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderSubServicesService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderSubServicesDTO;
import vn.nextlogix.service.dto.OrderSubServicesSearchDTO;
import vn.nextlogix.service.dto.OrderSubServicesCriteria;
import vn.nextlogix.service.OrderSubServicesQueryService;
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
 * REST controller for managing OrderSubServices.
 */
@RestController
@RequestMapping("/api")
public class OrderSubServicesResource {

    private final Logger log = LoggerFactory.getLogger(OrderSubServicesResource.class);

    private static final String ENTITY_NAME = "orderSubServices";

    private final OrderSubServicesService orderSubServicesService;

    private final OrderSubServicesQueryService orderSubServicesQueryService;

    public OrderSubServicesResource(OrderSubServicesService orderSubServicesService, OrderSubServicesQueryService orderSubServicesQueryService     ) {
        this.orderSubServicesService = orderSubServicesService;
        this.orderSubServicesQueryService = orderSubServicesQueryService;


    }

    /**
     * POST  /order-sub-services : Create a new orderSubServices.
     *
     * @param orderSubServicesDTO the orderSubServicesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderSubServicesDTO, or with status 400 (Bad Request) if the orderSubServices has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-sub-services")
    @Timed
    public ResponseEntity<OrderSubServicesDTO> createOrderSubServices(@Valid @RequestBody OrderSubServicesDTO orderSubServicesDTO) throws URISyntaxException {
        log.debug("REST request to save OrderSubServices : {}", orderSubServicesDTO);
        if (orderSubServicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderSubServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderSubServicesDTO result = orderSubServicesService.save(orderSubServicesDTO);
        return ResponseEntity.created(new URI("/api/order-sub-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-sub-services : Updates an existing orderSubServices.
     *
     * @param orderSubServicesDTO the orderSubServicesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderSubServicesDTO,
     * or with status 400 (Bad Request) if the orderSubServicesDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderSubServicesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-sub-services")
    @Timed
    public ResponseEntity<OrderSubServicesDTO> updateOrderSubServices(@Valid @RequestBody OrderSubServicesDTO orderSubServicesDTO) throws URISyntaxException {
        log.debug("REST request to update OrderSubServices : {}", orderSubServicesDTO);
        if (orderSubServicesDTO.getId() == null) {
            return createOrderSubServices(orderSubServicesDTO);
        }
        OrderSubServicesDTO result = orderSubServicesService.save(orderSubServicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderSubServicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-sub-services : get all the orderSubServices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderSubServices in body
     */
    @GetMapping("/order-sub-services")
    @Timed
    public ResponseEntity<List<OrderSubServicesDTO>> getAllOrderSubServices(OrderSubServicesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderSubServices by criteria: {}", criteria);
        Page<OrderSubServicesDTO> page = orderSubServicesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-sub-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-sub-services/:id : get the "id" orderSubServices.
     *
     * @param id the id of the orderSubServicesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderSubServicesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-sub-services/{id}")
    @Timed
    public ResponseEntity<OrderSubServicesDTO> getOrderSubServices(@PathVariable Long id) {
        log.debug("REST request to get OrderSubServices : {}", id);
        OrderSubServicesDTO orderSubServicesDTO = orderSubServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderSubServicesDTO));
    }

    /**
     * DELETE  /order-sub-services/:id : delete the "id" orderSubServices.
     *
     * @param id the id of the orderSubServicesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-sub-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderSubServices(@PathVariable Long id) {
        log.debug("REST request to delete OrderSubServices : {}", id);
        orderSubServicesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-sub-services?query=:query : search for the orderSubServices corresponding
     * to the query.
     *
     * @param query the query of the orderSubServices search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-sub-services")
    @Timed
    public ResponseEntity<List<OrderSubServicesDTO>> searchOrderSubServices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderSubServices for query {}", query);
        Page<OrderSubServicesDTO> page = orderSubServicesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-sub-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-sub-services")
    @Timed
    public ResponseEntity<List<OrderSubServicesDTO>> searchExampleOrderSubServices(OrderSubServicesSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderSubServices for searchDTO {}", searchDTO);
        Page<OrderSubServicesDTO> page = orderSubServicesService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-sub-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
