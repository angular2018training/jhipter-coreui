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

import vn.nextlogix.domain.OrderConsignee;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderConsigneeRepository;
import vn.nextlogix.repository.search.OrderConsigneeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

import vn.nextlogix.service.dto.OrderConsigneeCriteria;

import vn.nextlogix.service.dto.OrderConsigneeDTO;
import vn.nextlogix.service.mapper.OrderConsigneeMapper;

/**
 * Service for executing complex queries for OrderConsignee entities in the database.
 * The main input is a {@link OrderConsigneeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderConsigneeDTO} or a {@link Page} of {@link OrderConsigneeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderConsigneeQueryService extends QueryService<OrderConsignee> {

    private final Logger log = LoggerFactory.getLogger(OrderConsigneeQueryService.class);


    private final OrderConsigneeRepository orderConsigneeRepository;

    private final OrderConsigneeMapper orderConsigneeMapper;

    private final OrderConsigneeSearchRepository orderConsigneeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;


    public OrderConsigneeQueryService(OrderConsigneeRepository orderConsigneeRepository, OrderConsigneeMapper orderConsigneeMapper, OrderConsigneeSearchRepository orderConsigneeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
) {
        this.orderConsigneeRepository = orderConsigneeRepository;
        this.orderConsigneeMapper = orderConsigneeMapper;
        this.orderConsigneeSearchRepository = orderConsigneeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;

    }

    /**
     * Return a {@link List} of {@link OrderConsigneeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderConsigneeDTO> findByCriteria(OrderConsigneeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderConsignee> specification = createSpecification(criteria);
        return orderConsigneeMapper.toDto(orderConsigneeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderConsigneeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderConsigneeDTO> findByCriteria(OrderConsigneeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderConsignee> specification = createSpecification(criteria);
        final Page<OrderConsignee> result = orderConsigneeRepository.findAll(specification, page);
        return result.map(orderConsigneeMapper::toDto);
    }

    /**
     * Function to convert OrderConsigneeCriteria to a {@link Specifications}
     */
    private Specifications<OrderConsignee> createSpecification(OrderConsigneeCriteria criteria) {
        Specifications<OrderConsignee> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderConsignee_.id));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), OrderConsignee_.address));
            }
            if (criteria.getConsigneePhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConsigneePhone(), OrderConsignee_.consigneePhone));
            }
            if (criteria.getConsigneeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConsigneeName(), OrderConsignee_.consigneeName));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderConsignee_.company, Company_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), OrderConsignee_.district, District_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), OrderConsignee_.province, Province_.id));
            }
        }
        return specification;
    }

}
