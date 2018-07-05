package vn.nextlogix.service.impl;

import vn.nextlogix.service.FileUploadService;
import vn.nextlogix.domain.FileUpload;


    import vn.nextlogix.repository.FileUploadRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.FileUploadSearchRepository;
import vn.nextlogix.service.dto.FileUploadDTO;
import vn.nextlogix.service.dto.FileUploadSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.FileUploadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FileUpload.
 */
@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    private final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private final FileUploadRepository fileUploadRepository;

    private final FileUploadMapper fileUploadMapper;

    private final FileUploadSearchRepository fileUploadSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository, FileUploadMapper fileUploadMapper, FileUploadSearchRepository fileUploadSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadMapper = fileUploadMapper;
        this.fileUploadSearchRepository = fileUploadSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

    }

    /**
     * Save a fileUpload.
     *
     * @param fileUploadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FileUploadDTO save(FileUploadDTO fileUploadDTO) {
        log.debug("Request to save FileUpload : {}", fileUploadDTO);
        FileUpload fileUpload = fileUploadMapper.toEntity(fileUploadDTO);
        if(StringUtils.isBlank(fileUpload.getHashedId())) {
        	fileUpload.setHashedId(UUID.randomUUID().toString());
        }
        fileUpload = fileUploadRepository.save(fileUpload);
        FileUploadDTO result = fileUploadMapper.toDto(fileUpload);
        fileUploadSearchRepository.save(fileUpload);
        return result;
    }

    /**
     * Get all the fileUploads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileUploadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileUploads");
        return fileUploadRepository.findAll(pageable)
            .map(fileUploadMapper::toDto);
    }

    /**
     * Get one fileUpload by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FileUploadDTO findOne(Long id) {
        log.debug("Request to get FileUpload : {}", id);
        FileUpload fileUpload = fileUploadRepository.findOne(id);
        return fileUploadMapper.toDto(fileUpload);
    }

    /**
     * Delete the fileUpload by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileUpload : {}", id);
        fileUploadRepository.delete(id);
        fileUploadSearchRepository.delete(id);
    }

    /**
     * Search for the fileUpload corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileUploadDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FileUploads for query {}", query);
        Page<FileUpload> result = fileUploadSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(fileUploadMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<FileUploadDTO> searchExample(FileUploadSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getHashedId())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("hashedId", "*"+searchDto.getHashedId()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContentType())) {
                 boolQueryBuilder.must(QueryBuilders.wildcardQuery("contentType", "*"+searchDto.getContentType()+"*"));
            }
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<FileUpload> fileUploadPage= fileUploadSearchRepository.search(query);
            List<FileUploadDTO> fileUploadList =  StreamSupport
            .stream(fileUploadPage.spliterator(), false)
            .map(fileUploadMapper::toDto)
            .collect(Collectors.toList());
            fileUploadList.forEach(fileUploadDto -> {
            if(fileUploadDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(fileUploadDto.getCompanyId());
                fileUploadDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(fileUploadList,pageable,fileUploadPage.getTotalElements());
        }

	@Override
	public FileUploadDTO findByHashedId(String hashedId) {
		List<FileUpload> fileUpload = fileUploadRepository.findByHashedId(hashedId);
		if(fileUpload.size()==0) return null;
		else {
			FileUploadDTO fileUploadDTO = fileUploadMapper.toDto(fileUpload.get(0));
			return fileUploadDTO;
		}
	}

	@Override
	public void deleteByHashedId(String hashedId) {
		List<FileUpload> fileUpload = fileUploadRepository.findByHashedId(hashedId);
		if(fileUpload.size()>0)
		fileUploadRepository.delete(fileUpload);
		
	}
}
