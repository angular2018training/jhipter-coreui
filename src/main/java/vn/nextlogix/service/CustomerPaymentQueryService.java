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

import vn.nextlogix.domain.CustomerPayment;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.CustomerPaymentRepository;
import vn.nextlogix.repository.search.CustomerPaymentSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.BankSearchRepository;
    import vn.nextlogix.service.mapper.BankMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.PaymentTypeSearchRepository;
    import vn.nextlogix.service.mapper.PaymentTypeMapper;

import vn.nextlogix.service.dto.CustomerPaymentCriteria;

import vn.nextlogix.service.dto.CustomerPaymentDTO;
import vn.nextlogix.service.mapper.CustomerPaymentMapper;

/**
 * Service for executing complex queries for CustomerPayment entities in the database.
 * The main input is a {@link CustomerPaymentCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerPaymentDTO} or a {@link Page} of {@link CustomerPaymentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerPaymentQueryService extends QueryService<CustomerPayment> {

    private final Logger log = LoggerFactory.getLogger(CustomerPaymentQueryService.class);


    private final CustomerPaymentRepository customerPaymentRepository;

    private final CustomerPaymentMapper customerPaymentMapper;

    private final CustomerPaymentSearchRepository customerPaymentSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final BankSearchRepository bankSearchRepository;
        private final BankMapper bankMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final PaymentTypeSearchRepository paymentTypeSearchRepository;
        private final PaymentTypeMapper paymentTypeMapper;


    public CustomerPaymentQueryService(CustomerPaymentRepository customerPaymentRepository, CustomerPaymentMapper customerPaymentMapper, CustomerPaymentSearchRepository customerPaymentSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,BankSearchRepository bankSearchRepository,BankMapper  bankMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,PaymentTypeSearchRepository paymentTypeSearchRepository,PaymentTypeMapper  paymentTypeMapper
) {
        this.customerPaymentRepository = customerPaymentRepository;
        this.customerPaymentMapper = customerPaymentMapper;
        this.customerPaymentSearchRepository = customerPaymentSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.bankSearchRepository = bankSearchRepository;
                                     this.bankMapper = bankMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.paymentTypeSearchRepository = paymentTypeSearchRepository;
                                     this.paymentTypeMapper = paymentTypeMapper;

    }

    /**
     * Return a {@link List} of {@link CustomerPaymentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerPaymentDTO> findByCriteria(CustomerPaymentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CustomerPayment> specification = createSpecification(criteria);
        return customerPaymentMapper.toDto(customerPaymentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerPaymentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerPaymentDTO> findByCriteria(CustomerPaymentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CustomerPayment> specification = createSpecification(criteria);
        final Page<CustomerPayment> result = customerPaymentRepository.findAll(specification, page);
        return result.map(customerPaymentMapper::toDto);
    }

    /**
     * Function to convert CustomerPaymentCriteria to a {@link Specifications}
     */
    private Specifications<CustomerPayment> createSpecification(CustomerPaymentCriteria criteria) {
        Specifications<CustomerPayment> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CustomerPayment_.id));
            }
            if (criteria.getBranchName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBranchName(), CustomerPayment_.branchName));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), CustomerPayment_.accountNumber));
            }
            if (criteria.getAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountName(), CustomerPayment_.accountName));
            }
            if (criteria.getCardNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCardNumber(), CustomerPayment_.cardNumber));
            }
            if (criteria.getPaymentAmountMoney() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPaymentAmountMoney(), CustomerPayment_.paymentAmountMoney));
            }
            if (criteria.getIsVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsVerify(), CustomerPayment_.isVerify));
            }
            if (criteria.getDateVerify() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateVerify(), CustomerPayment_.dateVerify));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), CustomerPayment_.company, Company_.id));
            }
            if (criteria.getBankId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBankId(), CustomerPayment_.bank, Bank_.id));
            }
            if (criteria.getUserVerifyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserVerifyId(), CustomerPayment_.userVerify, UserExtraInfo_.id));
            }
            if (criteria.getPaymentTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPaymentTypeId(), CustomerPayment_.paymentType, PaymentType_.id));
            }
        }
        return specification;
    }

}
