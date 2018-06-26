package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderUserRouteTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderUserRouteTypeDTO;
import vn.nextlogix.service.dto.OrderUserRouteTypeSearchDTO;
import vn.nextlogix.service.dto.OrderUserRouteTypeCriteria;
import vn.nextlogix.service.OrderUserRouteTypeQueryService;
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
 * REST controller for managing OrderUserRouteType.
 */
@RestController
@RequestMapping("/api")
public class OrderUserRouteTypeResource {

    private final Logger log = LoggerFactory.getLogger(OrderUserRouteTypeResource.class);

    private static final String ENTITY_NAME = "orderUserRouteType";

    private final OrderUserRouteTypeService orderUserRouteTypeService;

    private final OrderUserRouteTypeQueryService orderUserRouteTypeQueryService;

    public OrderUserRouteTypeResource(OrderUserRouteTypeService orderUserRouteTypeService, OrderUserRouteTypeQueryService orderUserRouteTypeQueryService     ) {
        this.orderUserRouteTypeService = orderUserRouteTypeService;
        this.orderUserRouteTypeQueryService = orderUserRouteTypeQueryService;


    }

    /**
     * POST  /order-user-route-types : Create a new orderUserRouteType.
     *
     * @param orderUserRouteTypeDTO the orderUserRouteTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderUserRouteTypeDTO, or with status 400 (Bad Request) if the orderUserRouteType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-user-route-types")
    @Timed
    public ResponseEntity<OrderUserRouteTypeDTO> createOrderUserRouteType(@Valid @RequestBody OrderUserRouteTypeDTO orderUserRouteTypeDTO) throws URISyntaxException {
        log.debug("REST request to save OrderUserRouteType : {}", orderUserRouteTypeDTO);
        if (orderUserRouteTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderUserRouteType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderUserRouteTypeDTO result = orderUserRouteTypeService.save(orderUserRouteTypeDTO);
        return ResponseEntity.created(new URI("/api/order-user-route-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-user-route-types : Updates an existing orderUserRouteType.
     *
     * @param orderUserRouteTypeDTO the orderUserRouteTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderUserRouteTypeDTO,
     * or with status 400 (Bad Request) if the orderUserRouteTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderUserRouteTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-user-route-types")
    @Timed
    public ResponseEntity<OrderUserRouteTypeDTO> updateOrderUserRouteType(@Valid @RequestBody OrderUserRouteTypeDTO orderUserRouteTypeDTO) throws URISyntaxException {
        log.debug("REST request to update OrderUserRouteType : {}", orderUserRouteTypeDTO);
        if (orderUserRouteTypeDTO.getId() == null) {
            return createOrderUserRouteType(orderUserRouteTypeDTO);
        }
        OrderUserRouteTypeDTO result = orderUserRouteTypeService.save(orderUserRouteTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderUserRouteTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-user-route-types : get all the orderUserRouteTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderUserRouteTypes in body
     */
    @GetMapping("/order-user-route-types")
    @Timed
    public ResponseEntity<List<OrderUserRouteTypeDTO>> getAllOrderUserRouteTypes(OrderUserRouteTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderUserRouteTypes by criteria: {}", criteria);
        Page<OrderUserRouteTypeDTO> page = orderUserRouteTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-user-route-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-user-route-types/:id : get the "id" orderUserRouteType.
     *
     * @param id the id of the orderUserRouteTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderUserRouteTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-user-route-types/{id}")
    @Timed
    public ResponseEntity<OrderUserRouteTypeDTO> getOrderUserRouteType(@PathVariable Long id) {
        log.debug("REST request to get OrderUserRouteType : {}", id);
        OrderUserRouteTypeDTO orderUserRouteTypeDTO = orderUserRouteTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderUserRouteTypeDTO));
    }

    /**
     * DELETE  /order-user-route-types/:id : delete the "id" orderUserRouteType.
     *
     * @param id the id of the orderUserRouteTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-user-route-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderUserRouteType(@PathVariable Long id) {
        log.debug("REST request to delete OrderUserRouteType : {}", id);
        orderUserRouteTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-user-route-types?query=:query : search for the orderUserRouteType corresponding
     * to the query.
     *
     * @param query the query of the orderUserRouteType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-user-route-types")
    @Timed
    public ResponseEntity<List<OrderUserRouteTypeDTO>> searchOrderUserRouteTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderUserRouteTypes for query {}", query);
        Page<OrderUserRouteTypeDTO> page = orderUserRouteTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-user-route-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-user-route-types")
    @Timed
    public ResponseEntity<List<OrderUserRouteTypeDTO>> searchExampleOrderUserRouteTypes(OrderUserRouteTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderUserRouteTypes for searchDTO {}", searchDTO);
        Page<OrderUserRouteTypeDTO> page = orderUserRouteTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-user-route-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
