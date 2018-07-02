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

import vn.nextlogix.domain.PostOffice;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.PostOfficeRepository;
import vn.nextlogix.repository.search.PostOfficeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

import vn.nextlogix.service.dto.PostOfficeCriteria;

import vn.nextlogix.service.dto.PostOfficeDTO;
import vn.nextlogix.service.mapper.PostOfficeMapper;

/**
 * Service for executing complex queries for PostOffice entities in the database.
 * The main input is a {@link PostOfficeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PostOfficeDTO} or a {@link Page} of {@link PostOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PostOfficeQueryService extends QueryService<PostOffice> {

    private final Logger log = LoggerFactory.getLogger(PostOfficeQueryService.class);


    private final PostOfficeRepository postOfficeRepository;

    private final PostOfficeMapper postOfficeMapper;

    private final PostOfficeSearchRepository postOfficeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public PostOfficeQueryService(PostOfficeRepository postOfficeRepository, PostOfficeMapper postOfficeMapper, PostOfficeSearchRepository postOfficeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.postOfficeRepository = postOfficeRepository;
        this.postOfficeMapper = postOfficeMapper;
        this.postOfficeSearchRepository = postOfficeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Return a {@link List} of {@link PostOfficeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PostOfficeDTO> findByCriteria(PostOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<PostOffice> specification = createSpecification(criteria);
        return postOfficeMapper.toDto(postOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PostOfficeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PostOfficeDTO> findByCriteria(PostOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<PostOffice> specification = createSpecification(criteria);
        final Page<PostOffice> result = postOfficeRepository.findAll(specification, page);
        return result.map(postOfficeMapper::toDto);
    }

    /**
     * Function to convert PostOfficeCriteria to a {@link Specifications}
     */
    private Specifications<PostOffice> createSpecification(PostOfficeCriteria criteria) {
        Specifications<PostOffice> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PostOffice_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), PostOffice_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PostOffice_.name));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), PostOffice_.address));
            }
            if (criteria.getSortOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortOrder(), PostOffice_.sortOrder));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), PostOffice_.phone));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), PostOffice_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), PostOffice_.longitude));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PostOffice_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), PostOffice_.company, Company_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), PostOffice_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), PostOffice_.district, District_.id));
            }
        }
        return specification;
    }

}
