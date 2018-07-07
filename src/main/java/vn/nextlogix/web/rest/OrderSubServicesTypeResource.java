package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.OrderSubServicesTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.OrderSubServicesTypeDTO;
import vn.nextlogix.service.dto.OrderSubServicesTypeSearchDTO;
import vn.nextlogix.service.dto.OrderSubServicesTypeCriteria;
import vn.nextlogix.service.OrderSubServicesTypeQueryService;
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
 * REST controller for managing OrderSubServicesType.
 */
@RestController
@RequestMapping("/api")
public class OrderSubServicesTypeResource {

    private final Logger log = LoggerFactory.getLogger(OrderSubServicesTypeResource.class);

    private static final String ENTITY_NAME = "orderSubServicesType";

    private final OrderSubServicesTypeService orderSubServicesTypeService;

    private final OrderSubServicesTypeQueryService orderSubServicesTypeQueryService;

    public OrderSubServicesTypeResource(OrderSubServicesTypeService orderSubServicesTypeService, OrderSubServicesTypeQueryService orderSubServicesTypeQueryService     ) {
        this.orderSubServicesTypeService = orderSubServicesTypeService;
        this.orderSubServicesTypeQueryService = orderSubServicesTypeQueryService;


    }

    /**
     * POST  /order-sub-services-types : Create a new orderSubServicesType.
     *
     * @param orderSubServicesTypeDTO the orderSubServicesTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderSubServicesTypeDTO, or with status 400 (Bad Request) if the orderSubServicesType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-sub-services-types")
    @Timed
    public ResponseEntity<OrderSubServicesTypeDTO> createOrderSubServicesType(@Valid @RequestBody OrderSubServicesTypeDTO orderSubServicesTypeDTO) throws URISyntaxException {
        log.debug("REST request to save OrderSubServicesType : {}", orderSubServicesTypeDTO);
        if (orderSubServicesTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderSubServicesType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderSubServicesTypeDTO result = orderSubServicesTypeService.save(orderSubServicesTypeDTO);
        return ResponseEntity.created(new URI("/api/order-sub-services-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-sub-services-types : Updates an existing orderSubServicesType.
     *
     * @param orderSubServicesTypeDTO the orderSubServicesTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderSubServicesTypeDTO,
     * or with status 400 (Bad Request) if the orderSubServicesTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderSubServicesTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-sub-services-types")
    @Timed
    public ResponseEntity<OrderSubServicesTypeDTO> updateOrderSubServicesType(@Valid @RequestBody OrderSubServicesTypeDTO orderSubServicesTypeDTO) throws URISyntaxException {
        log.debug("REST request to update OrderSubServicesType : {}", orderSubServicesTypeDTO);
        if (orderSubServicesTypeDTO.getId() == null) {
            return createOrderSubServicesType(orderSubServicesTypeDTO);
        }
        OrderSubServicesTypeDTO result = orderSubServicesTypeService.save(orderSubServicesTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderSubServicesTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-sub-services-types : get all the orderSubServicesTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderSubServicesTypes in body
     */
    @GetMapping("/order-sub-services-types")
    @Timed
    public ResponseEntity<List<OrderSubServicesTypeDTO>> getAllOrderSubServicesTypes(OrderSubServicesTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderSubServicesTypes by criteria: {}", criteria);
        Page<OrderSubServicesTypeDTO> page = orderSubServicesTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-sub-services-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-sub-services-types/:id : get the "id" orderSubServicesType.
     *
     * @param id the id of the orderSubServicesTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderSubServicesTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-sub-services-types/{id}")
    @Timed
    public ResponseEntity<OrderSubServicesTypeDTO> getOrderSubServicesType(@PathVariable Long id) {
        log.debug("REST request to get OrderSubServicesType : {}", id);
        OrderSubServicesTypeDTO orderSubServicesTypeDTO = orderSubServicesTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderSubServicesTypeDTO));
    }

    /**
     * DELETE  /order-sub-services-types/:id : delete the "id" orderSubServicesType.
     *
     * @param id the id of the orderSubServicesTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-sub-services-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderSubServicesType(@PathVariable Long id) {
        log.debug("REST request to delete OrderSubServicesType : {}", id);
        orderSubServicesTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-sub-services-types?query=:query : search for the orderSubServicesType corresponding
     * to the query.
     *
     * @param query the query of the orderSubServicesType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-sub-services-types")
    @Timed
    public ResponseEntity<List<OrderSubServicesTypeDTO>> searchOrderSubServicesTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderSubServicesTypes for query {}", query);
        Page<OrderSubServicesTypeDTO> page = orderSubServicesTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-sub-services-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/order-sub-services-types")
    @Timed
    public ResponseEntity<List<OrderSubServicesTypeDTO>> searchExampleOrderSubServicesTypes(OrderSubServicesTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of OrderSubServicesTypes for searchDTO {}", searchDTO);
        Page<OrderSubServicesTypeDTO> page = orderSubServicesTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/order-sub-services-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
