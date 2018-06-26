package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderStatusService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderStatusDTO;
import vn.nextlogix.service.dto.OrderStatusSearchDTO;
import vn.nextlogix.service.dto.OrderStatusCriteria;
import vn.nextlogix.service.OrderStatusQueryService;
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
 * REST controller for managing OrderStatus.
 */
@RestController
@RequestMapping("/api")
public class OrderStatusResource {

    private final Logger log = LoggerFactory.getLogger(OrderStatusResource.class);

    private static final String ENTITY_NAME = "orderStatus";

    private final OrderStatusService orderStatusService;

    private final OrderStatusQueryService orderStatusQueryService;

    public OrderStatusResource(OrderStatusService orderStatusService, OrderStatusQueryService orderStatusQueryService     ) {
        this.orderStatusService = orderStatusService;
        this.orderStatusQueryService = orderStatusQueryService;


    }

    /**
     * POST  /order-statuses : Create a new orderStatus.
     *
     * @param orderStatusDTO the orderStatusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderStatusDTO, or with status 400 (Bad Request) if the orderStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-statuses")
    @Timed
    public ResponseEntity<OrderStatusDTO> createOrderStatus(@Valid @RequestBody OrderStatusDTO orderStatusDTO) throws URISyntaxException {
        log.debug("REST request to save OrderStatus : {}", orderStatusDTO);
        if (orderStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderStatusDTO result = orderStatusService.save(orderStatusDTO);
        return ResponseEntity.created(new URI("/api/order-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-statuses : Updates an existing orderStatus.
     *
     * @param orderStatusDTO the orderStatusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderStatusDTO,
     * or with status 400 (Bad Request) if the orderStatusDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderStatusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-statuses")
    @Timed
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@Valid @RequestBody OrderStatusDTO orderStatusDTO) throws URISyntaxException {
        log.debug("REST request to update OrderStatus : {}", orderStatusDTO);
        if (orderStatusDTO.getId() == null) {
            return createOrderStatus(orderStatusDTO);
        }
        OrderStatusDTO result = orderStatusService.save(orderStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-statuses : get all the orderStatuses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderStatuses in body
     */
    @GetMapping("/order-statuses")
    @Timed
    public ResponseEntity<List<OrderStatusDTO>> getAllOrderStatuses(OrderStatusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderStatuses by criteria: {}", criteria);
        Page<OrderStatusDTO> page = orderStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-statuses/:id : get the "id" orderStatus.
     *
     * @param id the id of the orderStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-statuses/{id}")
    @Timed
    public ResponseEntity<OrderStatusDTO> getOrderStatus(@PathVariable Long id) {
        log.debug("REST request to get OrderStatus : {}", id);
        OrderStatusDTO orderStatusDTO = orderStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderStatusDTO));
    }

    /**
     * DELETE  /order-statuses/:id : delete the "id" orderStatus.
     *
     * @param id the id of the orderStatusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable Long id) {
        log.debug("REST request to delete OrderStatus : {}", id);
        orderStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-statuses?query=:query : search for the orderStatus corresponding
     * to the query.
     *
     * @param query the query of the orderStatus search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-statuses")
    @Timed
    public ResponseEntity<List<OrderStatusDTO>> searchOrderStatuses(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderStatuses for query {}", query);
        Page<OrderStatusDTO> page = orderStatusService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-statuses")
    @Timed
    public ResponseEntity<List<OrderStatusDTO>> searchExampleOrderStatuses(OrderStatusSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderStatuses for searchDTO {}", searchDTO);
        Page<OrderStatusDTO> page = orderStatusService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
