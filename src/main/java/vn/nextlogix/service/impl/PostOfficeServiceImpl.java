package vn.nextlogix.service.impl;

import vn.nextlogix.service.PostOfficeService;
import vn.nextlogix.domain.PostOffice;
import vn.nextlogix.repository.PostOfficeRepository;
import vn.nextlogix.repository.search.PostOfficeSearchRepository;
import vn.nextlogix.service.dto.PostOfficeDTO;
import vn.nextlogix.service.dto.PostOfficeSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Province;
import vn.nextlogix.service.mapper.PostOfficeMapper;
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

    import vn.nextlogix.repository.search.ProvinceSearchRepository;
    import vn.nextlogix.service.mapper.ProvinceMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PostOffice.
 */
@Service
@Transactional
public class PostOfficeServiceImpl implements PostOfficeService {

    private final Logger log = LoggerFactory.getLogger(PostOfficeServiceImpl.class);

    private final PostOfficeRepository postOfficeRepository;

    private final PostOfficeMapper postOfficeMapper;

    private final PostOfficeSearchRepository postOfficeSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;


    public PostOfficeServiceImpl(PostOfficeRepository postOfficeRepository, PostOfficeMapper postOfficeMapper, PostOfficeSearchRepository postOfficeSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
) {
        this.postOfficeRepository = postOfficeRepository;
        this.postOfficeMapper = postOfficeMapper;
        this.postOfficeSearchRepository = postOfficeSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;

    }

    /**
     * Save a postOffice.
     *
     * @param postOfficeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PostOfficeDTO save(PostOfficeDTO postOfficeDTO) {
        log.debug("Request to save PostOffice : {}", postOfficeDTO);
        PostOffice postOffice = postOfficeMapper.toEntity(postOfficeDTO);
        postOffice = postOfficeRepository.save(postOffice);
        PostOfficeDTO result = postOfficeMapper.toDto(postOffice);
        postOfficeSearchRepository.save(postOffice);
        return result;
    }

    /**
     * Get all the postOffices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PostOffices");
        return postOfficeRepository.findAll(pageable)
            .map(postOfficeMapper::toDto);
    }

    /**
     * Get one postOffice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PostOfficeDTO findOne(Long id) {
        log.debug("Request to get PostOffice : {}", id);
        PostOffice postOffice = postOfficeRepository.findOne(id);
        return postOfficeMapper.toDto(postOffice);
    }

    /**
     * Delete the postOffice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PostOffice : {}", id);
        postOfficeRepository.delete(id);
        postOfficeSearchRepository.delete(id);
    }

    /**
     * Search for the postOffice corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostOfficeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PostOffices for query {}", query);
        Page<PostOffice> result = postOfficeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(postOfficeMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<PostOfficeDTO> searchExample(PostOfficeSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<PostOffice> postOfficePage= postOfficeSearchRepository.search(query);
            List<PostOfficeDTO> postOfficeList =  StreamSupport
            .stream(postOfficePage.spliterator(), false)
            .map(postOfficeMapper::toDto)
            .collect(Collectors.toList());
            postOfficeList.forEach(postOfficeDto -> {
            if(postOfficeDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(postOfficeDto.getCompanyId());
                postOfficeDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(postOfficeDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(postOfficeDto.getProvinceId());
                postOfficeDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            });
            return new PageImpl<>(postOfficeList,pageable,postOfficePage.getTotalElements());
        }
}
