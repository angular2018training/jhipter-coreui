package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.OrderPickupService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.OrderPickupDTO;
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
 * REST controller for managing OrderPickup.
 */
@RestController
@RequestMapping("/api")
public class OrderPickupResource {

    private final Logger log = LoggerFactory.getLogger(OrderPickupResource.class);

    private static final String ENTITY_NAME = "orderPickup";

    private final OrderPickupService orderPickupService;

    public OrderPickupResource(OrderPickupService orderPickupService) {
        this.orderPickupService = orderPickupService;
    }

    /**
     * POST  /order-pickups : Create a new orderPickup.
     *
     * @param orderPickupDTO the orderPickupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderPickupDTO, or with status 400 (Bad Request) if the orderPickup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-pickups")
    @Timed
    public ResponseEntity<OrderPickupDTO> createOrderPickup(@Valid @RequestBody OrderPickupDTO orderPickupDTO) throws URISyntaxException {
        log.debug("REST request to save OrderPickup : {}", orderPickupDTO);
        if (orderPickupDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderPickup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderPickupDTO result = orderPickupService.save(orderPickupDTO);
        return ResponseEntity.created(new URI("/api/order-pickups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-pickups : Updates an existing orderPickup.
     *
     * @param orderPickupDTO the orderPickupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderPickupDTO,
     * or with status 400 (Bad Request) if the orderPickupDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderPickupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-pickups")
    @Timed
    public ResponseEntity<OrderPickupDTO> updateOrderPickup(@Valid @RequestBody OrderPickupDTO orderPickupDTO) throws URISyntaxException {
        log.debug("REST request to update OrderPickup : {}", orderPickupDTO);
        if (orderPickupDTO.getId() == null) {
            return createOrderPickup(orderPickupDTO);
        }
        OrderPickupDTO result = orderPickupService.save(orderPickupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderPickupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-pickups : get all the orderPickups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderPickups in body
     */
    @GetMapping("/order-pickups")
    @Timed
    public List<OrderPickupDTO> getAllOrderPickups() {
        log.debug("REST request to get all OrderPickups");
        return orderPickupService.findAll();
        }

    /**
     * GET  /order-pickups/:id : get the "id" orderPickup.
     *
     * @param id the id of the orderPickupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderPickupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-pickups/{id}")
    @Timed
    public ResponseEntity<OrderPickupDTO> getOrderPickup(@PathVariable Long id) {
        log.debug("REST request to get OrderPickup : {}", id);
        OrderPickupDTO orderPickupDTO = orderPickupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderPickupDTO));
    }

    /**
     * DELETE  /order-pickups/:id : delete the "id" orderPickup.
     *
     * @param id the id of the orderPickupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-pickups/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderPickup(@PathVariable Long id) {
        log.debug("REST request to delete OrderPickup : {}", id);
        orderPickupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-pickups?query=:query : search for the orderPickup corresponding
     * to the query.
     *
     * @param query the query of the orderPickup search
     * @return the result of the search
     */
    @GetMapping("/_search/order-pickups")
    @Timed
    public List<OrderPickupDTO> searchOrderPickups(@RequestParam String query) {
        log.debug("REST request to search OrderPickups for query {}", query);
        return orderPickupService.search(query);
    }

}
