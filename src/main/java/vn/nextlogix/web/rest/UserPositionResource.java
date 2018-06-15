package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.UserPositionService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.UserPositionDTO;
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
 * REST controller for managing UserPosition.
 */
@RestController
@RequestMapping("/api")
public class UserPositionResource {

    private final Logger log = LoggerFactory.getLogger(UserPositionResource.class);

    private static final String ENTITY_NAME = "userPosition";

    private final UserPositionService userPositionService;

    public UserPositionResource(UserPositionService userPositionService) {
        this.userPositionService = userPositionService;
    }

    /**
     * POST  /user-positions : Create a new userPosition.
     *
     * @param userPositionDTO the userPositionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userPositionDTO, or with status 400 (Bad Request) if the userPosition has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-positions")
    @Timed
    public ResponseEntity<UserPositionDTO> createUserPosition(@Valid @RequestBody UserPositionDTO userPositionDTO) throws URISyntaxException {
        log.debug("REST request to save UserPosition : {}", userPositionDTO);
        if (userPositionDTO.getId() != null) {
            throw new BadRequestAlertException("A new userPosition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPositionDTO result = userPositionService.save(userPositionDTO);
        return ResponseEntity.created(new URI("/api/user-positions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-positions : Updates an existing userPosition.
     *
     * @param userPositionDTO the userPositionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userPositionDTO,
     * or with status 400 (Bad Request) if the userPositionDTO is not valid,
     * or with status 500 (Internal Server Error) if the userPositionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-positions")
    @Timed
    public ResponseEntity<UserPositionDTO> updateUserPosition(@Valid @RequestBody UserPositionDTO userPositionDTO) throws URISyntaxException {
        log.debug("REST request to update UserPosition : {}", userPositionDTO);
        if (userPositionDTO.getId() == null) {
            return createUserPosition(userPositionDTO);
        }
        UserPositionDTO result = userPositionService.save(userPositionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userPositionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-positions : get all the userPositions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userPositions in body
     */
    @GetMapping("/user-positions")
    @Timed
    public List<UserPositionDTO> getAllUserPositions() {
        log.debug("REST request to get all UserPositions");
        return userPositionService.findAll();
        }

    /**
     * GET  /user-positions/:id : get the "id" userPosition.
     *
     * @param id the id of the userPositionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userPositionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-positions/{id}")
    @Timed
    public ResponseEntity<UserPositionDTO> getUserPosition(@PathVariable Long id) {
        log.debug("REST request to get UserPosition : {}", id);
        UserPositionDTO userPositionDTO = userPositionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userPositionDTO));
    }

    /**
     * DELETE  /user-positions/:id : delete the "id" userPosition.
     *
     * @param id the id of the userPositionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-positions/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserPosition(@PathVariable Long id) {
        log.debug("REST request to delete UserPosition : {}", id);
        userPositionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/user-positions?query=:query : search for the userPosition corresponding
     * to the query.
     *
     * @param query the query of the userPosition search
     * @return the result of the search
     */
    @GetMapping("/_search/user-positions")
    @Timed
    public List<UserPositionDTO> searchUserPositions(@RequestParam String query) {
        log.debug("REST request to search UserPositions for query {}", query);
        return userPositionService.search(query);
    }

}
