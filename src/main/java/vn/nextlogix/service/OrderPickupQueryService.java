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

import vn.nextlogix.domain.OrderPickup;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.OrderPickupRepository;
import vn.nextlogix.repository.search.OrderPickupSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.WardSearchRepository;
    import vn.nextlogix.service.mapper.WardMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

import vn.nextlogix.service.dto.OrderPickupCriteria;

import vn.nextlogix.service.dto.OrderPickupDTO;
import vn.nextlogix.service.mapper.OrderPickupMapper;

/**
 * Service for executing complex queries for OrderPickup entities in the database.
 * The main input is a {@link OrderPickupCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderPickupDTO} or a {@link Page} of {@link OrderPickupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderPickupQueryService extends QueryService<OrderPickup> {

    private final Logger log = LoggerFactory.getLogger(OrderPickupQueryService.class);


    private final OrderPickupRepository orderPickupRepository;

    private final OrderPickupMapper orderPickupMapper;

    private final OrderPickupSearchRepository orderPickupSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final WardSearchRepository wardSearchRepository;
        private final WardMapper wardMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;


    public OrderPickupQueryService(OrderPickupRepository orderPickupRepository, OrderPickupMapper orderPickupMapper, OrderPickupSearchRepository orderPickupSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,WardSearchRepository wardSearchRepository,WardMapper  wardMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
) {
        this.orderPickupRepository = orderPickupRepository;
        this.orderPickupMapper = orderPickupMapper;
        this.orderPickupSearchRepository = orderPickupSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.wardSearchRepository = wardSearchRepository;
                                     this.wardMapper = wardMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;

    }

    /**
     * Return a {@link List} of {@link OrderPickupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderPickupDTO> findByCriteria(OrderPickupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrderPickup> specification = createSpecification(criteria);
        return orderPickupMapper.toDto(orderPickupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderPickupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderPickupDTO> findByCriteria(OrderPickupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrderPickup> specification = createSpecification(criteria);
        final Page<OrderPickup> result = orderPickupRepository.findAll(specification, page);
        return result.map(orderPickupMapper::toDto);
    }

    /**
     * Function to convert OrderPickupCriteria to a {@link Specifications}
     */
    private Specifications<OrderPickup> createSpecification(OrderPickupCriteria criteria) {
        Specifications<OrderPickup> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderPickup_.id));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), OrderPickup_.address));
            }
            if (criteria.getContactPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactPhone(), OrderPickup_.contactPhone));
            }
            if (criteria.getContactName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactName(), OrderPickup_.contactName));
            }
            if (criteria.getAssignDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAssignDate(), OrderPickup_.assignDate));
            }
            if (criteria.getPickupDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPickupDate(), OrderPickup_.pickupDate));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), OrderPickup_.company, Company_.id));
            }
            if (criteria.getWardId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getWardId(), OrderPickup_.ward, Ward_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), OrderPickup_.district, District_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), OrderPickup_.province, Province_.id));
            }
            if (criteria.getPickupUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickupUserId(), OrderPickup_.pickupUser, UserExtraInfo_.id));
            }
        }
        return specification;
    }

}
