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

import vn.nextlogix.domain.UserGroup;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.UserGroupRepository;
import vn.nextlogix.repository.search.UserGroupSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.UserGroupCriteria;

import vn.nextlogix.service.dto.UserGroupDTO;
import vn.nextlogix.service.mapper.UserGroupMapper;

/**
 * Service for executing complex queries for UserGroup entities in the database.
 * The main input is a {@link UserGroupCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserGroupDTO} or a {@link Page} of {@link UserGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UserGroupQueryService extends QueryService<UserGroup> {

    private final Logger log = LoggerFactory.getLogger(UserGroupQueryService.class);


    private final UserGroupRepository userGroupRepository;

    private final UserGroupMapper userGroupMapper;

    private final UserGroupSearchRepository userGroupSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public UserGroupQueryService(UserGroupRepository userGroupRepository, UserGroupMapper userGroupMapper, UserGroupSearchRepository userGroupSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupMapper = userGroupMapper;
        this.userGroupSearchRepository = userGroupSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link UserGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserGroupDTO> findByCriteria(UserGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<UserGroup> specification = createSpecification(criteria);
        return userGroupMapper.toDto(userGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UserGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserGroupDTO> findByCriteria(UserGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<UserGroup> specification = createSpecification(criteria);
        final Page<UserGroup> result = userGroupRepository.findAll(specification, page);
        return result.map(userGroupMapper::toDto);
    }

    /**
     * Function to convert UserGroupCriteria to a {@link Specifications}
     */
    private Specifications<UserGroup> createSpecification(UserGroupCriteria criteria) {
        Specifications<UserGroup> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UserGroup_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), UserGroup_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), UserGroup_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), UserGroup_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), UserGroup_.company, Company_.id));
            }
        }
        return specification;
    }

}
