package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationTypeDTO;
import vn.nextlogix.service.dto.QuotationTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationType.
 */
public interface QuotationTypeService {

    /**
     * Save a quotationType.
     *
     * @param quotationTypeDTO the entity to save
     * @return the persisted entity
     */
    QuotationTypeDTO save(QuotationTypeDTO quotationTypeDTO);

    /**
     * Get all the quotationTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationTypeDTO findOne(Long id);

    /**
     * Delete the "id" quotationType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationTypeDTO> search(String query, Pageable pageable);

    Page<QuotationTypeDTO> searchExample( QuotationTypeSearchDTO searchDTO, Pageable pageable);
    

    }
