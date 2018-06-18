package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.PositionService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.PositionDTO;
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
 * REST controller for managing Position.
 */
@RestController
@RequestMapping("/api")
public class PositionResource {

    private final Logger log = LoggerFactory.getLogger(PositionResource.class);

    private static final String ENTITY_NAME = "position";

    private final PositionService positionService;

    public PositionResource(PositionService positionService) {
        this.positionService = positionService;
    }

    /**
     * POST  /positions : Create a new position.
     *
     * @param positionDTO the positionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new positionDTO, or with status 400 (Bad Request) if the position has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/positions")
    @Timed
    public ResponseEntity<PositionDTO> createPosition(@Valid @RequestBody PositionDTO positionDTO) throws URISyntaxException {
        log.debug("REST request to save Position : {}", positionDTO);
        if (positionDTO.getId() != null) {
            throw new BadRequestAlertException("A new position cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PositionDTO result = positionService.save(positionDTO);
        return ResponseEntity.created(new URI("/api/positions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /positions : Updates an existing position.
     *
     * @param positionDTO the positionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated positionDTO,
     * or with status 400 (Bad Request) if the positionDTO is not valid,
     * or with status 500 (Internal Server Error) if the positionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/positions")
    @Timed
    public ResponseEntity<PositionDTO> updatePosition(@Valid @RequestBody PositionDTO positionDTO) throws URISyntaxException {
        log.debug("REST request to update Position : {}", positionDTO);
        if (positionDTO.getId() == null) {
            return createPosition(positionDTO);
        }
        PositionDTO result = positionService.save(positionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, positionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /positions : get all the positions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of positions in body
     */
    @GetMapping("/positions")
    @Timed
    public List<PositionDTO> getAllPositions() {
        log.debug("REST request to get all Positions");
        return positionService.findAll();
        }

    /**
     * GET  /positions/:id : get the "id" position.
     *
     * @param id the id of the positionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the positionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/positions/{id}")
    @Timed
    public ResponseEntity<PositionDTO> getPosition(@PathVariable Long id) {
        log.debug("REST request to get Position : {}", id);
        PositionDTO positionDTO = positionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(positionDTO));
    }

    /**
     * DELETE  /positions/:id : delete the "id" position.
     *
     * @param id the id of the positionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/positions/{id}")
    @Timed
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        log.debug("REST request to delete Position : {}", id);
        positionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/positions?query=:query : search for the position corresponding
     * to the query.
     *
     * @param query the query of the position search
     * @return the result of the search
     */
    @GetMapping("/_search/positions")
    @Timed
    public List<PositionDTO> searchPositions(@RequestParam String query) {
        log.debug("REST request to search Positions for query {}", query);
        return positionService.search(query);
    }

}
