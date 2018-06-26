package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.WarehouseService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.WarehouseDTO;
import vn.nextlogix.service.dto.WarehouseSearchDTO;
import vn.nextlogix.service.dto.WarehouseCriteria;
import vn.nextlogix.service.WarehouseQueryService;
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
 * REST controller for managing Warehouse.
 */
@RestController
@RequestMapping("/api")
public class WarehouseResource {

    private final Logger log = LoggerFactory.getLogger(WarehouseResource.class);

    private static final String ENTITY_NAME = "warehouse";

    private final WarehouseService warehouseService;

    private final WarehouseQueryService warehouseQueryService;

    public WarehouseResource(WarehouseService warehouseService, WarehouseQueryService warehouseQueryService     ) {
        this.warehouseService = warehouseService;
        this.warehouseQueryService = warehouseQueryService;


    }

    /**
     * POST  /warehouses : Create a new warehouse.
     *
     * @param warehouseDTO the warehouseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new warehouseDTO, or with status 400 (Bad Request) if the warehouse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/warehouses")
    @Timed
    public ResponseEntity<WarehouseDTO> createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) throws URISyntaxException {
        log.debug("REST request to save Warehouse : {}", warehouseDTO);
        if (warehouseDTO.getId() != null) {
            throw new BadRequestAlertException("A new warehouse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WarehouseDTO result = warehouseService.save(warehouseDTO);
        return ResponseEntity.created(new URI("/api/warehouses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /warehouses : Updates an existing warehouse.
     *
     * @param warehouseDTO the warehouseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated warehouseDTO,
     * or with status 400 (Bad Request) if the warehouseDTO is not valid,
     * or with status 500 (Internal Server Error) if the warehouseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/warehouses")
    @Timed
    public ResponseEntity<WarehouseDTO> updateWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) throws URISyntaxException {
        log.debug("REST request to update Warehouse : {}", warehouseDTO);
        if (warehouseDTO.getId() == null) {
            return createWarehouse(warehouseDTO);
        }
        WarehouseDTO result = warehouseService.save(warehouseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, warehouseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /warehouses : get all the warehouses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of warehouses in body
     */
    @GetMapping("/warehouses")
    @Timed
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouses(WarehouseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Warehouses by criteria: {}", criteria);
        Page<WarehouseDTO> page = warehouseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/warehouses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /warehouses/:id : get the "id" warehouse.
     *
     * @param id the id of the warehouseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the warehouseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/warehouses/{id}")
    @Timed
    public ResponseEntity<WarehouseDTO> getWarehouse(@PathVariable Long id) {
        log.debug("REST request to get Warehouse : {}", id);
        WarehouseDTO warehouseDTO = warehouseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(warehouseDTO));
    }

    /**
     * DELETE  /warehouses/:id : delete the "id" warehouse.
     *
     * @param id the id of the warehouseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/warehouses/{id}")
    @Timed
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        log.debug("REST request to delete Warehouse : {}", id);
        warehouseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/warehouses?query=:query : search for the warehouse corresponding
     * to the query.
     *
     * @param query the query of the warehouse search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/warehouses")
    @Timed
    public ResponseEntity<List<WarehouseDTO>> searchWarehouses(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Warehouses for query {}", query);
        Page<WarehouseDTO> page = warehouseService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/warehouses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/warehouses")
    @Timed
    public ResponseEntity<List<WarehouseDTO>> searchExampleWarehouses(WarehouseSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of Warehouses for searchDTO {}", searchDTO);
        Page<WarehouseDTO> page = warehouseService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/warehouses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
