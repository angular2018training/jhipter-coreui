package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserPositionService;
import vn.nextlogix.domain.UserPosition;
import vn.nextlogix.repository.UserPositionRepository;
import vn.nextlogix.repository.search.UserPositionSearchRepository;
import vn.nextlogix.service.dto.UserPositionDTO;
import vn.nextlogix.service.dto.UserPositionSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.PostOffice;
    import vn.nextlogix.domain.Position;
    import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.service.mapper.UserPositionMapper;
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

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;

    import vn.nextlogix.repository.search.PositionSearchRepository;
    import vn.nextlogix.service.mapper.PositionMapper;


    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UserPosition.
 */
@Service
@Transactional
public class UserPositionServiceImpl implements UserPositionService {

    private final Logger log = LoggerFactory.getLogger(UserPositionServiceImpl.class);

    private final UserPositionRepository userPositionRepository;

    private final UserPositionMapper userPositionMapper;

    private final UserPositionSearchRepository userPositionSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final PostOfficeSearchRepository postOfficeSearchRepository;
        private final PostOfficeMapper postOfficeMapper;

        private final PositionSearchRepository positionSearchRepository;
        private final PositionMapper positionMapper;


        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;


    public UserPositionServiceImpl(UserPositionRepository userPositionRepository, UserPositionMapper userPositionMapper, UserPositionSearchRepository userPositionSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,PostOfficeSearchRepository postOfficeSearchRepository,PostOfficeMapper  postOfficeMapper
,PositionSearchRepository positionSearchRepository,PositionMapper  positionMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
) {
        this.userPositionRepository = userPositionRepository;
        this.userPositionMapper = userPositionMapper;
        this.userPositionSearchRepository = userPositionSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.postOfficeSearchRepository = postOfficeSearchRepository;
                                     this.postOfficeMapper = postOfficeMapper;
                                    this.positionSearchRepository = positionSearchRepository;
                                     this.positionMapper = positionMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;

    }

    /**
     * Save a userPosition.
     *
     * @param userPositionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserPositionDTO save(UserPositionDTO userPositionDTO) {
        log.debug("Request to save UserPosition : {}", userPositionDTO);
        UserPosition userPosition = userPositionMapper.toEntity(userPositionDTO);
        userPosition = userPositionRepository.save(userPosition);
        UserPositionDTO result = userPositionMapper.toDto(userPosition);
        userPositionSearchRepository.save(userPosition);
        return result;
    }

    /**
     * Get all the userPositions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserPositionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserPositions");
        return userPositionRepository.findAll(pageable)
            .map(userPositionMapper::toDto);
    }

    /**
     * Get one userPosition by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserPositionDTO findOne(Long id) {
        log.debug("Request to get UserPosition : {}", id);
        UserPosition userPosition = userPositionRepository.findOneWithEagerRelationships(id);
        return userPositionMapper.toDto(userPosition);
    }

    /**
     * Delete the userPosition by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPosition : {}", id);
        userPositionRepository.delete(id);
        userPositionSearchRepository.delete(id);
    }

    /**
     * Search for the userPosition corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserPositionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserPositions for query {}", query);
        Page<UserPosition> result = userPositionSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(userPositionMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<UserPositionDTO> searchExample(UserPositionSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<UserPosition> userPositionPage= userPositionSearchRepository.search(query);
            List<UserPositionDTO> userPositionList =  StreamSupport
            .stream(userPositionPage.spliterator(), false)
            .map(userPositionMapper::toDto)
            .collect(Collectors.toList());
            userPositionList.forEach(userPositionDto -> {
            if(userPositionDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(userPositionDto.getCompanyId());
                userPositionDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(userPositionDto.getPostOfficeId()!=null){
                PostOffice postOffice= postOfficeSearchRepository.findOne(userPositionDto.getPostOfficeId());
                userPositionDto.setPostOfficeDTO(postOfficeMapper.toDto(postOffice));
            }
            if(userPositionDto.getPositionId()!=null){
                Position position= positionSearchRepository.findOne(userPositionDto.getPositionId());
                userPositionDto.setPositionDTO(positionMapper.toDto(position));
            }
            if(userPositionDto.getUserExtraInfoId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(userPositionDto.getUserExtraInfoId());
                userPositionDto.setUserExtraInfoDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            });
            return new PageImpl<>(userPositionList,pageable,userPositionPage.getTotalElements());
        }
}
