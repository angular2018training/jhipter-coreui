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

import vn.nextlogix.domain.CustomerLegal;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerLegalRepository;
import vn.nextlogix.repository.search.CustomerLegalSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;


import vn.nextlogix.service.dto.CustomerLegalCriteria;

import vn.nextlogix.service.dto.CustomerLegalDTO;
import vn.nextlogix.service.mapper.CustomerLegalMapper;

/**
 * Service for executing complex queries for CustomerLegal entities in the database.
 * The main input is a {@link CustomerLegalCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerLegalDTO} or a {@link Page} of {@link CustomerLegalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerLegalQueryService extends QueryService<CustomerLegal> {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalQueryService.class);


    private final CustomerLegalRepository customerLegalRepository;

    private final CustomerLegalMapper customerLegalMapper;

    private final CustomerLegalSearchRepository customerLegalSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;



    public CustomerLegalQueryService(CustomerLegalRepository customerLegalRepository, CustomerLegalMapper customerLegalMapper, CustomerLegalSearchRepository customerLegalSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
) {
        this.customerLegalRepository = customerLegalRepository;
        this.customerLegalMapper = customerLegalMapper;
        this.customerLegalSearchRepository = customerLegalSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerLegalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerLegalDTO> findByCriteria(CustomerLegalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerLegal> specification = createSpecification(criteria);
        return customerLegalMapper.toDto(customerLegalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerLegalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerLegalDTO> findByCriteria(CustomerLegalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerLegal> specification = createSpecification(criteria);
        final Page<CustomerLegal> result = customerLegalRepository.findAll(specification, page);
        return result.map(customerLegalMapper::toDto);
    }

    /**
     * Function to convert CustomerLegalCriteria to a {@link Specifications}
     */
    private Specifications<CustomerLegal> createSpecification(CustomerLegalCriteria criteria) {
        Specifications<CustomerLegal> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerLegal_.id));
            }
            if (criteria.getContractCustomerName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractCustomerName(), CustomerLegal_.contractCustomerName));
            }
            if (criteria.getContractAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractAddress(), CustomerLegal_.contractAddress));
            }
            if (criteria.getContractContactName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractContactName(), CustomerLegal_.contractContactName));
            }
            if (criteria.getContractContactPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractContactPhone(), CustomerLegal_.contractContactPhone));
            }
            if (criteria.getTaxCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTaxCode(), CustomerLegal_.taxCode));
            }
            if (criteria.getContractExpirationDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractExpirationDate(), CustomerLegal_.contractExpirationDate));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerLegal_.company, Company_.id));
            }
            if (criteria.getProvinceId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProvinceId(), CustomerLegal_.province, Province_.id));
            }
            if (criteria.getDistrictId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDistrictId(), CustomerLegal_.district, District_.id));
            }
            if (criteria.getFileUploadId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFileUploadId(), CustomerLegal_.fileUploads, FileUpload_.id));
            }
        }
        return specification;
    }

}
