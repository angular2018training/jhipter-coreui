package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.DistrictTypeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.DistrictTypeDTO;
import vn.nextlogix.service.dto.DistrictTypeSearchDTO;
import vn.nextlogix.service.dto.DistrictTypeCriteria;
import vn.nextlogix.service.DistrictTypeQueryService;
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
 * REST controller for managing DistrictType.
 */
@RestController
@RequestMapping("/api")
public class DistrictTypeResource {

    private final Logger log = LoggerFactory.getLogger(DistrictTypeResource.class);

    private static final String ENTITY_NAME = "districtType";

    private final DistrictTypeService districtTypeService;

    private final DistrictTypeQueryService districtTypeQueryService;

    public DistrictTypeResource(DistrictTypeService districtTypeService, DistrictTypeQueryService districtTypeQueryService     ) {
        this.districtTypeService = districtTypeService;
        this.districtTypeQueryService = districtTypeQueryService;


    }

    /**
     * POST  /district-types : Create a new districtType.
     *
     * @param districtTypeDTO the districtTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new districtTypeDTO, or with status 400 (Bad Request) if the districtType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/district-types")
    @Timed
    public ResponseEntity<DistrictTypeDTO> createDistrictType(@Valid @RequestBody DistrictTypeDTO districtTypeDTO) throws URISyntaxException {
        log.debug("REST request to save DistrictType : {}", districtTypeDTO);
        if (districtTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new districtType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistrictTypeDTO result = districtTypeService.save(districtTypeDTO);
        return ResponseEntity.created(new URI("/api/district-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /district-types : Updates an existing districtType.
     *
     * @param districtTypeDTO the districtTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated districtTypeDTO,
     * or with status 400 (Bad Request) if the districtTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the districtTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/district-types")
    @Timed
    public ResponseEntity<DistrictTypeDTO> updateDistrictType(@Valid @RequestBody DistrictTypeDTO districtTypeDTO) throws URISyntaxException {
        log.debug("REST request to update DistrictType : {}", districtTypeDTO);
        if (districtTypeDTO.getId() == null) {
            return createDistrictType(districtTypeDTO);
        }
        DistrictTypeDTO result = districtTypeService.save(districtTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, districtTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /district-types : get all the districtTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of districtTypes in body
     */
    @GetMapping("/district-types")
    @Timed
    public ResponseEntity<List<DistrictTypeDTO>> getAllDistrictTypes(DistrictTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DistrictTypes by criteria: {}", criteria);
        Page<DistrictTypeDTO> page = districtTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/district-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /district-types/:id : get the "id" districtType.
     *
     * @param id the id of the districtTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the districtTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/district-types/{id}")
    @Timed
    public ResponseEntity<DistrictTypeDTO> getDistrictType(@PathVariable Long id) {
        log.debug("REST request to get DistrictType : {}", id);
        DistrictTypeDTO districtTypeDTO = districtTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(districtTypeDTO));
    }

    /**
     * DELETE  /district-types/:id : delete the "id" districtType.
     *
     * @param id the id of the districtTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/district-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteDistrictType(@PathVariable Long id) {
        log.debug("REST request to delete DistrictType : {}", id);
        districtTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/district-types?query=:query : search for the districtType corresponding
     * to the query.
     *
     * @param query the query of the districtType search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/district-types")
    @Timed
    public ResponseEntity<List<DistrictTypeDTO>> searchDistrictTypes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of DistrictTypes for query {}", query);
        Page<DistrictTypeDTO> page = districtTypeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/district-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/district-types")
    @Timed
    public ResponseEntity<List<DistrictTypeDTO>> searchExampleDistrictTypes(DistrictTypeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of DistrictTypes for searchDTO {}", searchDTO);
        Page<DistrictTypeDTO> page = districtTypeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/district-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
