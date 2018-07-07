package vn.nextlogix.service;

import vn.nextlogix.service.dto.QuotationInsuranceDTO;
import vn.nextlogix.service.dto.QuotationInsuranceSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationInsurance.
 */
public interface QuotationInsuranceService {

    /**
     * Save a quotationInsurance.
     *
     * @param quotationInsuranceDTO the entity to save
     * @return the persisted entity
     */
    QuotationInsuranceDTO save(QuotationInsuranceDTO quotationInsuranceDTO);

    /**
     * Get all the quotationInsurances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationInsuranceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationInsurance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationInsuranceDTO findOne(Long id);

    /**
     * Delete the "id" quotationInsurance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotationInsurance corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationInsuranceDTO> search(String query, Pageable pageable);

    Page<QuotationInsuranceDTO> searchExample( QuotationInsuranceSearchDTO searchDTO, Pageable pageable);
    

    }
