package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserExtraInfoService;
import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.repository.UserExtraInfoRepository;
import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
import vn.nextlogix.service.dto.UserExtraInfoDTO;
import vn.nextlogix.service.mapper.UserExtraInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UserExtraInfo.
 */
@Service
@Transactional
public class UserExtraInfoServiceImpl implements UserExtraInfoService {

    private final Logger log = LoggerFactory.getLogger(UserExtraInfoServiceImpl.class);

    private final UserExtraInfoRepository userExtraInfoRepository;

    private final UserExtraInfoMapper userExtraInfoMapper;

    private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;

    public UserExtraInfoServiceImpl(UserExtraInfoRepository userExtraInfoRepository, UserExtraInfoMapper userExtraInfoMapper, UserExtraInfoSearchRepository userExtraInfoSearchRepository) {
        this.userExtraInfoRepository = userExtraInfoRepository;
        this.userExtraInfoMapper = userExtraInfoMapper;
        this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
    }

    /**
     * Save a userExtraInfo.
     *
     * @param userExtraInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserExtraInfoDTO save(UserExtraInfoDTO userExtraInfoDTO) {
        log.debug("Request to save UserExtraInfo : {}", userExtraInfoDTO);
        UserExtraInfo userExtraInfo = userExtraInfoMapper.toEntity(userExtraInfoDTO);
        userExtraInfo = userExtraInfoRepository.save(userExtraInfo);
        UserExtraInfoDTO result = userExtraInfoMapper.toDto(userExtraInfo);
        userExtraInfoSearchRepository.save(userExtraInfo);
        return result;
    }

    /**
     * Get all the userExtraInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserExtraInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserExtraInfos");
        return userExtraInfoRepository.findAll(pageable)
            .map(userExtraInfoMapper::toDto);
    }

    /**
     * Get one userExtraInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserExtraInfoDTO findOne(Long id) {
        log.debug("Request to get UserExtraInfo : {}", id);
        UserExtraInfo userExtraInfo = userExtraInfoRepository.findOneWithEagerRelationships(id);
        return userExtraInfoMapper.toDto(userExtraInfo);
    }

    /**
     * Delete the userExtraInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserExtraInfo : {}", id);
        userExtraInfoRepository.delete(id);
        userExtraInfoSearchRepository.delete(id);
    }

    /**
     * Search for the userExtraInfo corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserExtraInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserExtraInfos for query {}", query);
        Page<UserExtraInfo> result = userExtraInfoSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(userExtraInfoMapper::toDto);
    }
}
