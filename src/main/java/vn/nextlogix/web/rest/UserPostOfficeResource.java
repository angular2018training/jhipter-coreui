package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.UserPostOfficeService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.UserPostOfficeDTO;
import vn.nextlogix.service.dto.UserPostOfficeSearchDTO;
import vn.nextlogix.service.dto.UserPostOfficeCriteria;
import vn.nextlogix.service.UserPostOfficeQueryService;
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
 * REST controller for managing UserPostOffice.
 */
@RestController
@RequestMapping("/api")
public class UserPostOfficeResource {

    private final Logger log = LoggerFactory.getLogger(UserPostOfficeResource.class);

    private static final String ENTITY_NAME = "userPostOffice";

    private final UserPostOfficeService userPostOfficeService;

    private final UserPostOfficeQueryService userPostOfficeQueryService;

    public UserPostOfficeResource(UserPostOfficeService userPostOfficeService, UserPostOfficeQueryService userPostOfficeQueryService     ) {
        this.userPostOfficeService = userPostOfficeService;
        this.userPostOfficeQueryService = userPostOfficeQueryService;


    }

    /**
     * POST  /user-post-offices : Create a new userPostOffice.
     *
     * @param userPostOfficeDTO the userPostOfficeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userPostOfficeDTO, or with status 400 (Bad Request) if the userPostOffice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-post-offices")
    @Timed
    public ResponseEntity<UserPostOfficeDTO> createUserPostOffice(@Valid @RequestBody UserPostOfficeDTO userPostOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save UserPostOffice : {}", userPostOfficeDTO);
        if (userPostOfficeDTO.getId() != null) {
            throw new BadRequestAlertException("A new userPostOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPostOfficeDTO result = userPostOfficeService.save(userPostOfficeDTO);
        return ResponseEntity.created(new URI("/api/user-post-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-post-offices : Updates an existing userPostOffice.
     *
     * @param userPostOfficeDTO the userPostOfficeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userPostOfficeDTO,
     * or with status 400 (Bad Request) if the userPostOfficeDTO is not valid,
     * or with status 500 (Internal Server Error) if the userPostOfficeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-post-offices")
    @Timed
    public ResponseEntity<UserPostOfficeDTO> updateUserPostOffice(@Valid @RequestBody UserPostOfficeDTO userPostOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update UserPostOffice : {}", userPostOfficeDTO);
        if (userPostOfficeDTO.getId() == null) {
            return createUserPostOffice(userPostOfficeDTO);
        }
        UserPostOfficeDTO result = userPostOfficeService.save(userPostOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userPostOfficeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-post-offices : get all the userPostOffices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of userPostOffices in body
     */
    @GetMapping("/user-post-offices")
    @Timed
    public ResponseEntity<List<UserPostOfficeDTO>> getAllUserPostOffices(UserPostOfficeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UserPostOffices by criteria: {}", criteria);
        Page<UserPostOfficeDTO> page = userPostOfficeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-post-offices/:id : get the "id" userPostOffice.
     *
     * @param id the id of the userPostOfficeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userPostOfficeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-post-offices/{id}")
    @Timed
    public ResponseEntity<UserPostOfficeDTO> getUserPostOffice(@PathVariable Long id) {
        log.debug("REST request to get UserPostOffice : {}", id);
        UserPostOfficeDTO userPostOfficeDTO = userPostOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userPostOfficeDTO));
    }

    /**
     * DELETE  /user-post-offices/:id : delete the "id" userPostOffice.
     *
     * @param id the id of the userPostOfficeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-post-offices/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserPostOffice(@PathVariable Long id) {
        log.debug("REST request to delete UserPostOffice : {}", id);
        userPostOfficeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/user-post-offices?query=:query : search for the userPostOffice corresponding
     * to the query.
     *
     * @param query the query of the userPostOffice search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/user-post-offices")
    @Timed
    public ResponseEntity<List<UserPostOfficeDTO>> searchUserPostOffices(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UserPostOffices for query {}", query);
        Page<UserPostOfficeDTO> page = userPostOfficeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/user-post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/user-post-offices")
    @Timed
    public ResponseEntity<List<UserPostOfficeDTO>> searchExampleUserPostOffices(UserPostOfficeSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of UserPostOffices for searchDTO {}", searchDTO);
        Page<UserPostOfficeDTO> page = userPostOfficeService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/user-post-offices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
