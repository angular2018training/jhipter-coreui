package vn.nextlogix.service;

import vn.nextlogix.service.dto.DistrictTypeDTO;
import vn.nextlogix.service.dto.DistrictTypeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DistrictType.
 */
public interface DistrictTypeService {

    /**
     * Save a districtType.
     *
     * @param districtTypeDTO the entity to save
     * @return the persisted entity
     */
    DistrictTypeDTO save(DistrictTypeDTO districtTypeDTO);

    /**
     * Get all the districtTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DistrictTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" districtType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DistrictTypeDTO findOne(Long id);

    /**
     * Delete the "id" districtType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the districtType corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DistrictTypeDTO> search(String query, Pageable pageable);

    Page<DistrictTypeDTO> searchExample( DistrictTypeSearchDTO searchDTO, Pageable pageable);
    

    }
