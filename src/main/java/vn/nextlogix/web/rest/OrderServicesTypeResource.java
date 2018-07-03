package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderServicesTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderServicesTypeDTO;
import vn.nextlogix.service.dto.OrderServicesTypeSearchDTO;
import vn.nextlogix.service.dto.OrderServicesTypeCriteria;
import vn.nextlogix.service.OrderServicesTypeQueryService;
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
 * REST controller for managing OrderServicesType.
 */
@RestController
@RequestMapping("/api")
public class OrderServicesTypeResource {

    private final Logger log = LoggerFactory.getLogger(OrderServicesTypeResource.class);

    private static final String ENTITY_NAME = "orderServicesType";

    private final OrderServicesTypeService orderServicesTypeService;

    private final OrderServicesTypeQueryService orderServicesTypeQueryService;

    public OrderServicesTypeResource(OrderServicesTypeService orderServicesTypeService, OrderServicesTypeQueryService orderServicesTypeQueryService     ) {
        this.orderServicesTypeService = orderServicesTypeService;
        this.orderServicesTypeQueryService = orderServicesTypeQueryService;


    }

    /**
     * POST  /order-services-types : Create a new orderServicesType.
     *
     * @param orderServicesTypeDTO the orderServicesTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderServicesTypeDTO, or with status 400 (Bad Request) if the orderServicesType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-services-types")
    @Timed
    public ResponseEntity<OrderServicesTypeDTO> createOrderServicesType(@Valid @RequestBody OrderServicesTypeDTO orderServicesTypeDTO) throws URISyntaxException {
        log.debug("REST request to save OrderServicesType : {}", orderServicesTypeDTO);
        if (orderServicesTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderServicesType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderServicesTypeDTO result = orderServicesTypeService.save(orderServicesTypeDTO);
        return ResponseEntity.created(new URI("/api/order-services-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-services-types : Updates an existing orderServicesType.
     *
     * @param orderServicesTypeDTO the orderServicesTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderServicesTypeDTO,
     * or with status 400 (Bad Request) if the orderServicesTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderServicesTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-services-types")
    @Timed
    public ResponseEntity<OrderServicesTypeDTO> updateOrderServicesType(@Valid @RequestBody OrderServicesTypeDTO orderServicesTypeDTO) throws URISyntaxException {
        log.debug("REST request to update OrderServicesType : {}", orderServicesTypeDTO);
        if (orderServicesTypeDTO.getId() == null) {
            return createOrderServicesType(orderServicesTypeDTO);
        }
        OrderServicesTypeDTO result = orderServicesTypeService.save(orderServicesTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderServicesTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-services-types : get all the orderServicesTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderServicesTypes in body
     */
    @GetMapping("/order-services-types")
    @Timed
    public ResponseEntity<List<OrderServicesTypeDTO>> getAllOrderServicesTypes(OrderServicesTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderServicesTypes by criteria: {}", criteria);
        Page<OrderServicesTypeDTO> page = orderServicesTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-services-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-services-types/:id : get the "id" orderServicesType.
     *
     * @param id the id of the orderServicesTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderServicesTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-services-types/{id}")
    @Timed
    public ResponseEntity<OrderServicesTypeDTO> getOrderServicesType(@PathVariable Long id) {
        log.debug("REST request to get OrderServicesType : {}", id);
        OrderServicesTypeDTO orderServicesTypeDTO = orderServicesTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderServicesTypeDTO));
    }

    /**
     * DELETE  /order-services-types/:id : delete the "id" orderServicesType.
     *
     * @param id the id of the orderServicesTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-services-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderServicesType(@PathVariable Long id) {
        log.debug("REST request to delete OrderServicesType : {}", id);
        orderServicesTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-services-types?query=:query : search for the orderServicesType corresponding
     * to the query.
     *
     * @param query the query of the orderServicesType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-services-types")
    @Timed
    public ResponseEntity<List<OrderServicesTypeDTO>> searchOrderServicesTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderServicesTypes for query {}", query);
        Page<OrderServicesTypeDTO> page = orderServicesTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-services-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-services-types")
    @Timed
    public ResponseEntity<List<OrderServicesTypeDTO>> searchExampleOrderServicesTypes(OrderServicesTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderServicesTypes for searchDTO {}", searchDTO);
        Page<OrderServicesTypeDTO> page = orderServicesTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-services-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
