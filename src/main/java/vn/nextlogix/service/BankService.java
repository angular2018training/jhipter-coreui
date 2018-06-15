package vn.nextlogix.service;

import vn.nextlogix.service.dto.BankDTO;
import java.util.List;

/**
 * Service Interface for managing Bank.
 */
public interface BankService {

    /**
     * Save a bank.
     *
     * @param bankDTO the entity to save
     * @return the persisted entity
     */
    BankDTO save(BankDTO bankDTO);

    /**
     * Get all the banks.
     *
     * @return the list of entities
     */
    List<BankDTO> findAll();

    /**
     * Get the "id" bank.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BankDTO findOne(Long id);

    /**
     * Delete the "id" bank.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the bank corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<BankDTO> search(String query);
}
