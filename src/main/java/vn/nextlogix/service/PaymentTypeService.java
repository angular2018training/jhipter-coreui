package vn.nextlogix.service;

import vn.nextlogix.service.dto.PaymentTypeDTO;
import vn.nextlogix.service.dto.PaymentTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PaymentType.
 */
public interface PaymentTypeService {

    /**
     * Save a paymentType.
     *
     * @param paymentTypeDTO the entity to save
     * @return the persisted entity
     */
    PaymentTypeDTO save(PaymentTypeDTO paymentTypeDTO);

    /**
     * Get all the paymentTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PaymentTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" paymentType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PaymentTypeDTO findOne(Long id);

    /**
     * Delete the "id" paymentType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the paymentType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PaymentTypeDTO> search(String query, Pageable pageable);

    Page<PaymentTypeDTO> searchExample( PaymentTypeSearchDTO searchDTO, Pageable pageable);
    

    }
