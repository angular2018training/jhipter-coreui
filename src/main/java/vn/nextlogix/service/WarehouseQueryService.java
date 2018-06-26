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

import vn.nextlogix.domain.Warehouse;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.WarehouseRepository;
import vn.nextlogix.repository.search.WarehouseSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.WardSearchRepository;
    import vn.nextlogix.service.mapper.WardMapper;

import vn.nextlogix.service.dto.WarehouseCriteria;

import vn.nextlogix.service.dto.WarehouseDTO;
import vn.nextlogix.service.mapper.WarehouseMapper;

/**
 * Service for executing complex queries for Warehouse entities in the database.
 * The main input is a {@link WarehouseCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WarehouseDTO} or a {@link Page} of {@link WarehouseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WarehouseQueryService extends QueryService<Warehouse> {

    private final Logger log = LoggerFactory.getLogger(WarehouseQueryService.class);


    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    private final WarehouseSearchRepository warehouseSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final WardSearchRepository wardSearchRepository;
        private final WardMapper wardMapper;


    public WarehouseQueryService(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper, WarehouseSearchRepository warehouseSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,WardSearchRepository wardSearchRepository,WardMapper  wardMapper
) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
        this.warehouseSearchRepository = warehouseSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.wardSearchRepository = wardSearchRepository;
                                     this.wardMapper = wardMapper;

    }

    /**
     * Return a {@link List} of {@link WarehouseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WarehouseDTO> findByCriteria(WarehouseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Warehouse> specification = createSpecification(criteria);
        return warehouseMapper.toDto(warehouseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WarehouseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WarehouseDTO> findByCriteria(WarehouseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Warehouse> specification = createSpecification(criteria);
        final Page<Warehouse> result = warehouseRepository.findAll(specification, page);
        return result.map(warehouseMapper::toDto);
    }

    /**
     * Function to convert WarehouseCriteria to a {@link Specifications}
     */
    private Specifications<Warehouse> createSpecification(WarehouseCriteria criteria) {
        Specifications<Warehouse> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Warehouse_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Warehouse_.name));
            }
            if (criteria.getContactName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactName(), Warehouse_.contactName));
            }
            if (criteria.getContactPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactPhone(), Warehouse_.contactPhone));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Warehouse_.address));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Warehouse_.createDate));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Warehouse_.company, Company_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), Warehouse_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), Warehouse_.district, District_.id));
            }
            if (criteria.getWardId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getWardId(), Warehouse_.ward, Ward_.id));
            }
        }
        return specification;
    }

}
