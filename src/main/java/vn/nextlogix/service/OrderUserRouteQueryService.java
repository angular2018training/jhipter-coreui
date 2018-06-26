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

import vn.nextlogix.domain.OrderUserRoute;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderUserRouteRepository;
import vn.nextlogix.repository.search.OrderUserRouteSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.OrderUserRouteTypeSearchRepository;
    import vn.nextlogix.service.mapper.OrderUserRouteTypeMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;

import vn.nextlogix.service.dto.OrderUserRouteCriteria;

import vn.nextlogix.service.dto.OrderUserRouteDTO;
import vn.nextlogix.service.mapper.OrderUserRouteMapper;

/**
 * Service for executing complex queries for OrderUserRoute entities in the database.
 * The main input is a {@link OrderUserRouteCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderUserRouteDTO} or a {@link Page} of {@link OrderUserRouteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderUserRouteQueryService extends QueryService<OrderUserRoute> {

    private final Logger log = LoggerFactory.getLogger(OrderUserRouteQueryService.class);


    private final OrderUserRouteRepository orderUserRouteRepository;

    private final OrderUserRouteMapper orderUserRouteMapper;

    private final OrderUserRouteSearchRepository orderUserRouteSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository;
        private final OrderUserRouteTypeMapper orderUserRouteTypeMapper;


        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public OrderUserRouteQueryService(OrderUserRouteRepository orderUserRouteRepository, OrderUserRouteMapper orderUserRouteMapper, OrderUserRouteSearchRepository orderUserRouteSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository,OrderUserRouteTypeMapper  orderUserRouteTypeMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.orderUserRouteRepository = orderUserRouteRepository;
        this.orderUserRouteMapper = orderUserRouteMapper;
        this.orderUserRouteSearchRepository = orderUserRouteSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.orderUserRouteTypeSearchRepository = orderUserRouteTypeSearchRepository;
                                     this.orderUserRouteTypeMapper = orderUserRouteTypeMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Return a {@link List} of {@link OrderUserRouteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderUserRouteDTO> findByCriteria(OrderUserRouteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderUserRoute> specification = createSpecification(criteria);
        return orderUserRouteMapper.toDto(orderUserRouteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderUserRouteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderUserRouteDTO> findByCriteria(OrderUserRouteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderUserRoute> specification = createSpecification(criteria);
        final Page<OrderUserRoute> result = orderUserRouteRepository.findAll(specification, page);
        return result.map(orderUserRouteMapper::toDto);
    }

    /**
     * Function to convert OrderUserRouteCriteria to a {@link Specifications}
     */
    private Specifications<OrderUserRoute> createSpecification(OrderUserRouteCriteria criteria) {
        Specifications<OrderUserRoute> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderUserRoute_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderUserRoute_.company, Company_.id));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserId(), OrderUserRoute_.user, UserExtraInfo_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), OrderUserRoute_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), OrderUserRoute_.district, District_.id));
            }
            if (criteria.getOrderUserRouteTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOrderUserRouteTypeId(), OrderUserRoute_.orderUserRouteType, OrderUserRouteType_.id));
            }
            if (criteria.getWardId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getWardId(), OrderUserRoute_.ward, District_.id));
            }
            if (criteria.getCustomerId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerId(), OrderUserRoute_.customer, Customer_.id));
            }
        }
        return specification;
    }

}
