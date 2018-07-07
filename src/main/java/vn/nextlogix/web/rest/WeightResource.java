package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.WeightService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.WeightDTO;
import vn.nextlogix.service.dto.WeightSearchDTO;
import vn.nextlogix.service.dto.WeightCriteria;
import vn.nextlogix.service.WeightQueryService;
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
 * REST controller for managing Weight.
 */
@RestController
@RequestMapping("/api")
public class WeightResource {

    private final Logger log = LoggerFactory.getLogger(WeightResource.class);

    private static final String ENTITY_NAME = "weight";

    private final WeightService weightService;

    private final WeightQueryService weightQueryService;

    public WeightResource(WeightService weightService, WeightQueryService weightQueryService     ) {
        this.weightService = weightService;
        this.weightQueryService = weightQueryService;


    }

    /**
     * POST  /weights : Create a new weight.
     *
     * @param weightDTO the weightDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new weightDTO, or with status 400 (Bad Request) if the weight has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/weights")
    @Timed
    public ResponseEntity<WeightDTO> createWeight(@Valid @RequestBody WeightDTO weightDTO) throws URISyntaxException {
        log.debug("REST request to save Weight : {}", weightDTO);
        if (weightDTO.getId() != null) {
            throw new BadRequestAlertException("A new weight cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeightDTO result = weightService.save(weightDTO);
        return ResponseEntity.created(new URI("/api/weights/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /weights : Updates an existing weight.
     *
     * @param weightDTO the weightDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated weightDTO,
     * or with status 400 (Bad Request) if the weightDTO is not valid,
     * or with status 500 (Internal Server Error) if the weightDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/weights")
    @Timed
    public ResponseEntity<WeightDTO> updateWeight(@Valid @RequestBody WeightDTO weightDTO) throws URISyntaxException {
        log.debug("REST request to update Weight : {}", weightDTO);
        if (weightDTO.getId() == null) {
            return createWeight(weightDTO);
        }
        WeightDTO result = weightService.save(weightDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, weightDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /weights : get all the weights.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of weights in body
     */
    @GetMapping("/weights")
    @Timed
    public ResponseEntity<List<WeightDTO>> getAllWeights(WeightCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Weights by criteria: {}", criteria);
        Page<WeightDTO> page = weightQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/weights");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /weights/:id : get the "id" weight.
     *
     * @param id the id of the weightDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the weightDTO, or with status 404 (Not Found)
     */
    @GetMapping("/weights/{id}")
    @Timed
    public ResponseEntity<WeightDTO> getWeight(@PathVariable Long id) {
        log.debug("REST request to get Weight : {}", id);
        WeightDTO weightDTO = weightService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(weightDTO));
    }

    /**
     * DELETE  /weights/:id : delete the "id" weight.
     *
     * @param id the id of the weightDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/weights/{id}")
    @Timed
    public ResponseEntity<Void> deleteWeight(@PathVariable Long id) {
        log.debug("REST request to delete Weight : {}", id);
        weightService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/weights?query=:query : search for the weight corresponding
     * to the query.
     *
     * @param query the query of the weight search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/weights")
    @Timed
    public ResponseEntity<List<WeightDTO>> searchWeights(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Weights for query {}", query);
        Page<WeightDTO> page = weightService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/weights");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/weights")
    @Timed
    public ResponseEntity<List<WeightDTO>> searchExampleWeights(WeightSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of Weights for searchDTO {}", searchDTO);
        Page<WeightDTO> page = weightService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/weights");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
