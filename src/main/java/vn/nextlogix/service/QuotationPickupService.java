package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationPickupDTO;
import vn.nextlogix.service.dto.QuotationPickupSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationPickup.
 */
public interface QuotationPickupService {

    /**
     * Save a quotationPickup.
     *
     * @param quotationPickupDTO the entity to save
     * @return the persisted entity
     */
    QuotationPickupDTO save(QuotationPickupDTO quotationPickupDTO);

    /**
     * Get all the quotationPickups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationPickupDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationPickup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationPickupDTO findOne(Long id);

    /**
     * Delete the "id" quotationPickup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationPickup corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationPickupDTO> search(String query, Pageable pageable);

    Page<QuotationPickupDTO> searchExample( QuotationPickupSearchDTO searchDTO, Pageable pageable);
    

    }
