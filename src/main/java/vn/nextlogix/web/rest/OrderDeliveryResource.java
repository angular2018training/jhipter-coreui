package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.OrderDeliveryService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.OrderDeliveryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing OrderDelivery.
 */
@RestController
@RequestMapping("/api")
public class OrderDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryResource.class);

    private static final String ENTITY_NAME = "orderDelivery";

    private final OrderDeliveryService orderDeliveryService;

    public OrderDeliveryResource(OrderDeliveryService orderDeliveryService) {
        this.orderDeliveryService = orderDeliveryService;
    }

    /**
     * POST  /order-deliveries : Create a new orderDelivery.
     *
     * @param orderDeliveryDTO the orderDeliveryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderDeliveryDTO, or with status 400 (Bad Request) if the orderDelivery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-deliveries")
    @Timed
    public ResponseEntity<OrderDeliveryDTO> createOrderDelivery(@Valid @RequestBody OrderDeliveryDTO orderDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to save OrderDelivery : {}", orderDeliveryDTO);
        if (orderDeliveryDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderDeliveryDTO result = orderDeliveryService.save(orderDeliveryDTO);
        return ResponseEntity.created(new URI("/api/order-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-deliveries : Updates an existing orderDelivery.
     *
     * @param orderDeliveryDTO the orderDeliveryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderDeliveryDTO,
     * or with status 400 (Bad Request) if the orderDeliveryDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderDeliveryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-deliveries")
    @Timed
    public ResponseEntity<OrderDeliveryDTO> updateOrderDelivery(@Valid @RequestBody OrderDeliveryDTO orderDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to update OrderDelivery : {}", orderDeliveryDTO);
        if (orderDeliveryDTO.getId() == null) {
            return createOrderDelivery(orderDeliveryDTO);
        }
        OrderDeliveryDTO result = orderDeliveryService.save(orderDeliveryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderDeliveryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-deliveries : get all the orderDeliveries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderDeliveries in body
     */
    @GetMapping("/order-deliveries")
    @Timed
    public List<OrderDeliveryDTO> getAllOrderDeliveries() {
        log.debug("REST request to get all OrderDeliveries");
        return orderDeliveryService.findAll();
        }

    /**
     * GET  /order-deliveries/:id : get the "id" orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderDeliveryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-deliveries/{id}")
    @Timed
    public ResponseEntity<OrderDeliveryDTO> getOrderDelivery(@PathVariable Long id) {
        log.debug("REST request to get OrderDelivery : {}", id);
        OrderDeliveryDTO orderDeliveryDTO = orderDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderDeliveryDTO));
    }

    /**
     * DELETE  /order-deliveries/:id : delete the "id" orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-deliveries/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderDelivery(@PathVariable Long id) {
        log.debug("REST request to delete OrderDelivery : {}", id);
        orderDeliveryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-deliveries?query=:query : search for the orderDelivery corresponding
     * to the query.
     *
     * @param query the query of the orderDelivery search
     * @return the result of the search
     */
    @GetMapping("/_search/order-deliveries")
    @Timed
    public List<OrderDeliveryDTO> searchOrderDeliveries(@RequestParam String query) {
        log.debug("REST request to search OrderDeliveries for query {}", query);
        return orderDeliveryService.search(query);
    }

}
