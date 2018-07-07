package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationReturnDTO;
import vn.nextlogix.service.dto.QuotationReturnSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationReturn.
 */
public interface QuotationReturnService {

    /**
     * Save a quotationReturn.
     *
     * @param quotationReturnDTO the entity to save
     * @return the persisted entity
     */
    QuotationReturnDTO save(QuotationReturnDTO quotationReturnDTO);

    /**
     * Get all the quotationReturns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationReturnDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationReturn.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationReturnDTO findOne(Long id);

    /**
     * Delete the "id" quotationReturn.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationReturn corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationReturnDTO> search(String query, Pageable pageable);

    Page<QuotationReturnDTO> searchExample( QuotationReturnSearchDTO searchDTO, Pageable pageable);
    

    }
