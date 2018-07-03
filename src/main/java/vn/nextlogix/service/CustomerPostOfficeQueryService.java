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

import vn.nextlogix.domain.CustomerPostOffice;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerPostOfficeRepository;
import vn.nextlogix.repository.search.CustomerPostOfficeSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.PostOfficeSearchRepository;
    import vn.nextlogix.service.mapper.PostOfficeMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;

import vn.nextlogix.service.dto.CustomerPostOfficeCriteria;

import vn.nextlogix.service.dto.CustomerPostOfficeDTO;
import vn.nextlogix.service.mapper.CustomerPostOfficeMapper;

/**
 * Service for executing complex queries for CustomerPostOffice entities in the database.
 * The main input is a {@link CustomerPostOfficeCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerPostOfficeDTO} or a {@link Page} of {@link CustomerPostOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerPostOfficeQueryService extends QueryService<CustomerPostOffice> {

    private final Logger log = LoggerFactory.getLogger(CustomerPostOfficeQueryService.class);


    private final CustomerPostOfficeRepository customerPostOfficeRepository;

    private final CustomerPostOfficeMapper customerPostOfficeMapper;

    private final CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final PostOfficeSearchRepository postOfficeSearchRepository;
        private final PostOfficeMapper postOfficeMapper;

        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public CustomerPostOfficeQueryService(CustomerPostOfficeRepository customerPostOfficeRepository, CustomerPostOfficeMapper customerPostOfficeMapper, CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,PostOfficeSearchRepository postOfficeSearchRepository,PostOfficeMapper  postOfficeMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.customerPostOfficeRepository = customerPostOfficeRepository;
        this.customerPostOfficeMapper = customerPostOfficeMapper;
        this.customerPostOfficeSearchRepository = customerPostOfficeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.postOfficeSearchRepository = postOfficeSearchRepository;
                                     this.postOfficeMapper = postOfficeMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerPostOfficeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerPostOfficeDTO> findByCriteria(CustomerPostOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerPostOffice> specification = createSpecification(criteria);
        return customerPostOfficeMapper.toDto(customerPostOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerPostOfficeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerPostOfficeDTO> findByCriteria(CustomerPostOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerPostOffice> specification = createSpecification(criteria);
        final Page<CustomerPostOffice> result = customerPostOfficeRepository.findAll(specification, page);
        return result.map(customerPostOfficeMapper::toDto);
    }

    /**
     * Function to convert CustomerPostOfficeCriteria to a {@link Specifications}
     */
    private Specifications<CustomerPostOffice> createSpecification(CustomerPostOfficeCriteria criteria) {
        Specifications<CustomerPostOffice> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerPostOffice_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CustomerPostOffice_.code));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), CustomerPostOffice_.isActive));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), CustomerPostOffice_.createDate));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerPostOffice_.company, Company_.id));
            }
            if (criteria.getPostOfficeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPostOfficeId(), CustomerPostOffice_.postOffice, PostOffice_.id));
            }
            if (criteria.getCustomerParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerParentId(), CustomerPostOffice_.customerParent, Customer_.id));
            }
        }
        return specification;
    }

}
