package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerTypeDTO;
import vn.nextlogix.service.dto.CustomerTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerType.
 */
public interface CustomerTypeService {

    /**
     * Save a customerType.
     *
     * @param customerTypeDTO the entity to save
     * @return the persisted entity
     */
    CustomerTypeDTO save(CustomerTypeDTO customerTypeDTO);

    /**
     * Get all the customerTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerTypeDTO findOne(Long id);

    /**
     * Delete the "id" customerType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerTypeDTO> search(String query, Pageable pageable);

    Page<CustomerTypeDTO> searchExample( CustomerTypeSearchDTO searchDTO, Pageable pageable);
    

    }
