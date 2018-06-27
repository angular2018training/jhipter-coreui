package vn.nextlogix.service;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.nextlogix.domain.*;
import vn.nextlogix.repository.*;
import vn.nextlogix.repository.search.*;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ManyToMany;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ElasticsearchIndexService {

    private static final Lock reindexLock = new ReentrantLock();

    private final Logger log = LoggerFactory.getLogger(ElasticsearchIndexService.class);

    private final BankRepository bankRepository;

    private final BankSearchRepository bankSearchRepository;

    private final CustomerRepository customerRepository;

    private final CustomerSearchRepository customerSearchRepository;

    private final CustomerLegalRepository customerLegalRepository;

    private final CustomerLegalSearchRepository customerLegalSearchRepository;

    private final CustomerPaymentRepository customerPaymentRepository;

    private final CustomerPaymentSearchRepository customerPaymentSearchRepository;

    private final CustomerPostOfficeRepository customerPostOfficeRepository;

    private final CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository;

    private final DistrictRepository districtRepository;

    private final DistrictSearchRepository districtSearchRepository;

    private final FileUploadRepository fileUploadRepository;

    private final FileUploadSearchRepository fileUploadSearchRepository;

    private final OrderConsigneeRepository orderConsigneeRepository;

    private final OrderConsigneeSearchRepository orderConsigneeSearchRepository;

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliverySearchRepository orderDeliverySearchRepository;

    private final OrderFeeRepository orderFeeRepository;

    private final OrderFeeSearchRepository orderFeeSearchRepository;

    private final OrderMainRepository orderMainRepository;

    private final OrderMainSearchRepository orderMainSearchRepository;

    private final OrderPickupRepository orderPickupRepository;

    private final OrderPickupSearchRepository orderPickupSearchRepository;

    private final OrderServiceRepository orderServiceRepository;

    private final OrderServiceSearchRepository orderServiceSearchRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusSearchRepository orderStatusSearchRepository;

    private final PositionRepository positionRepository;

    private final PositionSearchRepository positionSearchRepository;

    private final PostOfficeRepository postOfficeRepository;

    private final PostOfficeSearchRepository postOfficeSearchRepository;

    private final ProvinceRepository provinceRepository;

    private final ProvinceSearchRepository provinceSearchRepository;

    private final QuotationRepository quotationRepository;

    private final QuotationSearchRepository quotationSearchRepository;

    private final QuotationItemRepository quotationItemRepository;

    private final QuotationItemSearchRepository quotationItemSearchRepository;

    private final UserExtraInfoRepository userExtraInfoRepository;

    private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;

    private final UserGroupRepository userGroupRepository;

    private final UserGroupSearchRepository userGroupSearchRepository;

    private final UserPositionRepository userPositionRepository;

    private final UserPositionSearchRepository userPositionSearchRepository;

    private final WardRepository wardRepository;

    private final WardSearchRepository wardSearchRepository;

    private final UserRepository userRepository;

    private final UserSearchRepository userSearchRepository;


    private final WarehouseRepository warehouseRepository;

    private final WarehouseSearchRepository warehouseSearchRepository;

    private final CompanyRepository companyRepository;

    private final CompanySearchRepository companySearchRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

    public ElasticsearchIndexService(
        UserRepository userRepository,
        UserSearchRepository userSearchRepository,
        BankRepository bankRepository,
        BankSearchRepository bankSearchRepository,
        CustomerRepository customerRepository,
        CustomerSearchRepository customerSearchRepository,
        CustomerLegalRepository customerLegalRepository,
        CustomerLegalSearchRepository customerLegalSearchRepository,
        CustomerPaymentRepository customerPaymentRepository,
        CustomerPaymentSearchRepository customerPaymentSearchRepository,
        CustomerPostOfficeRepository customerPostOfficeRepository,
        CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository,
        DistrictRepository districtRepository,
        DistrictSearchRepository districtSearchRepository,
        FileUploadRepository fileUploadRepository,
        FileUploadSearchRepository fileUploadSearchRepository,
        OrderConsigneeRepository orderConsigneeRepository,
        OrderConsigneeSearchRepository orderConsigneeSearchRepository,
        OrderDeliveryRepository orderDeliveryRepository,
        OrderDeliverySearchRepository orderDeliverySearchRepository,
        OrderFeeRepository orderFeeRepository,
        OrderFeeSearchRepository orderFeeSearchRepository,
        OrderMainRepository orderMainRepository,
        OrderMainSearchRepository orderMainSearchRepository,
        OrderPickupRepository orderPickupRepository,
        OrderPickupSearchRepository orderPickupSearchRepository,
        OrderServiceRepository orderServiceRepository,
        OrderServiceSearchRepository orderServiceSearchRepository,
        OrderStatusRepository orderStatusRepository,
        OrderStatusSearchRepository orderStatusSearchRepository,
        PositionRepository positionRepository,
        PositionSearchRepository positionSearchRepository,
        PostOfficeRepository postOfficeRepository,
        PostOfficeSearchRepository postOfficeSearchRepository,
        ProvinceRepository provinceRepository,
        ProvinceSearchRepository provinceSearchRepository,
        QuotationRepository quotationRepository,
        QuotationSearchRepository quotationSearchRepository,
        QuotationItemRepository quotationItemRepository,
        QuotationItemSearchRepository quotationItemSearchRepository,
        UserExtraInfoRepository userExtraInfoRepository,
        UserExtraInfoSearchRepository userExtraInfoSearchRepository,
        UserGroupRepository userGroupRepository,
        UserGroupSearchRepository userGroupSearchRepository,
        UserPositionRepository userPositionRepository,
        UserPositionSearchRepository userPositionSearchRepository,
        WardRepository wardRepository,
        WardSearchRepository wardSearchRepository,
        WarehouseRepository warehouseRepository,
        WarehouseSearchRepository warehouseSearchRepository,
        CompanyRepository companyRepository,
        CompanySearchRepository companySearchRepository,
        ElasticsearchTemplate elasticsearchTemplate) {
        this.userRepository = userRepository;
        this.userSearchRepository = userSearchRepository;
        this.bankRepository = bankRepository;
        this.bankSearchRepository = bankSearchRepository;
        this.customerRepository = customerRepository;
        this.customerSearchRepository = customerSearchRepository;
        this.customerLegalRepository = customerLegalRepository;
        this.customerLegalSearchRepository = customerLegalSearchRepository;
        this.customerPaymentRepository = customerPaymentRepository;
        this.customerPaymentSearchRepository = customerPaymentSearchRepository;
        this.customerPostOfficeRepository = customerPostOfficeRepository;
        this.customerPostOfficeSearchRepository = customerPostOfficeSearchRepository;
        this.districtRepository = districtRepository;
        this.districtSearchRepository = districtSearchRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadSearchRepository = fileUploadSearchRepository;
        this.orderConsigneeRepository = orderConsigneeRepository;
        this.orderConsigneeSearchRepository = orderConsigneeSearchRepository;
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliverySearchRepository = orderDeliverySearchRepository;
        this.orderFeeRepository = orderFeeRepository;
        this.orderFeeSearchRepository = orderFeeSearchRepository;
        this.orderMainRepository = orderMainRepository;
        this.orderMainSearchRepository = orderMainSearchRepository;
        this.orderPickupRepository = orderPickupRepository;
        this.orderPickupSearchRepository = orderPickupSearchRepository;
        this.orderServiceRepository = orderServiceRepository;
        this.orderServiceSearchRepository = orderServiceSearchRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusSearchRepository = orderStatusSearchRepository;
        this.positionRepository = positionRepository;
        this.positionSearchRepository = positionSearchRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.postOfficeSearchRepository = postOfficeSearchRepository;
        this.provinceRepository = provinceRepository;
        this.provinceSearchRepository = provinceSearchRepository;
        this.quotationRepository = quotationRepository;
        this.quotationSearchRepository = quotationSearchRepository;
        this.quotationItemRepository = quotationItemRepository;
        this.quotationItemSearchRepository = quotationItemSearchRepository;
        this.userExtraInfoRepository = userExtraInfoRepository;
        this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
        this.userGroupRepository = userGroupRepository;
        this.userGroupSearchRepository = userGroupSearchRepository;
        this.userPositionRepository = userPositionRepository;
        this.userPositionSearchRepository = userPositionSearchRepository;
        this.wardRepository = wardRepository;
        this.wardSearchRepository = wardSearchRepository;
        this.warehouseRepository = warehouseRepository;
        this.warehouseSearchRepository = warehouseSearchRepository;
        this.companyRepository = companyRepository;
        this.companySearchRepository = companySearchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Async
    @Timed
    public void reindexAll() {
        if (reindexLock.tryLock()) {
            try {
                reindexForClass(Bank.class, bankRepository, bankSearchRepository);
                reindexForClass(Customer.class, customerRepository, customerSearchRepository);
                reindexForClass(CustomerLegal.class, customerLegalRepository, customerLegalSearchRepository);
                reindexForClass(CustomerPayment.class, customerPaymentRepository, customerPaymentSearchRepository);
                reindexForClass(CustomerPostOffice.class, customerPostOfficeRepository, customerPostOfficeSearchRepository);
                reindexForClass(District.class, districtRepository, districtSearchRepository);
                reindexForClass(FileUpload.class, fileUploadRepository, fileUploadSearchRepository);
                reindexForClass(OrderConsignee.class, orderConsigneeRepository, orderConsigneeSearchRepository);
                reindexForClass(OrderDelivery.class, orderDeliveryRepository, orderDeliverySearchRepository);
                reindexForClass(OrderFee.class, orderFeeRepository, orderFeeSearchRepository);
                reindexForClass(OrderMain.class, orderMainRepository, orderMainSearchRepository);
                reindexForClass(OrderPickup.class, orderPickupRepository, orderPickupSearchRepository);
                reindexForClass(OrderService.class, orderServiceRepository, orderServiceSearchRepository);
                reindexForClass(OrderStatus.class, orderStatusRepository, orderStatusSearchRepository);
                reindexForClass(Position.class, positionRepository, positionSearchRepository);
                reindexForClass(PostOffice.class, postOfficeRepository, postOfficeSearchRepository);
                reindexForClass(Province.class, provinceRepository, provinceSearchRepository);
                reindexForClass(Quotation.class, quotationRepository, quotationSearchRepository);
                reindexForClass(QuotationItem.class, quotationItemRepository, quotationItemSearchRepository);
                reindexForClass(UserExtraInfo.class, userExtraInfoRepository, userExtraInfoSearchRepository);
                reindexForClass(UserGroup.class, userGroupRepository, userGroupSearchRepository);
                reindexForClass(UserPosition.class, userPositionRepository, userPositionSearchRepository);
                reindexForClass(Ward.class, wardRepository, wardSearchRepository);
                reindexForClass(User.class, userRepository, userSearchRepository);
                reindexForClass(Warehouse.class,warehouseRepository,warehouseSearchRepository);
                reindexForClass(Company.class,companyRepository,companySearchRepository);

              log.info("Elasticsearch: Successfully performed reindexing");
            } finally {
                reindexLock.unlock();
            }
        } else {
            log.info("Elasticsearch: concurrent reindexing attempt");
        }
    }

    @SuppressWarnings("unchecked")
    private <T, ID extends Serializable> void reindexForClass(Class<T> entityClass, JpaRepository<T, ID> jpaRepository,
                                                              ElasticsearchRepository<T, ID> elasticsearchRepository) {
        elasticsearchTemplate.deleteIndex(entityClass);
        try {
            elasticsearchTemplate.createIndex(entityClass);
        } catch (IndexAlreadyExistsException e) {
            // Do nothing. Index was already concurrently recreated by some other service.
        }
        elasticsearchTemplate.putMapping(entityClass);
        if (jpaRepository.count() > 0) {
            // if a JHipster entity field is the owner side of a many-to-many relationship, it should be loaded manually
            List<Method> relationshipGetters = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.getType().equals(Set.class))
                .filter(field -> field.getAnnotation(ManyToMany.class) != null)
                .filter(field -> field.getAnnotation(ManyToMany.class).mappedBy().isEmpty())
                .filter(field -> field.getAnnotation(JsonIgnore.class) == null)
                .map(field -> {
                    try {
                        return new PropertyDescriptor(field.getName(), entityClass).getReadMethod();
                    } catch (IntrospectionException e) {
                        log.error("Error retrieving getter for class {}, field {}. Field will NOT be indexed",
                            entityClass.getSimpleName(), field.getName(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            int size = 100;
            for (int i = 0; i <= jpaRepository.count() / size; i++) {
                Pageable page = new PageRequest(i, size);
                log.info("Indexing page {} of {}, size {}", i, jpaRepository.count() / size, size);
                Page<T> results = jpaRepository.findAll(page);
                results.map(result -> {
                    // if there are any relationships to load, do it now
                    relationshipGetters.forEach(method -> {
                        try {
                            // eagerly load the relationship set
                            ((Set) method.invoke(result)).size();
                        } catch (Exception ex) {
                            log.error(ex.getMessage());
                        }
                    });
                    return result;
                });
                elasticsearchRepository.save(results.getContent());
            }
        }
        log.info("Elasticsearch: Indexed all rows for {}", entityClass.getSimpleName());
    }
}
