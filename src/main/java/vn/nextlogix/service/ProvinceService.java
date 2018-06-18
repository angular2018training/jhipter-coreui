package vn.nextlogix.service;

import vn.nextlogix.service.dto.ProvinceDTO;
import java.util.List;

/**
 * Service Interface for managing Province.
 */
public interface ProvinceService {

    /**
     * Save a province.
     *
     * @param provinceDTO the entity to save
     * @return the persisted entity
     */
    ProvinceDTO save(ProvinceDTO provinceDTO);

    /**
     * Get all the provinces.
     *
     * @return the list of entities
     */
    List<ProvinceDTO> findAll();

    /**
     * Get the "id" province.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ProvinceDTO findOne(Long id);

    /**
     * Delete the "id" province.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the province corresponding to the query.
     *
     * @param query the query of the search
     *
     * @return the list of entities
     */
    List<ProvinceDTO> search(String query);

}
