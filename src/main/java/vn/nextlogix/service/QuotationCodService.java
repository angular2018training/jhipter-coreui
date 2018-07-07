package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationCodDTO;
import vn.nextlogix.service.dto.QuotationCodSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationCod.
 */
public interface QuotationCodService {

    /**
     * Save a quotationCod.
     *
     * @param quotationCodDTO the entity to save
     * @return the persisted entity
     */
    QuotationCodDTO save(QuotationCodDTO quotationCodDTO);

    /**
     * Get all the quotationCods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationCodDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationCod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationCodDTO findOne(Long id);

    /**
     * Delete the "id" quotationCod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationCod corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationCodDTO> search(String query, Pageable pageable);

    Page<QuotationCodDTO> searchExample( QuotationCodSearchDTO searchDTO, Pageable pageable);
    

    }
