package vn.nextlogix.service;

import vn.nextlogix.service.dto.CustomerPaymentDTO;
import java.util.List;

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
     * @return the list of entities
     */
    List<CustomerPaymentDTO> findAll();

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
     * @return the list of entities
     */
    List<CustomerPaymentDTO> search(String query);
}
