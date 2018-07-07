package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationSubServicesDTO;
import vn.nextlogix.service.dto.QuotationSubServicesSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationSubServices.
 */
public interface QuotationSubServicesService {

    /**
     * Save a quotationSubServices.
     *
     * @param quotationSubServicesDTO the entity to save
     * @return the persisted entity
     */
    QuotationSubServicesDTO save(QuotationSubServicesDTO quotationSubServicesDTO);

    /**
     * Get all the quotationSubServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationSubServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationSubServices.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationSubServicesDTO findOne(Long id);

    /**
     * Delete the "id" quotationSubServices.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationSubServices corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationSubServicesDTO> search(String query, Pageable pageable);

    Page<QuotationSubServicesDTO> searchExample( QuotationSubServicesSearchDTO searchDTO, Pageable pageable);
    

    }
