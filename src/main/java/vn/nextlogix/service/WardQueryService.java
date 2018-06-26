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

import vn.nextlogix.domain.Ward;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.WardRepository;
import vn.nextlogix.repository.search.WardSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

import vn.nextlogix.service.dto.WardCriteria;

import vn.nextlogix.service.dto.WardDTO;
import vn.nextlogix.service.mapper.WardMapper;

/**
 * Service for executing complex queries for Ward entities in the database.
 * The main input is a {@link WardCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WardDTO} or a {@link Page} of {@link WardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WardQueryService extends QueryService<Ward> {

    private final Logger log = LoggerFactory.getLogger(WardQueryService.class);


    private final WardRepository wardRepository;

    private final WardMapper wardMapper;

    private final WardSearchRepository wardSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public WardQueryService(WardRepository wardRepository, WardMapper wardMapper, WardSearchRepository wardSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.wardRepository = wardRepository;
        this.wardMapper = wardMapper;
        this.wardSearchRepository = wardSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Return a {@link List} of {@link WardDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WardDTO> findByCriteria(WardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Ward> specification = createSpecification(criteria);
        return wardMapper.toDto(wardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WardDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WardDTO> findByCriteria(WardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Ward> specification = createSpecification(criteria);
        final Page<Ward> result = wardRepository.findAll(specification, page);
        return result.map(wardMapper::toDto);
    }

    /**
     * Function to convert WardCriteria to a {@link Specifications}
     */
    private Specifications<Ward> createSpecification(WardCriteria criteria) {
        Specifications<Ward> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Ward_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Ward_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Ward_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Ward_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Ward_.company, Company_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), Ward_.district, District_.id));
            }
        }
        return specification;
    }

}
