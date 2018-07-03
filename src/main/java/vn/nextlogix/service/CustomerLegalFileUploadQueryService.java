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

import vn.nextlogix.domain.CustomerLegalFileUpload;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerLegalFileUploadRepository;
import vn.nextlogix.repository.search.CustomerLegalFileUploadSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.FileUploadSearchRepository;
    import vn.nextlogix.service.mapper.FileUploadMapper;

    import vn.nextlogix.repository.search.CustomerLegalSearchRepository;
    import vn.nextlogix.service.mapper.CustomerLegalMapper;

import vn.nextlogix.service.dto.CustomerLegalFileUploadCriteria;

import vn.nextlogix.service.dto.CustomerLegalFileUploadDTO;
import vn.nextlogix.service.mapper.CustomerLegalFileUploadMapper;

/**
 * Service for executing complex queries for CustomerLegalFileUpload entities in the database.
 * The main input is a {@link CustomerLegalFileUploadCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerLegalFileUploadDTO} or a {@link Page} of {@link CustomerLegalFileUploadDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerLegalFileUploadQueryService extends QueryService<CustomerLegalFileUpload> {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalFileUploadQueryService.class);


    private final CustomerLegalFileUploadRepository customerLegalFileUploadRepository;

    private final CustomerLegalFileUploadMapper customerLegalFileUploadMapper;

    private final CustomerLegalFileUploadSearchRepository customerLegalFileUploadSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final FileUploadSearchRepository fileUploadSearchRepository;
        private final FileUploadMapper fileUploadMapper;

        private final CustomerLegalSearchRepository customerLegalSearchRepository;
        private final CustomerLegalMapper customerLegalMapper;


    public CustomerLegalFileUploadQueryService(CustomerLegalFileUploadRepository customerLegalFileUploadRepository, CustomerLegalFileUploadMapper customerLegalFileUploadMapper, CustomerLegalFileUploadSearchRepository customerLegalFileUploadSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,FileUploadSearchRepository fileUploadSearchRepository,FileUploadMapper  fileUploadMapper
,CustomerLegalSearchRepository customerLegalSearchRepository,CustomerLegalMapper  customerLegalMapper
) {
        this.customerLegalFileUploadRepository = customerLegalFileUploadRepository;
        this.customerLegalFileUploadMapper = customerLegalFileUploadMapper;
        this.customerLegalFileUploadSearchRepository = customerLegalFileUploadSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.fileUploadSearchRepository = fileUploadSearchRepository;
                                     this.fileUploadMapper = fileUploadMapper;
                                    this.customerLegalSearchRepository = customerLegalSearchRepository;
                                     this.customerLegalMapper = customerLegalMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerLegalFileUploadDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerLegalFileUploadDTO> findByCriteria(CustomerLegalFileUploadCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerLegalFileUpload> specification = createSpecification(criteria);
        return customerLegalFileUploadMapper.toDto(customerLegalFileUploadRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerLegalFileUploadDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerLegalFileUploadDTO> findByCriteria(CustomerLegalFileUploadCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerLegalFileUpload> specification = createSpecification(criteria);
        final Page<CustomerLegalFileUpload> result = customerLegalFileUploadRepository.findAll(specification, page);
        return result.map(customerLegalFileUploadMapper::toDto);
    }

    /**
     * Function to convert CustomerLegalFileUploadCriteria to a {@link Specifications}
     */
    private Specifications<CustomerLegalFileUpload> createSpecification(CustomerLegalFileUploadCriteria criteria) {
        Specifications<CustomerLegalFileUpload> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerLegalFileUpload_.id));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerLegalFileUpload_.company, Company_.id));
            }
            if (criteria.getFileUploadId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFileUploadId(), CustomerLegalFileUpload_.fileUpload, FileUpload_.id));
            }
            if (criteria.getCustomerLegalParentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCustomerLegalParentId(), CustomerLegalFileUpload_.customerLegalParent, CustomerLegal_.id));
            }
        }
        return specification;
    }

}
