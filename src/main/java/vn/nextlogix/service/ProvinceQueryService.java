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

import vn.nextlogix.domain.Province;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.ProvinceRepository;
import vn.nextlogix.repository.search.ProvinceSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.ProvinceCriteria;

import vn.nextlogix.service.dto.ProvinceDTO;
import vn.nextlogix.service.mapper.ProvinceMapper;

/**
 * Service for executing complex queries for Province entities in the database.
 * The main input is a {@link ProvinceCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProvinceDTO} or a {@link Page} of {@link ProvinceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProvinceQueryService extends QueryService<Province> {

    private final Logger log = LoggerFactory.getLogger(ProvinceQueryService.class);


    private final ProvinceRepository provinceRepository;

    private final ProvinceMapper provinceMapper;

    private final ProvinceSearchRepository provinceSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public ProvinceQueryService(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper, ProvinceSearchRepository provinceSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
        this.provinceSearchRepository = provinceSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link ProvinceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProvinceDTO> findByCriteria(ProvinceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Province> specification = createSpecification(criteria);
        return provinceMapper.toDto(provinceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProvinceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProvinceDTO> findByCriteria(ProvinceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Province> specification = createSpecification(criteria);
        final Page<Province> result = provinceRepository.findAll(specification, page);
        return result.map(provinceMapper::toDto);
    }

    /**
     * Function to convert ProvinceCriteria to a {@link Specifications}
     */
    private Specifications<Province> createSpecification(ProvinceCriteria criteria) {
        Specifications<Province> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Province_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Province_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Province_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Province_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Province_.company, Company_.id));
            }
        }
        return specification;
    }

}
