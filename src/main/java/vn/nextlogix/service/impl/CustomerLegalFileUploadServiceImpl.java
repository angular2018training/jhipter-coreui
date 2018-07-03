package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerLegalFileUploadService;
import vn.nextlogix.domain.CustomerLegalFileUpload;


    import vn.nextlogix.repository.CustomerLegalFileUploadRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.CustomerLegalFileUploadSearchRepository;
import vn.nextlogix.service.dto.CustomerLegalFileUploadDTO;
import vn.nextlogix.service.dto.CustomerLegalFileUploadSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.FileUpload;
    import vn.nextlogix.domain.CustomerLegal;
import vn.nextlogix.service.mapper.CustomerLegalFileUploadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;

    import vn.nextlogix.repository.search.FileUploadSearchRepository;
    import vn.nextlogix.service.mapper.FileUploadMapper;

    import vn.nextlogix.repository.search.CustomerLegalSearchRepository;
    import vn.nextlogix.service.mapper.CustomerLegalMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerLegalFileUpload.
 */
@Service
@Transactional
public class CustomerLegalFileUploadServiceImpl implements CustomerLegalFileUploadService {

    private final Logger log = LoggerFactory.getLogger(CustomerLegalFileUploadServiceImpl.class);

    private final CustomerLegalFileUploadRepository customerLegalFileUploadRepository;

    private final CustomerLegalFileUploadMapper customerLegalFileUploadMapper;

    private final CustomerLegalFileUploadSearchRepository customerLegalFileUploadSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final FileUploadSearchRepository fileUploadSearchRepository;
        private final FileUploadMapper fileUploadMapper;

        private final CustomerLegalSearchRepository customerLegalSearchRepository;
        private final CustomerLegalMapper customerLegalMapper;


    public CustomerLegalFileUploadServiceImpl(CustomerLegalFileUploadRepository customerLegalFileUploadRepository, CustomerLegalFileUploadMapper customerLegalFileUploadMapper, CustomerLegalFileUploadSearchRepository customerLegalFileUploadSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
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
     * Save a customerLegalFileUpload.
     *
     * @param customerLegalFileUploadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerLegalFileUploadDTO save(CustomerLegalFileUploadDTO customerLegalFileUploadDTO) {
        log.debug("Request to save CustomerLegalFileUpload : {}", customerLegalFileUploadDTO);
        CustomerLegalFileUpload customerLegalFileUpload = customerLegalFileUploadMapper.toEntity(customerLegalFileUploadDTO);
        customerLegalFileUpload = customerLegalFileUploadRepository.save(customerLegalFileUpload);
        CustomerLegalFileUploadDTO result = customerLegalFileUploadMapper.toDto(customerLegalFileUpload);
        customerLegalFileUploadSearchRepository.save(customerLegalFileUpload);
        return result;
    }

    /**
     * Get all the customerLegalFileUploads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerLegalFileUploadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerLegalFileUploads");
        return customerLegalFileUploadRepository.findAll(pageable)
            .map(customerLegalFileUploadMapper::toDto);
    }

    /**
     * Get one customerLegalFileUpload by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerLegalFileUploadDTO findOne(Long id) {
        log.debug("Request to get CustomerLegalFileUpload : {}", id);
        CustomerLegalFileUpload customerLegalFileUpload = customerLegalFileUploadRepository.findOne(id);
        return customerLegalFileUploadMapper.toDto(customerLegalFileUpload);
    }

    /**
     * Delete the customerLegalFileUpload by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerLegalFileUpload : {}", id);
        customerLegalFileUploadRepository.delete(id);
        customerLegalFileUploadSearchRepository.delete(id);
    }

    /**
     * Search for the customerLegalFileUpload corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerLegalFileUploadDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerLegalFileUploads for query {}", query);
        Page<CustomerLegalFileUpload> result = customerLegalFileUploadSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerLegalFileUploadMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerLegalFileUploadDTO> searchExample(CustomerLegalFileUploadSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getFileUploadId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("fileUpload.id", searchDto.getFileUploadId()));
            }
            if(searchDto.getCustomerLegalParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customerLegalParent.id", searchDto.getCustomerLegalParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<CustomerLegalFileUpload> customerLegalFileUploadPage= customerLegalFileUploadSearchRepository.search(query);
            List<CustomerLegalFileUploadDTO> customerLegalFileUploadList =  StreamSupport
            .stream(customerLegalFileUploadPage.spliterator(), false)
            .map(customerLegalFileUploadMapper::toDto)
            .collect(Collectors.toList());
            customerLegalFileUploadList.forEach(customerLegalFileUploadDto -> {
            if(customerLegalFileUploadDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerLegalFileUploadDto.getCompanyId());
                customerLegalFileUploadDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerLegalFileUploadDto.getFileUploadId()!=null){
                FileUpload fileUpload= fileUploadSearchRepository.findOne(customerLegalFileUploadDto.getFileUploadId());
                customerLegalFileUploadDto.setFileUploadDTO(fileUploadMapper.toDto(fileUpload));
            }
            if(customerLegalFileUploadDto.getCustomerLegalParentId()!=null){
                CustomerLegal customerLegal= customerLegalSearchRepository.findOne(customerLegalFileUploadDto.getCustomerLegalParentId());
                customerLegalFileUploadDto.setCustomerLegalParentDTO(customerLegalMapper.toDto(customerLegal));
            }
            });
            return new PageImpl<>(customerLegalFileUploadList,pageable,customerLegalFileUploadPage.getTotalElements());
        }
}
