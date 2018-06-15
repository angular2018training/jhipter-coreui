package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.service.PostOfficeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.service.dto.PostOfficeDTO;
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
 * REST controller for managing PostOffice.
 */
@RestController
@RequestMapping("/api")
public class PostOfficeResource {

    private final Logger log = LoggerFactory.getLogger(PostOfficeResource.class);

    private static final String ENTITY_NAME = "postOffice";

    private final PostOfficeService postOfficeService;

    public PostOfficeResource(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    /**
     * POST  /post-offices : Create a new postOffice.
     *
     * @param postOfficeDTO the postOfficeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new postOfficeDTO, or with status 400 (Bad Request) if the postOffice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/post-offices")
    @Timed
    public ResponseEntity<PostOfficeDTO> createPostOffice(@Valid @RequestBody PostOfficeDTO postOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save PostOffice : {}", postOfficeDTO);
        if (postOfficeDTO.getId() != null) {
            throw new BadRequestAlertException("A new postOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PostOfficeDTO result = postOfficeService.save(postOfficeDTO);
        return ResponseEntity.created(new URI("/api/post-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /post-offices : Updates an existing postOffice.
     *
     * @param postOfficeDTO the postOfficeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated postOfficeDTO,
     * or with status 400 (Bad Request) if the postOfficeDTO is not valid,
     * or with status 500 (Internal Server Error) if the postOfficeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/post-offices")
    @Timed
    public ResponseEntity<PostOfficeDTO> updatePostOffice(@Valid @RequestBody PostOfficeDTO postOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update PostOffice : {}", postOfficeDTO);
        if (postOfficeDTO.getId() == null) {
            return createPostOffice(postOfficeDTO);
        }
        PostOfficeDTO result = postOfficeService.save(postOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, postOfficeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /post-offices : get all the postOffices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of postOffices in body
     */
    @GetMapping("/post-offices")
    @Timed
    public List<PostOfficeDTO> getAllPostOffices() {
        log.debug("REST request to get all PostOffices");
        return postOfficeService.findAll();
        }

    /**
     * GET  /post-offices/:id : get the "id" postOffice.
     *
     * @param id the id of the postOfficeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the postOfficeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/post-offices/{id}")
    @Timed
    public ResponseEntity<PostOfficeDTO> getPostOffice(@PathVariable Long id) {
        log.debug("REST request to get PostOffice : {}", id);
        PostOfficeDTO postOfficeDTO = postOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(postOfficeDTO));
    }

    /**
     * DELETE  /post-offices/:id : delete the "id" postOffice.
     *
     * @param id the id of the postOfficeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/post-offices/{id}")
    @Timed
    public ResponseEntity<Void> deletePostOffice(@PathVariable Long id) {
        log.debug("REST request to delete PostOffice : {}", id);
        postOfficeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/post-offices?query=:query : search for the postOffice corresponding
     * to the query.
     *
     * @param query the query of the postOffice search
     * @return the result of the search
     */
    @GetMapping("/_search/post-offices")
    @Timed
    public List<PostOfficeDTO> searchPostOffices(@RequestParam String query) {
        log.debug("REST request to search PostOffices for query {}", query);
        return postOfficeService.search(query);
    }

}
