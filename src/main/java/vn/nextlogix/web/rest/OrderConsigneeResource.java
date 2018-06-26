package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderConsigneeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderConsigneeDTO;
import vn.nextlogix.service.dto.OrderConsigneeSearchDTO;
import vn.nextlogix.service.dto.OrderConsigneeCriteria;
import vn.nextlogix.service.OrderConsigneeQueryService;
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
 * REST controller for managing OrderConsignee.
 */
@RestController
@RequestMapping("/api")
public class OrderConsigneeResource {

    private final Logger log = LoggerFactory.getLogger(OrderConsigneeResource.class);

    private static final String ENTITY_NAME = "orderConsignee";

    private final OrderConsigneeService orderConsigneeService;

    private final OrderConsigneeQueryService orderConsigneeQueryService;

    public OrderConsigneeResource(OrderConsigneeService orderConsigneeService, OrderConsigneeQueryService orderConsigneeQueryService     ) {
        this.orderConsigneeService = orderConsigneeService;
        this.orderConsigneeQueryService = orderConsigneeQueryService;


    }

    /**
     * POST  /order-consignees : Create a new orderConsignee.
     *
     * @param orderConsigneeDTO the orderConsigneeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderConsigneeDTO, or with status 400 (Bad Request) if the orderConsignee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-consignees")
    @Timed
    public ResponseEntity<OrderConsigneeDTO> createOrderConsignee(@Valid @RequestBody OrderConsigneeDTO orderConsigneeDTO) throws URISyntaxException {
        log.debug("REST request to save OrderConsignee : {}", orderConsigneeDTO);
        if (orderConsigneeDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderConsignee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderConsigneeDTO result = orderConsigneeService.save(orderConsigneeDTO);
        return ResponseEntity.created(new URI("/api/order-consignees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-consignees : Updates an existing orderConsignee.
     *
     * @param orderConsigneeDTO the orderConsigneeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderConsigneeDTO,
     * or with status 400 (Bad Request) if the orderConsigneeDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderConsigneeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-consignees")
    @Timed
    public ResponseEntity<OrderConsigneeDTO> updateOrderConsignee(@Valid @RequestBody OrderConsigneeDTO orderConsigneeDTO) throws URISyntaxException {
        log.debug("REST request to update OrderConsignee : {}", orderConsigneeDTO);
        if (orderConsigneeDTO.getId() == null) {
            return createOrderConsignee(orderConsigneeDTO);
        }
        OrderConsigneeDTO result = orderConsigneeService.save(orderConsigneeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderConsigneeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-consignees : get all the orderConsignees.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderConsignees in body
     */
    @GetMapping("/order-consignees")
    @Timed
    public ResponseEntity<List<OrderConsigneeDTO>> getAllOrderConsignees(OrderConsigneeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderConsignees by criteria: {}", criteria);
        Page<OrderConsigneeDTO> page = orderConsigneeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-consignees");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-consignees/:id : get the "id" orderConsignee.
     *
     * @param id the id of the orderConsigneeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderConsigneeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-consignees/{id}")
    @Timed
    public ResponseEntity<OrderConsigneeDTO> getOrderConsignee(@PathVariable Long id) {
        log.debug("REST request to get OrderConsignee : {}", id);
        OrderConsigneeDTO orderConsigneeDTO = orderConsigneeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderConsigneeDTO));
    }

    /**
     * DELETE  /order-consignees/:id : delete the "id" orderConsignee.
     *
     * @param id the id of the orderConsigneeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-consignees/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderConsignee(@PathVariable Long id) {
        log.debug("REST request to delete OrderConsignee : {}", id);
        orderConsigneeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-consignees?query=:query : search for the orderConsignee corresponding
     * to the query.
     *
     * @param query the query of the orderConsignee search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-consignees")
    @Timed
    public ResponseEntity<List<OrderConsigneeDTO>> searchOrderConsignees(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderConsignees for query {}", query);
        Page<OrderConsigneeDTO> page = orderConsigneeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-consignees");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-consignees")
    @Timed
    public ResponseEntity<List<OrderConsigneeDTO>> searchExampleOrderConsignees(OrderConsigneeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderConsignees for searchDTO {}", searchDTO);
        Page<OrderConsigneeDTO> page = orderConsigneeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-consignees");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
