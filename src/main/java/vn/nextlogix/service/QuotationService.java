package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationDTO;
import vn.nextlogix.service.dto.QuotationSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDTO> search(String query, Pageable pageable);

    Page<QuotationDTO> searchExample( QuotationSearchDTO searchDTO, Pageable pageable);
    

    }
