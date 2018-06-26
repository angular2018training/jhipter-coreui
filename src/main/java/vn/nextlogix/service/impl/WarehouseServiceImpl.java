package vn.nextlogix.service.impl;

import vn.nextlogix.service.WarehouseService;
import vn.nextlogix.domain.Warehouse;
import vn.nextlogix.repository.WarehouseRepository;
import vn.nextlogix.repository.search.WarehouseSearchRepository;
import vn.nextlogix.service.dto.WarehouseDTO;
import vn.nextlogix.service.dto.WarehouseSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Province;
    import vn.nextlogix.domain.District;
    import vn.nextlogix.domain.Ward;
import vn.nextlogix.service.mapper.WarehouseMapper;
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

    import vn.nextlogix.repository.search.DistrictSearchRepository;
    import vn.nextlogix.service.mapper.DistrictMapper;

    import vn.nextlogix.repository.search.WardSearchRepository;
    import vn.nextlogix.service.mapper.WardMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Warehouse.
 */
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private final Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    private final WarehouseSearchRepository warehouseSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final ProvinceSearchRepository provinceSearchRepository;
        private final ProvinceMapper provinceMapper;

        private final DistrictSearchRepository districtSearchRepository;
        private final DistrictMapper districtMapper;

        private final WardSearchRepository wardSearchRepository;
        private final WardMapper wardMapper;


    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper, WarehouseSearchRepository warehouseSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,ProvinceSearchRepository provinceSearchRepository,ProvinceMapper  provinceMapper
,DistrictSearchRepository districtSearchRepository,DistrictMapper  districtMapper
,WardSearchRepository wardSearchRepository,WardMapper  wardMapper
) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
        this.warehouseSearchRepository = warehouseSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.provinceSearchRepository = provinceSearchRepository;
                                     this.provinceMapper = provinceMapper;
                                    this.districtSearchRepository = districtSearchRepository;
                                     this.districtMapper = districtMapper;
                                    this.wardSearchRepository = wardSearchRepository;
                                     this.wardMapper = wardMapper;

    }

    /**
     * Save a warehouse.
     *
     * @param warehouseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WarehouseDTO save(WarehouseDTO warehouseDTO) {
        log.debug("Request to save Warehouse : {}", warehouseDTO);
        Warehouse warehouse = warehouseMapper.toEntity(warehouseDTO);
        warehouse = warehouseRepository.save(warehouse);
        WarehouseDTO result = warehouseMapper.toDto(warehouse);
        warehouseSearchRepository.save(warehouse);
        return result;
    }

    /**
     * Get all the warehouses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Warehouses");
        return warehouseRepository.findAll(pageable)
            .map(warehouseMapper::toDto);
    }

    /**
     * Get one warehouse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WarehouseDTO findOne(Long id) {
        log.debug("Request to get Warehouse : {}", id);
        Warehouse warehouse = warehouseRepository.findOne(id);
        return warehouseMapper.toDto(warehouse);
    }

    /**
     * Delete the warehouse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Warehouse : {}", id);
        warehouseRepository.delete(id);
        warehouseSearchRepository.delete(id);
    }

    /**
     * Search for the warehouse corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Warehouses for query {}", query);
        Page<Warehouse> result = warehouseSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(warehouseMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseDTO> searchExample(WarehouseSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContactName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contactName", "*"+searchDto.getContactName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getContactPhone())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("contactPhone", "*"+searchDto.getContactPhone()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getAddress())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("address", "*"+searchDto.getAddress()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<Warehouse> warehousePage= warehouseSearchRepository.search(query);
            List<WarehouseDTO> warehouseList =  StreamSupport
            .stream(warehousePage.spliterator(), false)
            .map(warehouseMapper::toDto)
            .collect(Collectors.toList());
            warehouseList.forEach(warehouseDto -> {
            if(warehouseDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(warehouseDto.getCompanyId());
                warehouseDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(warehouseDto.getProvinceId()!=null){
                Province province= provinceSearchRepository.findOne(warehouseDto.getProvinceId());
                warehouseDto.setProvinceDTO(provinceMapper.toDto(province));
            }
            if(warehouseDto.getDistrictId()!=null){
                District district= districtSearchRepository.findOne(warehouseDto.getDistrictId());
                warehouseDto.setDistrictDTO(districtMapper.toDto(district));
            }
            if(warehouseDto.getWardId()!=null){
                Ward ward= wardSearchRepository.findOne(warehouseDto.getWardId());
                warehouseDto.setWardDTO(wardMapper.toDto(ward));
            }
            });
            return new PageImpl<>(warehouseList,pageable,warehousePage.getTotalElements());
        }
}
