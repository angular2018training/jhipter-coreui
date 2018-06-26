package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerPostOfficeDTO;
import vn.nextlogix.service.dto.CustomerPostOfficeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerPostOffice.
 */
public interface CustomerPostOfficeService {

    /**
     * Save a customerPostOffice.
     *
     * @param customerPostOfficeDTO the entity to save
     * @return the persisted entity
     */
    CustomerPostOfficeDTO save(CustomerPostOfficeDTO customerPostOfficeDTO);

    /**
     * Get all the customerPostOffices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerPostOfficeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerPostOffice.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerPostOfficeDTO findOne(Long id);

    /**
     * Delete the "id" customerPostOffice.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerPostOffice corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerPostOfficeDTO> search(String query, Pageable pageable);

    Page<CustomerPostOfficeDTO> searchExample( CustomerPostOfficeSearchDTO searchDTO, Pageable pageable);
    

    }
