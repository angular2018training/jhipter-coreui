package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerLegalDTO;
import vn.nextlogix.service.dto.CustomerLegalSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerLegal.
 */
public interface CustomerLegalService {

    /**
     * Save a customerLegal.
     *
     * @param customerLegalDTO the entity to save
     * @return the persisted entity
     */
    CustomerLegalDTO save(CustomerLegalDTO customerLegalDTO);

    /**
     * Get all the customerLegals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerLegalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerLegal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerLegalDTO findOne(Long id);

    /**
     * Delete the "id" customerLegal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerLegal corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerLegalDTO> search(String query, Pageable pageable);

    Page<CustomerLegalDTO> searchExample( CustomerLegalSearchDTO searchDTO, Pageable pageable);
    

    }
