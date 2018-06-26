package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserGroupService;
import vn.nextlogix.domain.UserGroup;
import vn.nextlogix.repository.UserGroupRepository;
import vn.nextlogix.repository.search.UserGroupSearchRepository;
import vn.nextlogix.service.dto.UserGroupDTO;
import vn.nextlogix.service.dto.UserGroupSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.UserGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
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


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserGroupMapper userGroupMapper, UserGroupSearchRepository userGroupSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupMapper = userGroupMapper;
        this.userGroupSearchRepository = userGroupSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserGroups");
        return userGroupRepository.findAll(pageable)
            .map(userGroupMapper::toDto);
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
        UserGroup userGroup = userGroupRepository.findOne(id);
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
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserGroupDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserGroups for query {}", query);
        Page<UserGroup> result = userGroupSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(userGroupMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<UserGroupDTO> searchExample(UserGroupSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<UserGroup> userGroupPage= userGroupSearchRepository.search(query);
            List<UserGroupDTO> userGroupList =  StreamSupport
            .stream(userGroupPage.spliterator(), false)
            .map(userGroupMapper::toDto)
            .collect(Collectors.toList());
            userGroupList.forEach(userGroupDto -> {
            if(userGroupDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(userGroupDto.getCompanyId());
                userGroupDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(userGroupList,pageable,userGroupPage.getTotalElements());
        }
}
