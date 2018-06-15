package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.OrderServiceService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.OrderServiceDTO;
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
 * REST controller for managing OrderService.
 */
@RestController
@RequestMapping("/api")
public class OrderServiceResource {

    private final Logger log = LoggerFactory.getLogger(OrderServiceResource.class);

    private static final String ENTITY_NAME = "orderService";

    private final OrderServiceService orderServiceService;

    public OrderServiceResource(OrderServiceService orderServiceService) {
        this.orderServiceService = orderServiceService;
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
     * @return the ResponseEntity with status 200 (OK) and the list of orderServices in body
     */
    @GetMapping("/order-services")
    @Timed
    public List<OrderServiceDTO> getAllOrderServices() {
        log.debug("REST request to get all OrderServices");
        return orderServiceService.findAll();
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
     * @return the result of the search
     */
    @GetMapping("/_search/order-services")
    @Timed
    public List<OrderServiceDTO> searchOrderServices(@RequestParam String query) {
        log.debug("REST request to search OrderServices for query {}", query);
        return orderServiceService.search(query);
    }

}
