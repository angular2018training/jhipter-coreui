package vn.nextlogix.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import vn.nextlogix.domain.UserPosition;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.UserPositionRepository;
import vn.nextlogix.repository.search.UserPositionSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;

    import vn.nextlogix.repository.search.PositionSearchRepository;
    import vn.nextlogix.service.mapper.PositionMapper;


    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

import vn.nextlogix.service.dto.UserPositionCriteria;

import vn.nextlogix.service.dto.UserPositionDTO;
import vn.nextlogix.service.mapper.UserPositionMapper;

/**
 * Service for executing complex queries for UserPosition entities in the database.
 * The main input is a {@link UserPositionCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserPositionDTO} or a {@link Page} of {@link UserPositionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserPositionQueryService extends QueryService<UserPosition> {

    private final Logger log = LoggerFactory.getLogger(UserPositionQueryService.class);


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


    public UserPositionQueryService(UserPositionRepository userPositionRepository, UserPositionMapper userPositionMapper, UserPositionSearchRepository userPositionSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
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
     * Return a {@link List} of {@link UserPositionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserPositionDTO> findByCriteria(UserPositionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<UserPosition> specification = createSpecification(criteria);
        return userPositionMapper.toDto(userPositionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserPositionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserPositionDTO> findByCriteria(UserPositionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<UserPosition> specification = createSpecification(criteria);
        final Page<UserPosition> result = userPositionRepository.findAll(specification, page);
        return result.map(userPositionMapper::toDto);
    }

    /**
     * Function to convert UserPositionCriteria to a {@link Specifications}
     */
    private Specifications<UserPosition> createSpecification(UserPositionCriteria criteria) {
        Specifications<UserPosition> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UserPosition_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), UserPosition_.company, Company_.id));
            }
            if (criteria.getPostOfficeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPostOfficeId(), UserPosition_.postOffice, PostOffice_.id));
            }
            if (criteria.getPositionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPositionId(), UserPosition_.position, Position_.id));
            }
            if (criteria.getUserGroupId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserGroupId(), UserPosition_.userGroups, UserGroup_.id));
            }
            if (criteria.getUserExtraInfoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserExtraInfoId(), UserPosition_.userExtraInfo, UserExtraInfo_.id));
            }
        }
        return specification;
    }

}
