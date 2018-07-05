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

import vn.nextlogix.domain.FileUpload;
import vn.nextlogix.domain.*; // for static metamodels
import vn.nextlogix.repository.FileUploadRepository;
import vn.nextlogix.repository.search.FileUploadSearchRepository;

    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

import vn.nextlogix.service.dto.FileUploadCriteria;

import vn.nextlogix.service.dto.FileUploadDTO;
import vn.nextlogix.service.mapper.FileUploadMapper;

/**
 * Service for executing complex queries for FileUpload entities in the database.
 * The main input is a {@link FileUploadCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FileUploadDTO} or a {@link Page} of {@link FileUploadDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FileUploadQueryService extends QueryService<FileUpload> {

    private final Logger log = LoggerFactory.getLogger(FileUploadQueryService.class);


    private final FileUploadRepository fileUploadRepository;

    private final FileUploadMapper fileUploadMapper;

    private final FileUploadSearchRepository fileUploadSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public FileUploadQueryService(FileUploadRepository fileUploadRepository, FileUploadMapper fileUploadMapper, FileUploadSearchRepository fileUploadSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadMapper = fileUploadMapper;
        this.fileUploadSearchRepository = fileUploadSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Return a {@link List} of {@link FileUploadDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FileUploadDTO> findByCriteria(FileUploadCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FileUpload> specification = createSpecification(criteria);
        return fileUploadMapper.toDto(fileUploadRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FileUploadDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FileUploadDTO> findByCriteria(FileUploadCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FileUpload> specification = createSpecification(criteria);
        final Page<FileUpload> result = fileUploadRepository.findAll(specification, page);
        return result.map(fileUploadMapper::toDto);
    }

    /**
     * Function to convert FileUploadCriteria to a {@link Specifications}
     */
    private Specifications<FileUpload> createSpecification(FileUploadCriteria criteria) {
        Specifications<FileUpload> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FileUpload_.id));
            }
            if (criteria.getHashedId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHashedId(), FileUpload_.hashedId));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), FileUpload_.name));
            }
            if (criteria.getUploadTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUploadTime(), FileUpload_.uploadTime));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContentType(), FileUpload_.contentType));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), FileUpload_.company, Company_.id));
            }
        }
        return specification;
    }

}
