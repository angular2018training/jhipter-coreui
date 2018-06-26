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

import vn.nextlogix.domain.Position;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.PositionRepository;
import vn.nextlogix.repository.search.PositionSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.PositionCriteria;

import vn.nextlogix.service.dto.PositionDTO;
import vn.nextlogix.service.mapper.PositionMapper;

/**
 * Service for executing complex queries for Position entities in the database.
 * The main input is a {@link PositionCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PositionDTO} or a {@link Page} of {@link PositionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PositionQueryService extends QueryService<Position> {

    private final Logger log = LoggerFactory.getLogger(PositionQueryService.class);


    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    private final PositionSearchRepository positionSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public PositionQueryService(PositionRepository positionRepository, PositionMapper positionMapper, PositionSearchRepository positionSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
        this.positionSearchRepository = positionSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link PositionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PositionDTO> findByCriteria(PositionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Position> specification = createSpecification(criteria);
        return positionMapper.toDto(positionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PositionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PositionDTO> findByCriteria(PositionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Position> specification = createSpecification(criteria);
        final Page<Position> result = positionRepository.findAll(specification, page);
        return result.map(positionMapper::toDto);
    }

    /**
     * Function to convert PositionCriteria to a {@link Specifications}
     */
    private Specifications<Position> createSpecification(PositionCriteria criteria) {
        Specifications<Position> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Position_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Position_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Position_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Position_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Position_.company, Company_.id));
            }
        }
        return specification;
    }

}
