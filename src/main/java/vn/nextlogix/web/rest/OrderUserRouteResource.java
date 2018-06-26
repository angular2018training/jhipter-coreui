package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderUserRouteService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderUserRouteDTO;
import vn.nextlogix.service.dto.OrderUserRouteSearchDTO;
import vn.nextlogix.service.dto.OrderUserRouteCriteria;
import vn.nextlogix.service.OrderUserRouteQueryService;
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
 * REST controller for managing OrderUserRoute.
 */
@RestController
@RequestMapping("/api")
public class OrderUserRouteResource {

    private final Logger log = LoggerFactory.getLogger(OrderUserRouteResource.class);

    private static final String ENTITY_NAME = "orderUserRoute";

    private final OrderUserRouteService orderUserRouteService;

    private final OrderUserRouteQueryService orderUserRouteQueryService;

    public OrderUserRouteResource(OrderUserRouteService orderUserRouteService, OrderUserRouteQueryService orderUserRouteQueryService     ) {
        this.orderUserRouteService = orderUserRouteService;
        this.orderUserRouteQueryService = orderUserRouteQueryService;


    }

    /**
     * POST  /order-user-routes : Create a new orderUserRoute.
     *
     * @param orderUserRouteDTO the orderUserRouteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderUserRouteDTO, or with status 400 (Bad Request) if the orderUserRoute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-user-routes")
    @Timed
    public ResponseEntity<OrderUserRouteDTO> createOrderUserRoute(@Valid @RequestBody OrderUserRouteDTO orderUserRouteDTO) throws URISyntaxException {
        log.debug("REST request to save OrderUserRoute : {}", orderUserRouteDTO);
        if (orderUserRouteDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderUserRoute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderUserRouteDTO result = orderUserRouteService.save(orderUserRouteDTO);
        return ResponseEntity.created(new URI("/api/order-user-routes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-user-routes : Updates an existing orderUserRoute.
     *
     * @param orderUserRouteDTO the orderUserRouteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderUserRouteDTO,
     * or with status 400 (Bad Request) if the orderUserRouteDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderUserRouteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-user-routes")
    @Timed
    public ResponseEntity<OrderUserRouteDTO> updateOrderUserRoute(@Valid @RequestBody OrderUserRouteDTO orderUserRouteDTO) throws URISyntaxException {
        log.debug("REST request to update OrderUserRoute : {}", orderUserRouteDTO);
        if (orderUserRouteDTO.getId() == null) {
            return createOrderUserRoute(orderUserRouteDTO);
        }
        OrderUserRouteDTO result = orderUserRouteService.save(orderUserRouteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderUserRouteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-user-routes : get all the orderUserRoutes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderUserRoutes in body
     */
    @GetMapping("/order-user-routes")
    @Timed
    public ResponseEntity<List<OrderUserRouteDTO>> getAllOrderUserRoutes(OrderUserRouteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderUserRoutes by criteria: {}", criteria);
        Page<OrderUserRouteDTO> page = orderUserRouteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-user-routes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-user-routes/:id : get the "id" orderUserRoute.
     *
     * @param id the id of the orderUserRouteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderUserRouteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-user-routes/{id}")
    @Timed
    public ResponseEntity<OrderUserRouteDTO> getOrderUserRoute(@PathVariable Long id) {
        log.debug("REST request to get OrderUserRoute : {}", id);
        OrderUserRouteDTO orderUserRouteDTO = orderUserRouteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderUserRouteDTO));
    }

    /**
     * DELETE  /order-user-routes/:id : delete the "id" orderUserRoute.
     *
     * @param id the id of the orderUserRouteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-user-routes/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderUserRoute(@PathVariable Long id) {
        log.debug("REST request to delete OrderUserRoute : {}", id);
        orderUserRouteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-user-routes?query=:query : search for the orderUserRoute corresponding
     * to the query.
     *
     * @param query the query of the orderUserRoute search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-user-routes")
    @Timed
    public ResponseEntity<List<OrderUserRouteDTO>> searchOrderUserRoutes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderUserRoutes for query {}", query);
        Page<OrderUserRouteDTO> page = orderUserRouteService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-user-routes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-user-routes")
    @Timed
    public ResponseEntity<List<OrderUserRouteDTO>> searchExampleOrderUserRoutes(OrderUserRouteSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderUserRoutes for searchDTO {}", searchDTO);
        Page<OrderUserRouteDTO> page = orderUserRouteService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-user-routes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
