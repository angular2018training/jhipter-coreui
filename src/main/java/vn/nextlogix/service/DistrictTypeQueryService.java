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

import vn.nextlogix.domain.DistrictType;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.DistrictTypeRepository;
import vn.nextlogix.repository.search.DistrictTypeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.DistrictTypeCriteria;

import vn.nextlogix.service.dto.DistrictTypeDTO;
import vn.nextlogix.service.mapper.DistrictTypeMapper;

/**
 * Service for executing complex queries for DistrictType entities in the database.
 * The main input is a {@link DistrictTypeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DistrictTypeDTO} or a {@link Page} of {@link DistrictTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DistrictTypeQueryService extends QueryService<DistrictType> {

    private final Logger log = LoggerFactory.getLogger(DistrictTypeQueryService.class);


    private final DistrictTypeRepository districtTypeRepository;

    private final DistrictTypeMapper districtTypeMapper;

    private final DistrictTypeSearchRepository districtTypeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public DistrictTypeQueryService(DistrictTypeRepository districtTypeRepository, DistrictTypeMapper districtTypeMapper, DistrictTypeSearchRepository districtTypeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.districtTypeRepository = districtTypeRepository;
        this.districtTypeMapper = districtTypeMapper;
        this.districtTypeSearchRepository = districtTypeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link DistrictTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DistrictTypeDTO> findByCriteria(DistrictTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<DistrictType> specification = createSpecification(criteria);
        return districtTypeMapper.toDto(districtTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DistrictTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DistrictTypeDTO> findByCriteria(DistrictTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<DistrictType> specification = createSpecification(criteria);
        final Page<DistrictType> result = districtTypeRepository.findAll(specification, page);
        return result.map(districtTypeMapper::toDto);
    }

    /**
     * Function to convert DistrictTypeCriteria to a {@link Specifications}
     */
    private Specifications<DistrictType> createSpecification(DistrictTypeCriteria criteria) {
        Specifications<DistrictType> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DistrictType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), DistrictType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DistrictType_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), DistrictType_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), DistrictType_.company, Company_.id));
            }
        }
        return specification;
    }

}
