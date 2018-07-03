package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerWarehouseService;
import vn.nextlogix.domain.CustomerWarehouse;


    import vn.nextlogix.repository.CustomerWarehouseRepository;
    import org.elasticsearch.search.sort.SortBuilders;
    import org.elasticsearch.search.sort.SortOrder;

    import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.CustomerWarehouseSearchRepository;
import vn.nextlogix.service.dto.CustomerWarehouseDTO;
import vn.nextlogix.service.dto.CustomerWarehouseSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Warehouse;
    import vn.nextlogix.domain.Customer;
import vn.nextlogix.service.mapper.CustomerWarehouseMapper;
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

    import vn.nextlogix.repository.search.WarehouseSearchRepository;
    import vn.nextlogix.service.mapper.WarehouseMapper;

    import vn.nextlogix.repository.search.CustomerSearchRepository;
    import vn.nextlogix.service.mapper.CustomerMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerWarehouse.
 */
@Service
@Transactional
public class CustomerWarehouseServiceImpl implements CustomerWarehouseService {

    private final Logger log = LoggerFactory.getLogger(CustomerWarehouseServiceImpl.class);

    private final CustomerWarehouseRepository customerWarehouseRepository;

    private final CustomerWarehouseMapper customerWarehouseMapper;

    private final CustomerWarehouseSearchRepository customerWarehouseSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final WarehouseSearchRepository warehouseSearchRepository;
        private final WarehouseMapper warehouseMapper;

        private final CustomerSearchRepository customerSearchRepository;
        private final CustomerMapper customerMapper;


    public CustomerWarehouseServiceImpl(CustomerWarehouseRepository customerWarehouseRepository, CustomerWarehouseMapper customerWarehouseMapper, CustomerWarehouseSearchRepository customerWarehouseSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,WarehouseSearchRepository warehouseSearchRepository,WarehouseMapper  warehouseMapper
,CustomerSearchRepository customerSearchRepository,CustomerMapper  customerMapper
) {
        this.customerWarehouseRepository = customerWarehouseRepository;
        this.customerWarehouseMapper = customerWarehouseMapper;
        this.customerWarehouseSearchRepository = customerWarehouseSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.warehouseSearchRepository = warehouseSearchRepository;
                                     this.warehouseMapper = warehouseMapper;
                                    this.customerSearchRepository = customerSearchRepository;
                                     this.customerMapper = customerMapper;

    }

    /**
     * Save a customerWarehouse.
     *
     * @param customerWarehouseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerWarehouseDTO save(CustomerWarehouseDTO customerWarehouseDTO) {
        log.debug("Request to save CustomerWarehouse : {}", customerWarehouseDTO);
        CustomerWarehouse customerWarehouse = customerWarehouseMapper.toEntity(customerWarehouseDTO);
        customerWarehouse = customerWarehouseRepository.save(customerWarehouse);
        CustomerWarehouseDTO result = customerWarehouseMapper.toDto(customerWarehouse);
        customerWarehouseSearchRepository.save(customerWarehouse);
        return result;
    }

    /**
     * Get all the customerWarehouses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerWarehouseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerWarehouses");
        return customerWarehouseRepository.findAll(pageable)
            .map(customerWarehouseMapper::toDto);
    }

    /**
     * Get one customerWarehouse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerWarehouseDTO findOne(Long id) {
        log.debug("Request to get CustomerWarehouse : {}", id);
        CustomerWarehouse customerWarehouse = customerWarehouseRepository.findOne(id);
        return customerWarehouseMapper.toDto(customerWarehouse);
    }

    /**
     * Delete the customerWarehouse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerWarehouse : {}", id);
        customerWarehouseRepository.delete(id);
        customerWarehouseSearchRepository.delete(id);
    }

    /**
     * Search for the customerWarehouse corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerWarehouseDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerWarehouses for query {}", query);
        Page<CustomerWarehouse> result = customerWarehouseSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerWarehouseMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerWarehouseDTO> searchExample(CustomerWarehouseSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(searchDto.getCompanyId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
            }
            if(searchDto.getWarehouseId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("warehouse.id", searchDto.getWarehouseId()));
            }
            if(searchDto.getCustomerParentId() !=null) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("customerParent.id", searchDto.getCustomerParentId()));
            }
            NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);

            pageable.getSort().forEach(sort -> {
            queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty()).order(sort.getDirection() ==org.springframework.data.domain.Sort.Direction.ASC?SortOrder.ASC:SortOrder.DESC).unmappedType("long"));
            });
            NativeSearchQuery query = queryBuilder.build();
            Page<CustomerWarehouse> customerWarehousePage= customerWarehouseSearchRepository.search(query);
            List<CustomerWarehouseDTO> customerWarehouseList =  StreamSupport
            .stream(customerWarehousePage.spliterator(), false)
            .map(customerWarehouseMapper::toDto)
            .collect(Collectors.toList());
            customerWarehouseList.forEach(customerWarehouseDto -> {
            if(customerWarehouseDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerWarehouseDto.getCompanyId());
                customerWarehouseDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerWarehouseDto.getWarehouseId()!=null){
                Warehouse warehouse= warehouseSearchRepository.findOne(customerWarehouseDto.getWarehouseId());
                customerWarehouseDto.setWarehouseDTO(warehouseMapper.toDto(warehouse));
            }
            if(customerWarehouseDto.getCustomerParentId()!=null){
                Customer customer= customerSearchRepository.findOne(customerWarehouseDto.getCustomerParentId());
                customerWarehouseDto.setCustomerParentDTO(customerMapper.toDto(customer));
            }
            });
            return new PageImpl<>(customerWarehouseList,pageable,customerWarehousePage.getTotalElements());
        }
}
