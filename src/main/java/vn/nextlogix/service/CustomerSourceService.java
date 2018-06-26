package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerSourceDTO;
import vn.nextlogix.service.dto.CustomerSourceSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerSource.
 */
public interface CustomerSourceService {

    /**
     * Save a customerSource.
     *
     * @param customerSourceDTO the entity to save
     * @return the persisted entity
     */
    CustomerSourceDTO save(CustomerSourceDTO customerSourceDTO);

    /**
     * Get all the customerSources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerSourceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerSource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerSourceDTO findOne(Long id);

    /**
     * Delete the "id" customerSource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerSource corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerSourceDTO> search(String query, Pageable pageable);

    Page<CustomerSourceDTO> searchExample( CustomerSourceSearchDTO searchDTO, Pageable pageable);
    

    }
