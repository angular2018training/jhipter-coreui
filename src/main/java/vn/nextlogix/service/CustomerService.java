package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerDTO;
import vn.nextlogix.service.dto.CustomerSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService {

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    CustomerDTO save(CustomerDTO customerDTO);

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerDTO findOne(Long id);

    /**
     * Delete the "id" customer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customer corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerDTO> search(String query, Pageable pageable);

    Page<CustomerDTO> searchExample( CustomerSearchDTO searchDTO, Pageable pageable);
    

    }
