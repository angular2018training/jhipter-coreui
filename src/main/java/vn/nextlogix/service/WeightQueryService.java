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

import vn.nextlogix.domain.Weight;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.WeightRepository;
import vn.nextlogix.repository.search.WeightSearchRepository;

import vn.nextlogix.service.dto.WeightCriteria;

import vn.nextlogix.service.dto.WeightDTO;
import vn.nextlogix.service.mapper.WeightMapper;

/**
 * Service for executing complex queries for Weight entities in the database.
 * The main input is a {@link WeightCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WeightDTO} or a {@link Page} of {@link WeightDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WeightQueryService extends QueryService<Weight> {

    private final Logger log = LoggerFactory.getLogger(WeightQueryService.class);


    private final WeightRepository weightRepository;

    private final WeightMapper weightMapper;

    private final WeightSearchRepository weightSearchRepository;



    public WeightQueryService(WeightRepository weightRepository, WeightMapper weightMapper, WeightSearchRepository weightSearchRepository     ) {
        this.weightRepository = weightRepository;
        this.weightMapper = weightMapper;
        this.weightSearchRepository = weightSearchRepository;

    }

    /**
     * Return a {@link List} of {@link WeightDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WeightDTO> findByCriteria(WeightCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Weight> specification = createSpecification(criteria);
        return weightMapper.toDto(weightRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WeightDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WeightDTO> findByCriteria(WeightCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Weight> specification = createSpecification(criteria);
        final Page<Weight> result = weightRepository.findAll(specification, page);
        return result.map(weightMapper::toDto);
    }

    /**
     * Function to convert WeightCriteria to a {@link Specifications}
     */
    private Specifications<Weight> createSpecification(WeightCriteria criteria) {
        Specifications<Weight> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Weight_.id));
            }
            if (criteria.getMinAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMinAmount(), Weight_.minAmount));
            }
            if (criteria.getMaxAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxAmount(), Weight_.maxAmount));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Weight_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Weight_.description));
            }
        }
        return specification;
    }

}
