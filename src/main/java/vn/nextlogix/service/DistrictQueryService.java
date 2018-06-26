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

import vn.nextlogix.domain.District;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.DistrictRepository;
import vn.nextlogix.repository.search.DistrictSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

import vn.nextlogix.service.dto.DistrictCriteria;

import vn.nextlogix.service.dto.DistrictDTO;
import vn.nextlogix.service.mapper.DistrictMapper;

/**
 * Service for executing complex queries for District entities in the database.
 * The main input is a {@link DistrictCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DistrictDTO} or a {@link Page} of {@link DistrictDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DistrictQueryService extends QueryService<District> {

    private final Logger log = LoggerFactory.getLogger(DistrictQueryService.class);


    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;

    private final DistrictSearchRepository districtSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;


    public DistrictQueryService(DistrictRepository districtRepository, DistrictMapper districtMapper, DistrictSearchRepository districtSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
        this.districtSearchRepository = districtSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;

    }

    /**
     * Return a {@link List} of {@link DistrictDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DistrictDTO> findByCriteria(DistrictCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<District> specification = createSpecification(criteria);
        return districtMapper.toDto(districtRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DistrictDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DistrictDTO> findByCriteria(DistrictCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<District> specification = createSpecification(criteria);
        final Page<District> result = districtRepository.findAll(specification, page);
        return result.map(districtMapper::toDto);
    }

    /**
     * Function to convert DistrictCriteria to a {@link Specifications}
     */
    private Specifications<District> createSpecification(DistrictCriteria criteria) {
        Specifications<District> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), District_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), District_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), District_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), District_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), District_.company, Company_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), District_.province, Province_.id));
            }
        }
        return specification;
    }

}
