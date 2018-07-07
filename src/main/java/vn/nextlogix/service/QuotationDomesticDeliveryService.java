package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationDomesticDeliveryDTO;
import vn.nextlogix.service.dto.QuotationDomesticDeliverySearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationDomesticDelivery.
 */
public interface QuotationDomesticDeliveryService {

    /**
     * Save a quotationDomesticDelivery.
     *
     * @param quotationDomesticDeliveryDTO the entity to save
     * @return the persisted entity
     */
    QuotationDomesticDeliveryDTO save(QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO);

    /**
     * Get all the quotationDomesticDeliveries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDomesticDeliveryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationDomesticDelivery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationDomesticDeliveryDTO findOne(Long id);

    /**
     * Delete the "id" quotationDomesticDelivery.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationDomesticDelivery corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDomesticDeliveryDTO> search(String query, Pageable pageable);

    Page<QuotationDomesticDeliveryDTO> searchExample( QuotationDomesticDeliverySearchDTO searchDTO, Pageable pageable);
    

    }
