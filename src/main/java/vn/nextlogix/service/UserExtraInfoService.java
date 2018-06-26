package vn.nextlogix.service;

import vn.nextlogix.service.dto.UserExtraInfoDTO;
import vn.nextlogix.service.dto.UserExtraInfoSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UserExtraInfo.
 */
public interface UserExtraInfoService {

    /**
     * Save a userExtraInfo.
     *
     * @param userExtraInfoDTO the entity to save
     * @return the persisted entity
     */
    UserExtraInfoDTO save(UserExtraInfoDTO userExtraInfoDTO);

    /**
     * Get all the userExtraInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserExtraInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userExtraInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserExtraInfoDTO findOne(Long id);

    /**
     * Delete the "id" userExtraInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userExtraInfo corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserExtraInfoDTO> search(String query, Pageable pageable);

    Page<UserExtraInfoDTO> searchExample( UserExtraInfoSearchDTO searchDTO, Pageable pageable);
    

    }
