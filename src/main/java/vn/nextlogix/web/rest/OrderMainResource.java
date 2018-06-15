package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.OrderMainService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.OrderMainDTO;
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
 * REST controller for managing OrderMain.
 */
@RestController
@RequestMapping("/api")
public class OrderMainResource {

    private final Logger log = LoggerFactory.getLogger(OrderMainResource.class);

    private static final String ENTITY_NAME = "orderMain";

    private final OrderMainService orderMainService;

    public OrderMainResource(OrderMainService orderMainService) {
        this.orderMainService = orderMainService;
    }

    /**
     * POST  /order-mains : Create a new orderMain.
     *
     * @param orderMainDTO the orderMainDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderMainDTO, or with status 400 (Bad Request) if the orderMain has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-mains")
    @Timed
    public ResponseEntity<OrderMainDTO> createOrderMain(@Valid @RequestBody OrderMainDTO orderMainDTO) throws URISyntaxException {
        log.debug("REST request to save OrderMain : {}", orderMainDTO);
        if (orderMainDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderMain cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderMainDTO result = orderMainService.save(orderMainDTO);
        return ResponseEntity.created(new URI("/api/order-mains/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-mains : Updates an existing orderMain.
     *
     * @param orderMainDTO the orderMainDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderMainDTO,
     * or with status 400 (Bad Request) if the orderMainDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderMainDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-mains")
    @Timed
    public ResponseEntity<OrderMainDTO> updateOrderMain(@Valid @RequestBody OrderMainDTO orderMainDTO) throws URISyntaxException {
        log.debug("REST request to update OrderMain : {}", orderMainDTO);
        if (orderMainDTO.getId() == null) {
            return createOrderMain(orderMainDTO);
        }
        OrderMainDTO result = orderMainService.save(orderMainDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderMainDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-mains : get all the orderMains.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderMains in body
     */
    @GetMapping("/order-mains")
    @Timed
    public List<OrderMainDTO> getAllOrderMains() {
        log.debug("REST request to get all OrderMains");
        return orderMainService.findAll();
        }

    /**
     * GET  /order-mains/:id : get the "id" orderMain.
     *
     * @param id the id of the orderMainDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderMainDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-mains/{id}")
    @Timed
    public ResponseEntity<OrderMainDTO> getOrderMain(@PathVariable Long id) {
        log.debug("REST request to get OrderMain : {}", id);
        OrderMainDTO orderMainDTO = orderMainService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderMainDTO));
    }

    /**
     * DELETE  /order-mains/:id : delete the "id" orderMain.
     *
     * @param id the id of the orderMainDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-mains/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderMain(@PathVariable Long id) {
        log.debug("REST request to delete OrderMain : {}", id);
        orderMainService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-mains?query=:query : search for the orderMain corresponding
     * to the query.
     *
     * @param query the query of the orderMain search
     * @return the result of the search
     */
    @GetMapping("/_search/order-mains")
    @Timed
    public List<OrderMainDTO> searchOrderMains(@RequestParam String query) {
        log.debug("REST request to search OrderMains for query {}", query);
        return orderMainService.search(query);
    }

}
