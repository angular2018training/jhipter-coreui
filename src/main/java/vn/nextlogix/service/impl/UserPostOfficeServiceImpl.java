package vn.nextlogix.service.impl;

import vn.nextlogix.service.UserPostOfficeService;
import vn.nextlogix.domain.UserPostOffice;
import vn.nextlogix.exception.BusinessException;
import vn.nextlogix.repository.UserPostOfficeRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.UserPostOfficeSearchRepository;
import vn.nextlogix.service.dto.UserPostOfficeDTO;
import vn.nextlogix.service.dto.UserPostOfficeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.PostOffice;
    import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.service.mapper.UserPostOfficeMapper;
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
 * Service Implementation for managing UserPostOffice.
 */
@Service
@Transactional
public class UserPostOfficeServiceImpl implements UserPostOfficeService {

    private final Logger log = LoggerFactory.getLogger(UserPostOfficeServiceImpl.class);

    private final UserPostOfficeRepository userPostOfficeRepository;

    private final UserPostOfficeMapper userPostOfficeMapper;

    private final UserPostOfficeSearchRepository userPostOfficeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final PostOfficeSearchRepository postOfficeSearchRepository;
        private final PostOfficeMapper postOfficeMapper;


        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;


    public UserPostOfficeServiceImpl(UserPostOfficeRepository userPostOfficeRepository, UserPostOfficeMapper userPostOfficeMapper, UserPostOfficeSearchRepository userPostOfficeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,PostOfficeSearchRepository postOfficeSearchRepository,PostOfficeMapper  postOfficeMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
) {
        this.userPostOfficeRepository = userPostOfficeRepository;
        this.userPostOfficeMapper = userPostOfficeMapper;
        this.userPostOfficeSearchRepository = userPostOfficeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.postOfficeSearchRepository = postOfficeSearchRepository;
                                     this.postOfficeMapper = postOfficeMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;

    }

    /**
     * Save a userPostOffice.
     *
     * @param userPostOfficeDTO the entity to save
     * @return the persisted entity
     * @throws BusinessException 
     */
    @Override
    public UserPostOfficeDTO save(UserPostOfficeDTO userPostOfficeDTO) throws BusinessException {
        log.debug("Request to save UserPostOffice : {}", userPostOfficeDTO);
        checkExists(userPostOfficeDTO);
        UserPostOffice userPostOffice = userPostOfficeMapper.toEntity(userPostOfficeDTO);
        userPostOffice = userPostOfficeRepository.save(userPostOffice);
        UserPostOfficeDTO result = userPostOfficeMapper.toDto(userPostOffice);
        userPostOfficeSearchRepository.save(userPostOffice);
        return result;
    }

    private void checkExists(UserPostOfficeDTO userPostOfficeDTO) throws BusinessException {
		if(userPostOfficeDTO.getId() ==null) {
			Long userExtraInfoId = userPostOfficeDTO.getUserExtraInfoParentId();
			Long postOfficeId = userPostOfficeDTO.getPostOfficeId();
			List<UserPostOffice>  userPostOffice = userPostOfficeRepository.findByUserExtraInfoParent_IdAndPostOfficeId(userExtraInfoId,postOfficeId);
			if(userPostOffice.size()>0) throw new BusinessException("userPostOffice.duplicate.record");
		}
		
	}

	/**
     * Get all the userPostOffices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserPostOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserPostOffices");
        return userPostOfficeRepository.findAll(pageable)
            .map(userPostOfficeMapper::toDto);
    }

    /**
     * Get one userPostOffice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserPostOfficeDTO findOne(Long id) {
        log.debug("Request to get UserPostOffice : {}", id);
        UserPostOffice userPostOffice = userPostOfficeRepository.findOneWithEagerRelationships(id);
        return userPostOfficeMapper.toDto(userPostOffice);
    }

    /**
     * Delete the userPostOffice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPostOffice : {}", id);
        userPostOfficeRepository.delete(id);
        userPostOfficeSearchRepository.delete(id);
    }

    /**
     * Search for the userPostOffice corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserPostOfficeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserPostOffices for query {}", query);
        Page<UserPostOffice> result = userPostOfficeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(userPostOfficeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<UserPostOfficeDTO> searchExample(UserPostOfficeSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getPostOfficeId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("postOffice.id", searchDto.getPostOfficeId()));
            }
            if(searchDto.getUserExtraInfoParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("userExtraInfoParent.id", searchDto.getUserExtraInfoParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<UserPostOffice> userPostOfficePage= userPostOfficeSearchRepository.search(query);
            List<UserPostOfficeDTO> userPostOfficeList =  StreamSupport
            .stream(userPostOfficePage.spliterator(), false)
            .map(userPostOfficeMapper::toDto)
            .collect(Collectors.toList());
            userPostOfficeList.forEach(userPostOfficeDto -> {
            if(userPostOfficeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(userPostOfficeDto.getCompanyId());
                userPostOfficeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(userPostOfficeDto.getPostOfficeId()!=null){
                PostOffice postOffice= postOfficeSearchRepository.findOne(userPostOfficeDto.getPostOfficeId());
                userPostOfficeDto.setPostOfficeDTO(postOfficeMapper.toDto(postOffice));
            }
            if(userPostOfficeDto.getUserExtraInfoParentId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(userPostOfficeDto.getUserExtraInfoParentId());
                userPostOfficeDto.setUserExtraInfoParentDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            });
            return new PageImpl<>(userPostOfficeList,pageable,userPostOfficePage.getTotalElements());
        }
}
