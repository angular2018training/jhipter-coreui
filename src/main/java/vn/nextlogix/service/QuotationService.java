package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationDTO;
import java.util.List;

/**
 * Service Interface for managing Quotation.
 */
public interface QuotationService {

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    QuotationDTO save(QuotationDTO quotationDTO);

    /**
     * Get all the quotations.
     *
     * @return the list of entities
     */
    List<QuotationDTO> findAll();

    /**
     * Get the "id" quotation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationDTO findOne(Long id);

    /**
     * Delete the "id" quotation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotation corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<QuotationDTO> search(String query);
}
