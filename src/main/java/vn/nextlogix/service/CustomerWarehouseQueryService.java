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

import vn.nextlogix.domain.CustomerWarehouse;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerWarehouseRepository;
import vn.nextlogix.repository.search.CustomerWarehouseSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.WarehouseSearchRepository;
    import vn.nextlogix.service.mapper.WarehouseMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;

import vn.nextlogix.service.dto.CustomerWarehouseCriteria;

import vn.nextlogix.service.dto.CustomerWarehouseDTO;
import vn.nextlogix.service.mapper.CustomerWarehouseMapper;

/**
 * Service for executing complex queries for CustomerWarehouse entities in the database.
 * The main input is a {@link CustomerWarehouseCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerWarehouseDTO} or a {@link Page} of {@link CustomerWarehouseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerWarehouseQueryService extends QueryService<CustomerWarehouse> {

    private final Logger log = LoggerFactory.getLogger(CustomerWarehouseQueryService.class);


    private final CustomerWarehouseRepository customerWarehouseRepository;

    private final CustomerWarehouseMapper customerWarehouseMapper;

    private final CustomerWarehouseSearchRepository customerWarehouseSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final WarehouseSearchRepository warehouseSearchRepository;
        private final WarehouseMapper warehouseMapper;

        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public CustomerWarehouseQueryService(CustomerWarehouseRepository customerWarehouseRepository, CustomerWarehouseMapper customerWarehouseMapper, CustomerWarehouseSearchRepository customerWarehouseSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,WarehouseSearchRepository warehouseSearchRepository,WarehouseMapper  warehouseMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.customerWarehouseRepository = customerWarehouseRepository;
        this.customerWarehouseMapper = customerWarehouseMapper;
        this.customerWarehouseSearchRepository = customerWarehouseSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.warehouseSearchRepository = warehouseSearchRepository;
                                     this.warehouseMapper = warehouseMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerWarehouseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerWarehouseDTO> findByCriteria(CustomerWarehouseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerWarehouse> specification = createSpecification(criteria);
        return customerWarehouseMapper.toDto(customerWarehouseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerWarehouseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerWarehouseDTO> findByCriteria(CustomerWarehouseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerWarehouse> specification = createSpecification(criteria);
        final Page<CustomerWarehouse> result = customerWarehouseRepository.findAll(specification, page);
        return result.map(customerWarehouseMapper::toDto);
    }

    /**
     * Function to convert CustomerWarehouseCriteria to a {@link Specifications}
     */
    private Specifications<CustomerWarehouse> createSpecification(CustomerWarehouseCriteria criteria) {
        Specifications<CustomerWarehouse> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerWarehouse_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerWarehouse_.company, Company_.id));
            }
            if (criteria.getWarehouseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getWarehouseId(), CustomerWarehouse_.warehouse, Warehouse_.id));
            }
            if (criteria.getCustomerParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerParentId(), CustomerWarehouse_.customerParent, Customer_.id));
            }
        }
        return specification;
    }

}
