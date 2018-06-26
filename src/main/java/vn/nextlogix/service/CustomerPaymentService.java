package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerPaymentDTO;
import vn.nextlogix.service.dto.CustomerPaymentSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerPayment.
 */
public interface CustomerPaymentService {

    /**
     * Save a customerPayment.
     *
     * @param customerPaymentDTO the entity to save
     * @return the persisted entity
     */
    CustomerPaymentDTO save(CustomerPaymentDTO customerPaymentDTO);

    /**
     * Get all the customerPayments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerPaymentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerPayment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerPaymentDTO findOne(Long id);

    /**
     * Delete the "id" customerPayment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerPayment corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerPaymentDTO> search(String query, Pageable pageable);

    Page<CustomerPaymentDTO> searchExample( CustomerPaymentSearchDTO searchDTO, Pageable pageable);
    

    }
