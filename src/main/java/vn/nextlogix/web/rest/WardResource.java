package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.WardService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.WardDTO;
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
 * REST controller for managing Ward.
 */
@RestController
@RequestMapping("/api")
public class WardResource {

    private final Logger log = LoggerFactory.getLogger(WardResource.class);

    private static final String ENTITY_NAME = "ward";

    private final WardService wardService;

    public WardResource(WardService wardService) {
        this.wardService = wardService;
    }

    /**
     * POST  /wards : Create a new ward.
     *
     * @param wardDTO the wardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wardDTO, or with status 400 (Bad Request) if the ward has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wards")
    @Timed
    public ResponseEntity<WardDTO> createWard(@Valid @RequestBody WardDTO wardDTO) throws URISyntaxException {
        log.debug("REST request to save Ward : {}", wardDTO);
        if (wardDTO.getId() != null) {
            throw new BadRequestAlertException("A new ward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WardDTO result = wardService.save(wardDTO);
        return ResponseEntity.created(new URI("/api/wards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wards : Updates an existing ward.
     *
     * @param wardDTO the wardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wardDTO,
     * or with status 400 (Bad Request) if the wardDTO is not valid,
     * or with status 500 (Internal Server Error) if the wardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wards")
    @Timed
    public ResponseEntity<WardDTO> updateWard(@Valid @RequestBody WardDTO wardDTO) throws URISyntaxException {
        log.debug("REST request to update Ward : {}", wardDTO);
        if (wardDTO.getId() == null) {
            return createWard(wardDTO);
        }
        WardDTO result = wardService.save(wardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wards : get all the wards.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wards in body
     */
    @GetMapping("/wards")
    @Timed
    public List<WardDTO> getAllWards() {
        log.debug("REST request to get all Wards");
        return wardService.findAll();
        }

    /**
     * GET  /wards/:id : get the "id" ward.
     *
     * @param id the id of the wardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wards/{id}")
    @Timed
    public ResponseEntity<WardDTO> getWard(@PathVariable Long id) {
        log.debug("REST request to get Ward : {}", id);
        WardDTO wardDTO = wardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wardDTO));
    }

    /**
     * DELETE  /wards/:id : delete the "id" ward.
     *
     * @param id the id of the wardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wards/{id}")
    @Timed
    public ResponseEntity<Void> deleteWard(@PathVariable Long id) {
        log.debug("REST request to delete Ward : {}", id);
        wardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/wards?query=:query : search for the ward corresponding
     * to the query.
     *
     * @param query the query of the ward search
     * @return the result of the search
     */
    @GetMapping("/_search/wards")
    @Timed
    public List<WardDTO> searchWards(@RequestParam String query) {
        log.debug("REST request to search Wards for query {}", query);
        return wardService.search(query);
    }

}
