package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserGroupService;
import vn.nextlogix.domain.UserGroup;
import vn.nextlogix.repository.UserGroupRepository;
import vn.nextlogix.repository.search.UserGroupSearchRepository;
import vn.nextlogix.service.dto.UserGroupDTO;
import vn.nextlogix.service.mapper.UserGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UserGroup.
 */
@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {

    private final Logger log = LoggerFactory.getLogger(UserGroupServiceImpl.class);

    private final UserGroupRepository userGroupRepository;

    private final UserGroupMapper userGroupMapper;

    private final UserGroupSearchRepository userGroupSearchRepository;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserGroupMapper userGroupMapper, UserGroupSearchRepository userGroupSearchRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupMapper = userGroupMapper;
        this.userGroupSearchRepository = userGroupSearchRepository;
    }

    /**
     * Save a userGroup.
     *
     * @param userGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserGroupDTO save(UserGroupDTO userGroupDTO) {
        log.debug("Request to save UserGroup : {}", userGroupDTO);
        UserGroup userGroup = userGroupMapper.toEntity(userGroupDTO);
        userGroup = userGroupRepository.save(userGroup);
        UserGroupDTO result = userGroupMapper.toDto(userGroup);
        userGroupSearchRepository.save(userGroup);
        return result;
    }

    /**
     * Get all the userGroups.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserGroupDTO> findAll() {
        log.debug("Request to get all UserGroups");
        return userGroupRepository.findAllWithEagerRelationships().stream()
            .map(userGroupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserGroupDTO findOne(Long id) {
        log.debug("Request to get UserGroup : {}", id);
        UserGroup userGroup = userGroupRepository.findOneWithEagerRelationships(id);
        return userGroupMapper.toDto(userGroup);
    }

    /**
     * Delete the userGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserGroup : {}", id);
        userGroupRepository.delete(id);
        userGroupSearchRepository.delete(id);
    }

    /**
     * Search for the userGroup corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserGroupDTO> search(String query) {
        log.debug("Request to search UserGroups for query {}", query);
        return StreamSupport
            .stream(userGroupSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(userGroupMapper::toDto)
            .collect(Collectors.toList());
    }
}
