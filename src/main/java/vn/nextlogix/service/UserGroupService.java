package vn.nextlogix.service;

import vn.nextlogix.service.dto.UserGroupDTO;
import java.util.List;

/**
 * Service Interface for managing UserGroup.
 */
public interface UserGroupService {

    /**
     * Save a userGroup.
     *
     * @param userGroupDTO the entity to save
     * @return the persisted entity
     */
    UserGroupDTO save(UserGroupDTO userGroupDTO);

    /**
     * Get all the userGroups.
     *
     * @return the list of entities
     */
    List<UserGroupDTO> findAll();

    /**
     * Get the "id" userGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserGroupDTO findOne(Long id);

    /**
     * Delete the "id" userGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userGroup corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<UserGroupDTO> search(String query);
}
