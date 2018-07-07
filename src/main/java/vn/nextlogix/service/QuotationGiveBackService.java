package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationGiveBackDTO;
import vn.nextlogix.service.dto.QuotationGiveBackSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationGiveBack.
 */
public interface QuotationGiveBackService {

    /**
     * Save a quotationGiveBack.
     *
     * @param quotationGiveBackDTO the entity to save
     * @return the persisted entity
     */
    QuotationGiveBackDTO save(QuotationGiveBackDTO quotationGiveBackDTO);

    /**
     * Get all the quotationGiveBacks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationGiveBackDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationGiveBack.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationGiveBackDTO findOne(Long id);

    /**
     * Delete the "id" quotationGiveBack.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationGiveBack corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationGiveBackDTO> search(String query, Pageable pageable);

    Page<QuotationGiveBackDTO> searchExample( QuotationGiveBackSearchDTO searchDTO, Pageable pageable);
    

    }
