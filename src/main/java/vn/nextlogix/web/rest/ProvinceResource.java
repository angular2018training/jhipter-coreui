package vn.nextlogix.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.nextlogix.exception.ApplicationException;
import vn.nextlogix.service.ProvinceService;
import vn.nextlogix.web.rest.errors.BadRequestAlertException;
import vn.nextlogix.web.rest.util.HeaderUtil;
import vn.nextlogix.web.rest.util.PaginationUtil;
import vn.nextlogix.service.dto.ProvinceDTO;
import vn.nextlogix.service.dto.ProvinceSearchDTO;
import vn.nextlogix.service.dto.ProvinceCriteria;
import vn.nextlogix.service.ProvinceQueryService;
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
 * REST controller for managing Province.
 */
@RestController
@RequestMapping("/api")
public class ProvinceResource {

    private final Logger log = LoggerFactory.getLogger(ProvinceResource.class);

    private static final String ENTITY_NAME = "province";

    private final ProvinceService provinceService;

    private final ProvinceQueryService provinceQueryService;

    public ProvinceResource(ProvinceService provinceService, ProvinceQueryService provinceQueryService     ) {
        this.provinceService = provinceService;
        this.provinceQueryService = provinceQueryService;


    }

    /**
     * POST  /provinces : Create a new province.
     *
     * @param provinceDTO the provinceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new provinceDTO, or with status 400 (Bad Request) if the province has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/provinces")
    @Timed
    public ResponseEntity<ProvinceDTO> createProvince(@Valid @RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        log.debug("REST request to save Province : {}", provinceDTO);
        if (provinceDTO.getId() != null) {
            throw new BadRequestAlertException("A new province cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvinceDTO result = provinceService.save(provinceDTO);
        return ResponseEntity.created(new URI("/api/provinces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provinces : Updates an existing province.
     *
     * @param provinceDTO the provinceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated provinceDTO,
     * or with status 400 (Bad Request) if the provinceDTO is not valid,
     * or with status 500 (Internal Server Error) if the provinceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/provinces")
    @Timed
    public ResponseEntity<ProvinceDTO> updateProvince(@Valid @RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        log.debug("REST request to update Province : {}", provinceDTO);
        if (provinceDTO.getId() == null) {
            return createProvince(provinceDTO);
        }
        ProvinceDTO result = provinceService.save(provinceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, provinceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provinces : get all the provinces.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of provinces in body
     */
    @GetMapping("/provinces")
    @Timed
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces(ProvinceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Provinces by criteria: {}", criteria);
        Page<ProvinceDTO> page = provinceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/provinces");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /provinces/:id : get the "id" province.
     *
     * @param id the id of the provinceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the provinceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/provinces/{id}")
    @Timed
    public ResponseEntity<ProvinceDTO> getProvince(@PathVariable Long id) {
        log.debug("REST request to get Province : {}", id);
        ProvinceDTO provinceDTO = provinceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(provinceDTO));
    }

    /**
     * DELETE  /provinces/:id : delete the "id" province.
     *
     * @param id the id of the provinceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/provinces/{id}")
    @Timed
    public ResponseEntity<Void> deleteProvince(@PathVariable Long id) {
        log.debug("REST request to delete Province : {}", id);
        provinceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/provinces?query=:query : search for the province corresponding
     * to the query.
     *
     * @param query the query of the province search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/provinces")
    @Timed
    public ResponseEntity<List<ProvinceDTO>> searchProvinces(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Provinces for query {}", query);
        Page<ProvinceDTO> page = provinceService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/provinces");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/_search_example/provinces")
    @Timed
    public ResponseEntity<List<ProvinceDTO>> searchExampleProvinces(ProvinceSearchDTO searchDTO , Pageable pageable) throws ApplicationException {
        log.debug("REST request to search example for a page of Provinces for searchDTO {}", searchDTO);
        Page<ProvinceDTO> page = provinceService.searchExample(searchDTO, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchDTO, page, "/api/_search_example/provinces");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
