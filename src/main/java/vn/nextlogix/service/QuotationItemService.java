package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationItemDTO;
import java.util.List;

/**
 * Service Interface for managing QuotationItem.
 */
public interface QuotationItemService {

    /**
     * Save a quotationItem.
     *
     * @param quotationItemDTO the entity to save
     * @return the persisted entity
     */
    QuotationItemDTO save(QuotationItemDTO quotationItemDTO);

    /**
     * Get all the quotationItems.
     *
     * @return the list of entities
     */
    List<QuotationItemDTO> findAll();

    /**
     * Get the "id" quotationItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationItemDTO findOne(Long id);

    /**
     * Delete the "id" quotationItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationItem corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<QuotationItemDTO> search(String query);
}
