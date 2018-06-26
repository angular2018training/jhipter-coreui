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

import vn.nextlogix.domain.Bank;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.BankRepository;
import vn.nextlogix.repository.search.BankSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.BankCriteria;

import vn.nextlogix.service.dto.BankDTO;
import vn.nextlogix.service.mapper.BankMapper;

/**
 * Service for executing complex queries for Bank entities in the database.
 * The main input is a {@link BankCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BankDTO} or a {@link Page} of {@link BankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BankQueryService extends QueryService<Bank> {

    private final Logger log = LoggerFactory.getLogger(BankQueryService.class);


    private final BankRepository bankRepository;

    private final BankMapper bankMapper;

    private final BankSearchRepository bankSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public BankQueryService(BankRepository bankRepository, BankMapper bankMapper, BankSearchRepository bankSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.bankSearchRepository = bankSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link BankDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BankDTO> findByCriteria(BankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Bank> specification = createSpecification(criteria);
        return bankMapper.toDto(bankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BankDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BankDTO> findByCriteria(BankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Bank> specification = createSpecification(criteria);
        final Page<Bank> result = bankRepository.findAll(specification, page);
        return result.map(bankMapper::toDto);
    }

    /**
     * Function to convert BankCriteria to a {@link Specifications}
     */
    private Specifications<Bank> createSpecification(BankCriteria criteria) {
        Specifications<Bank> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Bank_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Bank_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Bank_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Bank_.description));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Bank_.company, Company_.id));
            }
        }
        return specification;
    }

}
