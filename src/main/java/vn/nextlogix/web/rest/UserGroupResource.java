package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.UserGroupService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.UserGroupDTO;
import vn.nextlogix.service.dto.UserGroupSearchDTO;
import vn.nextlogix.service.dto.UserGroupCriteria;
import vn.nextlogix.service.UserGroupQueryService;
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
 * REST controller for managing UserGroup.
 */
@RestController
@RequestMapping("/api")
public class UserGroupResource {

    private final Logger log = LoggerFactory.getLogger(UserGroupResource.class);

    private static final String ENTITY_NAME = "userGroup";

    private final UserGroupService userGroupService;

    private final UserGroupQueryService userGroupQueryService;

    public UserGroupResource(UserGroupService userGroupService, UserGroupQueryService userGroupQueryService     ) {
        this.userGroupService = userGroupService;
        this.userGroupQueryService = userGroupQueryService;


    }

    /**
     * POST  /user-groups : Create a new userGroup.
     *
     * @param userGroupDTO the userGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userGroupDTO, or with status 400 (Bad Request) if the userGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-groups")
    @Timed
    public ResponseEntity<UserGroupDTO> createUserGroup(@Valid @RequestBody UserGroupDTO userGroupDTO) throws URISyntaxException {
        log.debug("REST request to save UserGroup : {}", userGroupDTO);
        if (userGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new userGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserGroupDTO result = userGroupService.save(userGroupDTO);
        return ResponseEntity.created(new URI("/api/user-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-groups : Updates an existing userGroup.
     *
     * @param userGroupDTO the userGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userGroupDTO,
     * or with status 400 (Bad Request) if the userGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the userGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-groups")
    @Timed
    public ResponseEntity<UserGroupDTO> updateUserGroup(@Valid @RequestBody UserGroupDTO userGroupDTO) throws URISyntaxException {
        log.debug("REST request to update UserGroup : {}", userGroupDTO);
        if (userGroupDTO.getId() == null) {
            return createUserGroup(userGroupDTO);
        }
        UserGroupDTO result = userGroupService.save(userGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-groups : get all the userGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of userGroups in body
     */
    @GetMapping("/user-groups")
    @Timed
    public ResponseEntity<List<UserGroupDTO>> getAllUserGroups(UserGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UserGroups by criteria: {}", criteria);
        Page<UserGroupDTO> page = userGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-groups/:id : get the "id" userGroup.
     *
     * @param id the id of the userGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-groups/{id}")
    @Timed
    public ResponseEntity<UserGroupDTO> getUserGroup(@PathVariable Long id) {
        log.debug("REST request to get UserGroup : {}", id);
        UserGroupDTO userGroupDTO = userGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userGroupDTO));
    }

    /**
     * DELETE  /user-groups/:id : delete the "id" userGroup.
     *
     * @param id the id of the userGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserGroup(@PathVariable Long id) {
        log.debug("REST request to delete UserGroup : {}", id);
        userGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/user-groups?query=:query : search for the userGroup corresponding
     * to the query.
     *
     * @param query the query of the userGroup search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/user-groups")
    @Timed
    public ResponseEntity<List<UserGroupDTO>> searchUserGroups(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UserGroups for query {}", query);
        Page<UserGroupDTO> page = userGroupService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/user-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/user-groups")
    @Timed
    public ResponseEntity<List<UserGroupDTO>> searchExampleUserGroups(UserGroupSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of UserGroups for searchDTO {}", searchDTO);
        Page<UserGroupDTO> page = userGroupService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/user-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
