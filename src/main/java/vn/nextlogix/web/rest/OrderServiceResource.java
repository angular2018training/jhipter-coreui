package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderServiceService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderServiceDTO;
import vn.nextlogix.service.dto.OrderServiceSearchDTO;
import vn.nextlogix.service.dto.OrderServiceCriteria;
import vn.nextlogix.service.OrderServiceQueryService;
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
 * REST controller for managing OrderService.
 */
@RestController
@RequestMapping("/api")
public class OrderServiceResource {

    private final Logger log = LoggerFactory.getLogger(OrderServiceResource.class);

    private static final String ENTITY_NAME = "orderService";

    private final OrderServiceService orderServiceService;

    private final OrderServiceQueryService orderServiceQueryService;

    public OrderServiceResource(OrderServiceService orderServiceService, OrderServiceQueryService orderServiceQueryService     ) {
        this.orderServiceService = orderServiceService;
        this.orderServiceQueryService = orderServiceQueryService;


    }

    /**
     * POST  /order-services : Create a new orderService.
     *
     * @param orderServiceDTO the orderServiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderServiceDTO, or with status 400 (Bad Request) if the orderService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-services")
    @Timed
    public ResponseEntity<OrderServiceDTO> createOrderService(@Valid @RequestBody OrderServiceDTO orderServiceDTO) throws URISyntaxException {
        log.debug("REST request to save OrderService : {}", orderServiceDTO);
        if (orderServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderServiceDTO result = orderServiceService.save(orderServiceDTO);
        return ResponseEntity.created(new URI("/api/order-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-services : Updates an existing orderService.
     *
     * @param orderServiceDTO the orderServiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderServiceDTO,
     * or with status 400 (Bad Request) if the orderServiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderServiceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-services")
    @Timed
    public ResponseEntity<OrderServiceDTO> updateOrderService(@Valid @RequestBody OrderServiceDTO orderServiceDTO) throws URISyntaxException {
        log.debug("REST request to update OrderService : {}", orderServiceDTO);
        if (orderServiceDTO.getId() == null) {
            return createOrderService(orderServiceDTO);
        }
        OrderServiceDTO result = orderServiceService.save(orderServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderServiceDTO.getId().toString()))
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
    public ResponseEntity<List<OrderServiceDTO>> getAllOrderServices(OrderServiceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderServices by criteria: {}", criteria);
        Page<OrderServiceDTO> page = orderServiceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-services/:id : get the "id" orderService.
     *
     * @param id the id of the orderServiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderServiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-services/{id}")
    @Timed
    public ResponseEntity<OrderServiceDTO> getOrderService(@PathVariable Long id) {
        log.debug("REST request to get OrderService : {}", id);
        OrderServiceDTO orderServiceDTO = orderServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderServiceDTO));
    }

    /**
     * DELETE  /order-services/:id : delete the "id" orderService.
     *
     * @param id the id of the orderServiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderService(@PathVariable Long id) {
        log.debug("REST request to delete OrderService : {}", id);
        orderServiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-services?query=:query : search for the orderService corresponding
     * to the query.
     *
     * @param query the query of the orderService search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-services")
    @Timed
    public ResponseEntity<List<OrderServiceDTO>> searchOrderServices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderServices for query {}", query);
        Page<OrderServiceDTO> page = orderServiceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-services")
    @Timed
    public ResponseEntity<List<OrderServiceDTO>> searchExampleOrderServices(OrderServiceSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderServices for searchDTO {}", searchDTO);
        Page<OrderServiceDTO> page = orderServiceService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
