package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.UserExtraInfoService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.UserExtraInfoDTO;
import vn.nextlogix.service.dto.UserExtraInfoSearchDTO;
import vn.nextlogix.service.dto.UserExtraInfoCriteria;
import vn.nextlogix.service.UserExtraInfoQueryService;
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
 * REST controller for managing UserExtraInfo.
 */
@RestController
@RequestMapping("/api")
public class UserExtraInfoResource {

    private final Logger log = LoggerFactory.getLogger(UserExtraInfoResource.class);

    private static final String ENTITY_NAME = "userExtraInfo";

    private final UserExtraInfoService userExtraInfoService;

    private final UserExtraInfoQueryService userExtraInfoQueryService;

    public UserExtraInfoResource(UserExtraInfoService userExtraInfoService, UserExtraInfoQueryService userExtraInfoQueryService     ) {
        this.userExtraInfoService = userExtraInfoService;
        this.userExtraInfoQueryService = userExtraInfoQueryService;


    }

    /**
     * POST  /user-extra-infos : Create a new userExtraInfo.
     *
     * @param userExtraInfoDTO the userExtraInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userExtraInfoDTO, or with status 400 (Bad Request) if the userExtraInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-extra-infos")
    @Timed
    public ResponseEntity<UserExtraInfoDTO> createUserExtraInfo(@Valid @RequestBody UserExtraInfoDTO userExtraInfoDTO) throws URISyntaxException {
        log.debug("REST request to save UserExtraInfo : {}", userExtraInfoDTO);
        if (userExtraInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new userExtraInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserExtraInfoDTO result = userExtraInfoService.save(userExtraInfoDTO);
        return ResponseEntity.created(new URI("/api/user-extra-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-extra-infos : Updates an existing userExtraInfo.
     *
     * @param userExtraInfoDTO the userExtraInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userExtraInfoDTO,
     * or with status 400 (Bad Request) if the userExtraInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the userExtraInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-extra-infos")
    @Timed
    public ResponseEntity<UserExtraInfoDTO> updateUserExtraInfo(@Valid @RequestBody UserExtraInfoDTO userExtraInfoDTO) throws URISyntaxException {
        log.debug("REST request to update UserExtraInfo : {}", userExtraInfoDTO);
        if (userExtraInfoDTO.getId() == null) {
            return createUserExtraInfo(userExtraInfoDTO);
        }
        UserExtraInfoDTO result = userExtraInfoService.save(userExtraInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userExtraInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-extra-infos : get all the userExtraInfos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of userExtraInfos in body
     */
    @GetMapping("/user-extra-infos")
    @Timed
    public ResponseEntity<List<UserExtraInfoDTO>> getAllUserExtraInfos(UserExtraInfoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UserExtraInfos by criteria: {}", criteria);
        Page<UserExtraInfoDTO> page = userExtraInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-extra-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-extra-infos/:id : get the "id" userExtraInfo.
     *
     * @param id the id of the userExtraInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userExtraInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-extra-infos/{id}")
    @Timed
    public ResponseEntity<UserExtraInfoDTO> getUserExtraInfo(@PathVariable Long id) {
        log.debug("REST request to get UserExtraInfo : {}", id);
        UserExtraInfoDTO userExtraInfoDTO = userExtraInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userExtraInfoDTO));
    }

    /**
     * DELETE  /user-extra-infos/:id : delete the "id" userExtraInfo.
     *
     * @param id the id of the userExtraInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-extra-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserExtraInfo(@PathVariable Long id) {
        log.debug("REST request to delete UserExtraInfo : {}", id);
        userExtraInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/user-extra-infos?query=:query : search for the userExtraInfo corresponding
     * to the query.
     *
     * @param query the query of the userExtraInfo search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/user-extra-infos")
    @Timed
    public ResponseEntity<List<UserExtraInfoDTO>> searchUserExtraInfos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of UserExtraInfos for query {}", query);
        Page<UserExtraInfoDTO> page = userExtraInfoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/user-extra-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/user-extra-infos")
    @Timed
    public ResponseEntity<List<UserExtraInfoDTO>> searchExampleUserExtraInfos(UserExtraInfoSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of UserExtraInfos for searchDTO {}", searchDTO);
        Page<UserExtraInfoDTO> page = userExtraInfoService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/user-extra-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
