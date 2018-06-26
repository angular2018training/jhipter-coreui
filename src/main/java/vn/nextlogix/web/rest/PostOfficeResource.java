package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.PostOfficeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.PostOfficeDTO;
import vn.nextlogix.service.dto.PostOfficeSearchDTO;
import vn.nextlogix.service.dto.PostOfficeCriteria;
import vn.nextlogix.service.PostOfficeQueryService;
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
 * REST controller for managing PostOffice.
 */
@RestController
@RequestMapping("/api")
public class PostOfficeResource {

    private final Logger log = LoggerFactory.getLogger(PostOfficeResource.class);

    private static final String ENTITY_NAME = "postOffice";

    private final PostOfficeService postOfficeService;

    private final PostOfficeQueryService postOfficeQueryService;

    public PostOfficeResource(PostOfficeService postOfficeService, PostOfficeQueryService postOfficeQueryService     ) {
        this.postOfficeService = postOfficeService;
        this.postOfficeQueryService = postOfficeQueryService;


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
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of postOffices in body
     */
    @GetMapping("/post-offices")
    @Timed
    public ResponseEntity<List<PostOfficeDTO>> getAllPostOffices(PostOfficeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PostOffices by criteria: {}", criteria);
        Page<PostOfficeDTO> page = postOfficeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/post-offices")
    @Timed
    public ResponseEntity<List<PostOfficeDTO>> searchPostOffices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PostOffices for query {}", query);
        Page<PostOfficeDTO> page = postOfficeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/post-offices")
    @Timed
    public ResponseEntity<List<PostOfficeDTO>> searchExamplePostOffices(PostOfficeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of PostOffices for searchDTO {}", searchDTO);
        Page<PostOfficeDTO> page = postOfficeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
