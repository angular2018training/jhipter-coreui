package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserExtraInfoService;
import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.repository.UserExtraInfoRepository;
import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
import vn.nextlogix.service.dto.UserExtraInfoDTO;
import vn.nextlogix.service.dto.UserExtraInfoSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.UserExtraInfoMapper;
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
 * Service Implementation for managing UserExtraInfo.
 */
@Service
@Transactional
public class UserExtraInfoServiceImpl implements UserExtraInfoService {

    private final Logger log = LoggerFactory.getLogger(UserExtraInfoServiceImpl.class);

    private final UserExtraInfoRepository userExtraInfoRepository;

    private final UserExtraInfoMapper userExtraInfoMapper;

    private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;



        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public UserExtraInfoServiceImpl(UserExtraInfoRepository userExtraInfoRepository, UserExtraInfoMapper userExtraInfoMapper, UserExtraInfoSearchRepository userExtraInfoSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.userExtraInfoRepository = userExtraInfoRepository;
        this.userExtraInfoMapper = userExtraInfoMapper;
        this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

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
        UserExtraInfo userExtraInfo = userExtraInfoRepository.findOne(id);
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



    @Override
    @Transactional(readOnly = true)
    public Page<UserExtraInfoDTO> searchExample(UserExtraInfoSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getEmail())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("email", "*"+searchDto.getEmail()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getPhone())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("phone", "*"+searchDto.getPhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getAddress())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("address", "*"+searchDto.getAddress()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<UserExtraInfo> userExtraInfoPage= userExtraInfoSearchRepository.search(query);
            List<UserExtraInfoDTO> userExtraInfoList =  StreamSupport
            .stream(userExtraInfoPage.spliterator(), false)
            .map(userExtraInfoMapper::toDto)
            .collect(Collectors.toList());
            userExtraInfoList.forEach(userExtraInfoDto -> {
            if(userExtraInfoDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(userExtraInfoDto.getCompanyId());
                userExtraInfoDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(userExtraInfoList,pageable,userExtraInfoPage.getTotalElements());
        }
}
