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

import vn.nextlogix.domain.MasterForm;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.MasterFormRepository;
import vn.nextlogix.repository.search.MasterFormSearchRepository;


    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

import vn.nextlogix.service.dto.MasterFormCriteria;

import vn.nextlogix.service.dto.MasterFormDTO;
import vn.nextlogix.service.mapper.MasterFormMapper;

/**
 * Service for executing complex queries for MasterForm entities in the database.
 * The main input is a {@link MasterFormCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MasterFormDTO} or a {@link Page} of {@link MasterFormDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MasterFormQueryService extends QueryService<MasterForm> {

    private final Logger log = LoggerFactory.getLogger(MasterFormQueryService.class);


    private final MasterFormRepository masterFormRepository;

    private final MasterFormMapper masterFormMapper;

    private final MasterFormSearchRepository masterFormSearchRepository;



        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;


    public MasterFormQueryService(MasterFormRepository masterFormRepository, MasterFormMapper masterFormMapper, MasterFormSearchRepository masterFormSearchRepository     ,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.masterFormRepository = masterFormRepository;
        this.masterFormMapper = masterFormMapper;
        this.masterFormSearchRepository = masterFormSearchRepository;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Return a {@link List} of {@link MasterFormDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MasterFormDTO> findByCriteria(MasterFormCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<MasterForm> specification = createSpecification(criteria);
        return masterFormMapper.toDto(masterFormRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MasterFormDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MasterFormDTO> findByCriteria(MasterFormCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<MasterForm> specification = createSpecification(criteria);
        final Page<MasterForm> result = masterFormRepository.findAll(specification, page);
        return result.map(masterFormMapper::toDto);
    }

    /**
     * Function to convert MasterFormCriteria to a {@link Specifications}
     */
    private Specifications<MasterForm> createSpecification(MasterFormCriteria criteria) {
        Specifications<MasterForm> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MasterForm_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MasterForm_.code));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), MasterForm_.note));
            }
            if (criteria.getReceiveTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReceiveTime(), MasterForm_.receiveTime));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), MasterForm_.isActive));
            }
            if (criteria.getDetailFormDetailListId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDetailFormDetailListId(), MasterForm_.detailFormDetailLists, DetailForm_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), MasterForm_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), MasterForm_.district, District_.id));
            }
        }
        return specification;
    }

}
