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

import vn.nextlogix.domain.DetailForm;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.DetailFormRepository;
import vn.nextlogix.repository.search.DetailFormSearchRepository;

    import vn.nextlogix.repository.search.MasterFormSearchRepository;
    import vn.nextlogix.service.mapper.MasterFormMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

import vn.nextlogix.service.dto.DetailFormCriteria;

import vn.nextlogix.service.dto.DetailFormDTO;
import vn.nextlogix.service.mapper.DetailFormMapper;

/**
 * Service for executing complex queries for DetailForm entities in the database.
 * The main input is a {@link DetailFormCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DetailFormDTO} or a {@link Page} of {@link DetailFormDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DetailFormQueryService extends QueryService<DetailForm> {

    private final Logger log = LoggerFactory.getLogger(DetailFormQueryService.class);


    private final DetailFormRepository detailFormRepository;

    private final DetailFormMapper detailFormMapper;

    private final DetailFormSearchRepository detailFormSearchRepository;


        private final MasterFormSearchRepository masterFormSearchRepository;
        private final MasterFormMapper masterFormMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public DetailFormQueryService(DetailFormRepository detailFormRepository, DetailFormMapper detailFormMapper, DetailFormSearchRepository detailFormSearchRepository     ,MasterFormSearchRepository masterFormSearchRepository,MasterFormMapper  masterFormMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.detailFormRepository = detailFormRepository;
        this.detailFormMapper = detailFormMapper;
        this.detailFormSearchRepository = detailFormSearchRepository;
                                    this.masterFormSearchRepository = masterFormSearchRepository;
                                     this.masterFormMapper = masterFormMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Return a {@link List} of {@link DetailFormDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DetailFormDTO> findByCriteria(DetailFormCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<DetailForm> specification = createSpecification(criteria);
        return detailFormMapper.toDto(detailFormRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DetailFormDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DetailFormDTO> findByCriteria(DetailFormCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<DetailForm> specification = createSpecification(criteria);
        final Page<DetailForm> result = detailFormRepository.findAll(specification, page);
        return result.map(detailFormMapper::toDto);
    }

    /**
     * Function to convert DetailFormCriteria to a {@link Specifications}
     */
    private Specifications<DetailForm> createSpecification(DetailFormCriteria criteria) {
        Specifications<DetailForm> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DetailForm_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), DetailForm_.description));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), DetailForm_.createDate));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), DetailForm_.isActive));
            }
            if (criteria.getMasterFormId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMasterFormId(), DetailForm_.masterForm, MasterForm_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), DetailForm_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), DetailForm_.district, District_.id));
            }
        }
        return specification;
    }

}
