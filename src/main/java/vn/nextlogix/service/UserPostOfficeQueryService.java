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

import vn.nextlogix.domain.UserPostOffice;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.UserPostOfficeRepository;
import vn.nextlogix.repository.search.UserPostOfficeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;


    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

import vn.nextlogix.service.dto.UserPostOfficeCriteria;

import vn.nextlogix.service.dto.UserPostOfficeDTO;
import vn.nextlogix.service.mapper.UserPostOfficeMapper;

/**
 * Service for executing complex queries for UserPostOffice entities in the database.
 * The main input is a {@link UserPostOfficeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserPostOfficeDTO} or a {@link Page} of {@link UserPostOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserPostOfficeQueryService extends QueryService<UserPostOffice> {

    private final Logger log = LoggerFactory.getLogger(UserPostOfficeQueryService.class);


    private final UserPostOfficeRepository userPostOfficeRepository;

    private final UserPostOfficeMapper userPostOfficeMapper;

    private final UserPostOfficeSearchRepository userPostOfficeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final PostOfficeSearchRepository postOfficeSearchRepository;
        private final PostOfficeMapper postOfficeMapper;


        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;


    public UserPostOfficeQueryService(UserPostOfficeRepository userPostOfficeRepository, UserPostOfficeMapper userPostOfficeMapper, UserPostOfficeSearchRepository userPostOfficeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
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
     * Return a {@link List} of {@link UserPostOfficeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserPostOfficeDTO> findByCriteria(UserPostOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<UserPostOffice> specification = createSpecification(criteria);
        return userPostOfficeMapper.toDto(userPostOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserPostOfficeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserPostOfficeDTO> findByCriteria(UserPostOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<UserPostOffice> specification = createSpecification(criteria);
        final Page<UserPostOffice> result = userPostOfficeRepository.findAll(specification, page);
        return result.map(userPostOfficeMapper::toDto);
    }

    /**
     * Function to convert UserPostOfficeCriteria to a {@link Specifications}
     */
    private Specifications<UserPostOffice> createSpecification(UserPostOfficeCriteria criteria) {
        Specifications<UserPostOffice> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UserPostOffice_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), UserPostOffice_.company, Company_.id));
            }
            if (criteria.getPostOfficeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPostOfficeId(), UserPostOffice_.postOffice, PostOffice_.id));
            }
            if (criteria.getUserGroupId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserGroupId(), UserPostOffice_.userGroups, UserGroup_.id));
            }
            if (criteria.getUserExtraInfoParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserExtraInfoParentId(), UserPostOffice_.userExtraInfoParent, UserExtraInfo_.id));
            }
        }
        return specification;
    }

}
