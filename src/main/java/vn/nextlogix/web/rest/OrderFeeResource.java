package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.OrderFeeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.OrderFeeDTO;
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
 * REST controller for managing OrderFee.
 */
@RestController
@RequestMapping("/api")
public class OrderFeeResource {

    private final Logger log = LoggerFactory.getLogger(OrderFeeResource.class);

    private static final String ENTITY_NAME = "orderFee";

    private final OrderFeeService orderFeeService;

    public OrderFeeResource(OrderFeeService orderFeeService) {
        this.orderFeeService = orderFeeService;
    }

    /**
     * POST  /order-fees : Create a new orderFee.
     *
     * @param orderFeeDTO the orderFeeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderFeeDTO, or with status 400 (Bad Request) if the orderFee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-fees")
    @Timed
    public ResponseEntity<OrderFeeDTO> createOrderFee(@Valid @RequestBody OrderFeeDTO orderFeeDTO) throws URISyntaxException {
        log.debug("REST request to save OrderFee : {}", orderFeeDTO);
        if (orderFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderFeeDTO result = orderFeeService.save(orderFeeDTO);
        return ResponseEntity.created(new URI("/api/order-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-fees : Updates an existing orderFee.
     *
     * @param orderFeeDTO the orderFeeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderFeeDTO,
     * or with status 400 (Bad Request) if the orderFeeDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderFeeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-fees")
    @Timed
    public ResponseEntity<OrderFeeDTO> updateOrderFee(@Valid @RequestBody OrderFeeDTO orderFeeDTO) throws URISyntaxException {
        log.debug("REST request to update OrderFee : {}", orderFeeDTO);
        if (orderFeeDTO.getId() == null) {
            return createOrderFee(orderFeeDTO);
        }
        OrderFeeDTO result = orderFeeService.save(orderFeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-fees : get all the orderFees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderFees in body
     */
    @GetMapping("/order-fees")
    @Timed
    public List<OrderFeeDTO> getAllOrderFees() {
        log.debug("REST request to get all OrderFees");
        return orderFeeService.findAll();
        }

    /**
     * GET  /order-fees/:id : get the "id" orderFee.
     *
     * @param id the id of the orderFeeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderFeeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-fees/{id}")
    @Timed
    public ResponseEntity<OrderFeeDTO> getOrderFee(@PathVariable Long id) {
        log.debug("REST request to get OrderFee : {}", id);
        OrderFeeDTO orderFeeDTO = orderFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderFeeDTO));
    }

    /**
     * DELETE  /order-fees/:id : delete the "id" orderFee.
     *
     * @param id the id of the orderFeeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-fees/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderFee(@PathVariable Long id) {
        log.debug("REST request to delete OrderFee : {}", id);
        orderFeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-fees?query=:query : search for the orderFee corresponding
     * to the query.
     *
     * @param query the query of the orderFee search
     * @return the result of the search
     */
    @GetMapping("/_search/order-fees")
    @Timed
    public List<OrderFeeDTO> searchOrderFees(@RequestParam String query) {
        log.debug("REST request to search OrderFees for query {}", query);
        return orderFeeService.search(query);
    }

}
