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

import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.UserExtraInfoRepository;
import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.UserExtraInfoCriteria;

import vn.nextlogix.service.dto.UserExtraInfoDTO;
import vn.nextlogix.service.mapper.UserExtraInfoMapper;

/**
 * Service for executing complex queries for UserExtraInfo entities in the database.
 * The main input is a {@link UserExtraInfoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserExtraInfoDTO} or a {@link Page} of {@link UserExtraInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserExtraInfoQueryService extends QueryService<UserExtraInfo> {

    private final Logger log = LoggerFactory.getLogger(UserExtraInfoQueryService.class);


    private final UserExtraInfoRepository userExtraInfoRepository;

    private final UserExtraInfoMapper userExtraInfoMapper;

    private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;



        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public UserExtraInfoQueryService(UserExtraInfoRepository userExtraInfoRepository, UserExtraInfoMapper userExtraInfoMapper, UserExtraInfoSearchRepository userExtraInfoSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.userExtraInfoRepository = userExtraInfoRepository;
        this.userExtraInfoMapper = userExtraInfoMapper;
        this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link UserExtraInfoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserExtraInfoDTO> findByCriteria(UserExtraInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<UserExtraInfo> specification = createSpecification(criteria);
        return userExtraInfoMapper.toDto(userExtraInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserExtraInfoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserExtraInfoDTO> findByCriteria(UserExtraInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<UserExtraInfo> specification = createSpecification(criteria);
        final Page<UserExtraInfo> result = userExtraInfoRepository.findAll(specification, page);
        return result.map(userExtraInfoMapper::toDto);
    }

    /**
     * Function to convert UserExtraInfoCriteria to a {@link Specifications}
     */
    private Specifications<UserExtraInfo> createSpecification(UserExtraInfoCriteria criteria) {
        Specifications<UserExtraInfo> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UserExtraInfo_.id));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), UserExtraInfo_.phone));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), UserExtraInfo_.address));
            }
            if (criteria.getValidDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidDate(), UserExtraInfo_.validDate));
            }
            if (criteria.getLastLoginDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastLoginDate(), UserExtraInfo_.lastLoginDate));
            }
            if (criteria.getContractExpirationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContractExpirationDate(), UserExtraInfo_.contractExpirationDate));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), UserExtraInfo_.company, Company_.id));
            }
        }
        return specification;
    }

}
