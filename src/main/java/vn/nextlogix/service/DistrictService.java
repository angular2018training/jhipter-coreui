package vn.nextlogix.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.nextlogix.service.dto.DistrictDTO;
import vn.nextlogix.service.dto.DistrictSearchDTO;

/**
 * Service Interface for managing District.
 */
public interface DistrictService {

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    DistrictDTO save(DistrictDTO districtDTO);

    /**
     * Get all the districts.
     *
     * @return the list of entities
     */
    List<DistrictDTO> findAll();

    /**
     * Get the "id" district.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DistrictDTO findOne(Long id);

    /**
     * Delete the "id" district.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the district corresponding to the query.
     *
     * @param query the query of the search
     *
     * @return the list of entities
     */
    List<DistrictDTO> search(String query);

    Page<DistrictDTO> searchByExample(DistrictSearchDTO searchDto, Pageable pageable);

}
