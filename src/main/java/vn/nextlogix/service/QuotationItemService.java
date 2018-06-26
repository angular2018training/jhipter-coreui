package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationItemDTO;
import vn.nextlogix.service.dto.QuotationItemSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationItemDTO> findAll(Pageable pageable);

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationItemDTO> search(String query, Pageable pageable);

    Page<QuotationItemDTO> searchExample( QuotationItemSearchDTO searchDTO, Pageable pageable);
    

    }
