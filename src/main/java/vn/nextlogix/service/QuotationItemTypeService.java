package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationItemTypeDTO;
import vn.nextlogix.service.dto.QuotationItemTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationItemType.
 */
public interface QuotationItemTypeService {

    /**
     * Save a quotationItemType.
     *
     * @param quotationItemTypeDTO the entity to save
     * @return the persisted entity
     */
    QuotationItemTypeDTO save(QuotationItemTypeDTO quotationItemTypeDTO);

    /**
     * Get all the quotationItemTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationItemTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationItemType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationItemTypeDTO findOne(Long id);

    /**
     * Delete the "id" quotationItemType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationItemType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationItemTypeDTO> search(String query, Pageable pageable);

    Page<QuotationItemTypeDTO> searchExample( QuotationItemTypeSearchDTO searchDTO, Pageable pageable);
    

    }
