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

    private final CompanyRepository companyRepository;

    private final CompanySearchRepository companySearchRepository;

    private final CustomerRepository customerRepository;

    private final CustomerSearchRepository customerSearchRepository;

    private final CustomerLegalRepository customerLegalRepository;

    private final CustomerLegalSearchRepository customerLegalSearchRepository;

    private final CustomerLegalFileUploadRepository customerLegalFileUploadRepository;

    private final CustomerLegalFileUploadSearchRepository customerLegalFileUploadSearchRepository;

    private final CustomerPaymentRepository customerPaymentRepository;

    private final CustomerPaymentSearchRepository customerPaymentSearchRepository;

    private final CustomerPostOfficeRepository customerPostOfficeRepository;

    private final CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository;

    private final CustomerServicesRepository customerServicesRepository;

    private final CustomerServicesSearchRepository customerServicesSearchRepository;

    private final CustomerSourceRepository customerSourceRepository;

    private final CustomerSourceSearchRepository customerSourceSearchRepository;

    private final CustomerTypeRepository customerTypeRepository;

    private final CustomerTypeSearchRepository customerTypeSearchRepository;

    private final CustomerWarehouseRepository customerWarehouseRepository;

    private final CustomerWarehouseSearchRepository customerWarehouseSearchRepository;

    private final DetailFormRepository detailFormRepository;

    private final DetailFormSearchRepository detailFormSearchRepository;

    private final DistrictRepository districtRepository;

    private final DistrictSearchRepository districtSearchRepository;

    private final DistrictTypeRepository districtTypeRepository;

    private final DistrictTypeSearchRepository districtTypeSearchRepository;

    private final FileUploadRepository fileUploadRepository;

    private final FileUploadSearchRepository fileUploadSearchRepository;

    private final MasterFormRepository masterFormRepository;

    private final MasterFormSearchRepository masterFormSearchRepository;

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

    private final OrderServicesRepository orderServicesRepository;

    private final OrderServicesSearchRepository orderServicesSearchRepository;

    private final OrderServicesTypeRepository orderServicesTypeRepository;

    private final OrderServicesTypeSearchRepository orderServicesTypeSearchRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusSearchRepository orderStatusSearchRepository;

    private final OrderSubServicesRepository orderSubServicesRepository;

    private final OrderSubServicesSearchRepository orderSubServicesSearchRepository;

    private final OrderSubServicesTypeRepository orderSubServicesTypeRepository;

    private final OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository;

    private final OrderUserRouteRepository orderUserRouteRepository;

    private final OrderUserRouteSearchRepository orderUserRouteSearchRepository;

    private final OrderUserRouteTypeRepository orderUserRouteTypeRepository;

    private final OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository;

    private final PaymentTypeRepository paymentTypeRepository;

    private final PaymentTypeSearchRepository paymentTypeSearchRepository;


    private final PostOfficeRepository postOfficeRepository;

    private final PostOfficeSearchRepository postOfficeSearchRepository;

    private final ProvinceRepository provinceRepository;

    private final ProvinceSearchRepository provinceSearchRepository;

    private final QuotationRepository quotationRepository;

    private final QuotationSearchRepository quotationSearchRepository;

    private final QuotationCodRepository quotationCodRepository;

    private final QuotationCodSearchRepository quotationCodSearchRepository;

    private final QuotationDomesticDeliveryRepository quotationDomesticDeliveryRepository;

    private final QuotationDomesticDeliverySearchRepository quotationDomesticDeliverySearchRepository;

    private final QuotationGiveBackRepository quotationGiveBackRepository;

    private final QuotationGiveBackSearchRepository quotationGiveBackSearchRepository;

    private final QuotationInsuranceRepository quotationInsuranceRepository;

    private final QuotationInsuranceSearchRepository quotationInsuranceSearchRepository;

    private final QuotationItemRepository quotationItemRepository;

    private final QuotationItemSearchRepository quotationItemSearchRepository;

    private final QuotationItemTypeRepository quotationItemTypeRepository;

    private final QuotationItemTypeSearchRepository quotationItemTypeSearchRepository;

    private final QuotationPickupRepository quotationPickupRepository;

    private final QuotationPickupSearchRepository quotationPickupSearchRepository;

    private final QuotationReturnRepository quotationReturnRepository;

    private final QuotationReturnSearchRepository quotationReturnSearchRepository;

    private final QuotationSubServicesRepository quotationSubServicesRepository;

    private final QuotationSubServicesSearchRepository quotationSubServicesSearchRepository;

    private final QuotationTypeRepository quotationTypeRepository;

    private final QuotationTypeSearchRepository quotationTypeSearchRepository;

    private final RegionRepository regionRepository;

    private final RegionSearchRepository regionSearchRepository;

    private final UserExtraInfoRepository userExtraInfoRepository;

    private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;

    private final UserGroupRepository userGroupRepository;

    private final UserGroupSearchRepository userGroupSearchRepository;


    private final UserPostOfficeRepository userPostOfficeRepository;

    private final UserPostOfficeSearchRepository userPostOfficeSearchRepository;

    private final WardRepository wardRepository;

    private final WardSearchRepository wardSearchRepository;

    private final WarehouseRepository warehouseRepository;

    private final WarehouseSearchRepository warehouseSearchRepository;

    private final WeightRepository weightRepository;

    private final WeightSearchRepository weightSearchRepository;

    private final UserRepository userRepository;

    private final UserSearchRepository userSearchRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

    public ElasticsearchIndexService(
        UserRepository userRepository,
        UserSearchRepository userSearchRepository,
        BankRepository bankRepository,
        BankSearchRepository bankSearchRepository,
        CompanyRepository companyRepository,
        CompanySearchRepository companySearchRepository,
        CustomerRepository customerRepository,
        CustomerSearchRepository customerSearchRepository,
        CustomerLegalRepository customerLegalRepository,
        CustomerLegalSearchRepository customerLegalSearchRepository,
        CustomerLegalFileUploadRepository customerLegalFileUploadRepository,
        CustomerLegalFileUploadSearchRepository customerLegalFileUploadSearchRepository,
        CustomerPaymentRepository customerPaymentRepository,
        CustomerPaymentSearchRepository customerPaymentSearchRepository,
        CustomerPostOfficeRepository customerPostOfficeRepository,
        CustomerPostOfficeSearchRepository customerPostOfficeSearchRepository,
        CustomerServicesRepository customerServicesRepository,
        CustomerServicesSearchRepository customerServicesSearchRepository,
        CustomerSourceRepository customerSourceRepository,
        CustomerSourceSearchRepository customerSourceSearchRepository,
        CustomerTypeRepository customerTypeRepository,
        CustomerTypeSearchRepository customerTypeSearchRepository,
        CustomerWarehouseRepository customerWarehouseRepository,
        CustomerWarehouseSearchRepository customerWarehouseSearchRepository,
        DetailFormRepository detailFormRepository,
        DetailFormSearchRepository detailFormSearchRepository,
        DistrictRepository districtRepository,
        DistrictSearchRepository districtSearchRepository,
        DistrictTypeRepository districtTypeRepository,
        DistrictTypeSearchRepository districtTypeSearchRepository,
        FileUploadRepository fileUploadRepository,
        FileUploadSearchRepository fileUploadSearchRepository,
        MasterFormRepository masterFormRepository,
        MasterFormSearchRepository masterFormSearchRepository,
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
        OrderServicesRepository orderServicesRepository,
        OrderServicesSearchRepository orderServicesSearchRepository,
        OrderServicesTypeRepository orderServicesTypeRepository,
        OrderServicesTypeSearchRepository orderServicesTypeSearchRepository,
        OrderStatusRepository orderStatusRepository,
        OrderStatusSearchRepository orderStatusSearchRepository,
        OrderSubServicesRepository orderSubServicesRepository,
        OrderSubServicesSearchRepository orderSubServicesSearchRepository,
        OrderSubServicesTypeRepository orderSubServicesTypeRepository,
        OrderSubServicesTypeSearchRepository orderSubServicesTypeSearchRepository,
        OrderUserRouteRepository orderUserRouteRepository,
        OrderUserRouteSearchRepository orderUserRouteSearchRepository,
        OrderUserRouteTypeRepository orderUserRouteTypeRepository,
        OrderUserRouteTypeSearchRepository orderUserRouteTypeSearchRepository,
        PaymentTypeRepository paymentTypeRepository,
        PaymentTypeSearchRepository paymentTypeSearchRepository,
        PostOfficeRepository postOfficeRepository,
        PostOfficeSearchRepository postOfficeSearchRepository,
        ProvinceRepository provinceRepository,
        ProvinceSearchRepository provinceSearchRepository,
        QuotationRepository quotationRepository,
        QuotationSearchRepository quotationSearchRepository,
        QuotationCodRepository quotationCodRepository,
        QuotationCodSearchRepository quotationCodSearchRepository,
        QuotationDomesticDeliveryRepository quotationDomesticDeliveryRepository,
        QuotationDomesticDeliverySearchRepository quotationDomesticDeliverySearchRepository,
        QuotationGiveBackRepository quotationGiveBackRepository,
        QuotationGiveBackSearchRepository quotationGiveBackSearchRepository,
        QuotationInsuranceRepository quotationInsuranceRepository,
        QuotationInsuranceSearchRepository quotationInsuranceSearchRepository,
        QuotationItemRepository quotationItemRepository,
        QuotationItemSearchRepository quotationItemSearchRepository,
        QuotationItemTypeRepository quotationItemTypeRepository,
        QuotationItemTypeSearchRepository quotationItemTypeSearchRepository,
        QuotationPickupRepository quotationPickupRepository,
        QuotationPickupSearchRepository quotationPickupSearchRepository,
        QuotationReturnRepository quotationReturnRepository,
        QuotationReturnSearchRepository quotationReturnSearchRepository,
        QuotationSubServicesRepository quotationSubServicesRepository,
        QuotationSubServicesSearchRepository quotationSubServicesSearchRepository,
        QuotationTypeRepository quotationTypeRepository,
        QuotationTypeSearchRepository quotationTypeSearchRepository,
        RegionRepository regionRepository,
        RegionSearchRepository regionSearchRepository,
        UserExtraInfoRepository userExtraInfoRepository,
        UserExtraInfoSearchRepository userExtraInfoSearchRepository,
        UserGroupRepository userGroupRepository,
        UserGroupSearchRepository userGroupSearchRepository,
        UserPostOfficeRepository userPostOfficeRepository,
        UserPostOfficeSearchRepository userPostOfficeSearchRepository,
        WardRepository wardRepository,
        WardSearchRepository wardSearchRepository,
        WarehouseRepository warehouseRepository,
        WarehouseSearchRepository warehouseSearchRepository,
        WeightRepository weightRepository,
        WeightSearchRepository weightSearchRepository,
        ElasticsearchTemplate elasticsearchTemplate) {
        this.userRepository = userRepository;
        this.userSearchRepository = userSearchRepository;
        this.bankRepository = bankRepository;
        this.bankSearchRepository = bankSearchRepository;
        this.companyRepository = companyRepository;
        this.companySearchRepository = companySearchRepository;
        this.customerRepository = customerRepository;
        this.customerSearchRepository = customerSearchRepository;
        this.customerLegalRepository = customerLegalRepository;
        this.customerLegalSearchRepository = customerLegalSearchRepository;
        this.customerLegalFileUploadRepository = customerLegalFileUploadRepository;
        this.customerLegalFileUploadSearchRepository = customerLegalFileUploadSearchRepository;
        this.customerPaymentRepository = customerPaymentRepository;
        this.customerPaymentSearchRepository = customerPaymentSearchRepository;
        this.customerPostOfficeRepository = customerPostOfficeRepository;
        this.customerPostOfficeSearchRepository = customerPostOfficeSearchRepository;
        this.customerServicesRepository = customerServicesRepository;
        this.customerServicesSearchRepository = customerServicesSearchRepository;
        this.customerSourceRepository = customerSourceRepository;
        this.customerSourceSearchRepository = customerSourceSearchRepository;
        this.customerTypeRepository = customerTypeRepository;
        this.customerTypeSearchRepository = customerTypeSearchRepository;
        this.customerWarehouseRepository = customerWarehouseRepository;
        this.customerWarehouseSearchRepository = customerWarehouseSearchRepository;
        this.detailFormRepository = detailFormRepository;
        this.detailFormSearchRepository = detailFormSearchRepository;
        this.districtRepository = districtRepository;
        this.districtSearchRepository = districtSearchRepository;
        this.districtTypeRepository = districtTypeRepository;
        this.districtTypeSearchRepository = districtTypeSearchRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadSearchRepository = fileUploadSearchRepository;
        this.masterFormRepository = masterFormRepository;
        this.masterFormSearchRepository = masterFormSearchRepository;
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
        this.orderServicesRepository = orderServicesRepository;
        this.orderServicesSearchRepository = orderServicesSearchRepository;
        this.orderServicesTypeRepository = orderServicesTypeRepository;
        this.orderServicesTypeSearchRepository = orderServicesTypeSearchRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusSearchRepository = orderStatusSearchRepository;
        this.orderSubServicesRepository = orderSubServicesRepository;
        this.orderSubServicesSearchRepository = orderSubServicesSearchRepository;
        this.orderSubServicesTypeRepository = orderSubServicesTypeRepository;
        this.orderSubServicesTypeSearchRepository = orderSubServicesTypeSearchRepository;
        this.orderUserRouteRepository = orderUserRouteRepository;
        this.orderUserRouteSearchRepository = orderUserRouteSearchRepository;
        this.orderUserRouteTypeRepository = orderUserRouteTypeRepository;
        this.orderUserRouteTypeSearchRepository = orderUserRouteTypeSearchRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeSearchRepository = paymentTypeSearchRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.postOfficeSearchRepository = postOfficeSearchRepository;
        this.provinceRepository = provinceRepository;
        this.provinceSearchRepository = provinceSearchRepository;
        this.quotationRepository = quotationRepository;
        this.quotationSearchRepository = quotationSearchRepository;
        this.quotationCodRepository = quotationCodRepository;
        this.quotationCodSearchRepository = quotationCodSearchRepository;
        this.quotationDomesticDeliveryRepository = quotationDomesticDeliveryRepository;
        this.quotationDomesticDeliverySearchRepository = quotationDomesticDeliverySearchRepository;
        this.quotationGiveBackRepository = quotationGiveBackRepository;
        this.quotationGiveBackSearchRepository = quotationGiveBackSearchRepository;
        this.quotationInsuranceRepository = quotationInsuranceRepository;
        this.quotationInsuranceSearchRepository = quotationInsuranceSearchRepository;
        this.quotationItemRepository = quotationItemRepository;
        this.quotationItemSearchRepository = quotationItemSearchRepository;
        this.quotationItemTypeRepository = quotationItemTypeRepository;
        this.quotationItemTypeSearchRepository = quotationItemTypeSearchRepository;
        this.quotationPickupRepository = quotationPickupRepository;
        this.quotationPickupSearchRepository = quotationPickupSearchRepository;
        this.quotationReturnRepository = quotationReturnRepository;
        this.quotationReturnSearchRepository = quotationReturnSearchRepository;
        this.quotationSubServicesRepository = quotationSubServicesRepository;
        this.quotationSubServicesSearchRepository = quotationSubServicesSearchRepository;
        this.quotationTypeRepository = quotationTypeRepository;
        this.quotationTypeSearchRepository = quotationTypeSearchRepository;
        this.regionRepository = regionRepository;
        this.regionSearchRepository = regionSearchRepository;
        this.userExtraInfoRepository = userExtraInfoRepository;
        this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
        this.userGroupRepository = userGroupRepository;
        this.userGroupSearchRepository = userGroupSearchRepository;
        this.userPostOfficeRepository = userPostOfficeRepository;
        this.userPostOfficeSearchRepository = userPostOfficeSearchRepository;
        this.wardRepository = wardRepository;
        this.wardSearchRepository = wardSearchRepository;
        this.warehouseRepository = warehouseRepository;
        this.warehouseSearchRepository = warehouseSearchRepository;
        this.weightRepository = weightRepository;
        this.weightSearchRepository = weightSearchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Async
    @Timed
    public void reindexAll() {
        if (reindexLock.tryLock()) {
            try {
                reindexForClass(Bank.class, bankRepository, bankSearchRepository);
                reindexForClass(Company.class, companyRepository, companySearchRepository);
                reindexForClass(Customer.class, customerRepository, customerSearchRepository);
                reindexForClass(CustomerLegal.class, customerLegalRepository, customerLegalSearchRepository);
                reindexForClass(CustomerLegalFileUpload.class, customerLegalFileUploadRepository, customerLegalFileUploadSearchRepository);
                reindexForClass(CustomerPayment.class, customerPaymentRepository, customerPaymentSearchRepository);
                reindexForClass(CustomerPostOffice.class, customerPostOfficeRepository, customerPostOfficeSearchRepository);
                reindexForClass(CustomerServices.class, customerServicesRepository, customerServicesSearchRepository);
                reindexForClass(CustomerSource.class, customerSourceRepository, customerSourceSearchRepository);
                reindexForClass(CustomerType.class, customerTypeRepository, customerTypeSearchRepository);
                reindexForClass(CustomerWarehouse.class, customerWarehouseRepository, customerWarehouseSearchRepository);
                reindexForClass(DetailForm.class, detailFormRepository, detailFormSearchRepository);
                reindexForClass(District.class, districtRepository, districtSearchRepository);
                reindexForClass(DistrictType.class, districtTypeRepository, districtTypeSearchRepository);
                reindexForClass(FileUpload.class, fileUploadRepository, fileUploadSearchRepository);
                reindexForClass(MasterForm.class, masterFormRepository, masterFormSearchRepository);
                reindexForClass(OrderConsignee.class, orderConsigneeRepository, orderConsigneeSearchRepository);
                reindexForClass(OrderDelivery.class, orderDeliveryRepository, orderDeliverySearchRepository);
                reindexForClass(OrderFee.class, orderFeeRepository, orderFeeSearchRepository);
                reindexForClass(OrderMain.class, orderMainRepository, orderMainSearchRepository);
                reindexForClass(OrderPickup.class, orderPickupRepository, orderPickupSearchRepository);
                reindexForClass(OrderServices.class, orderServicesRepository, orderServicesSearchRepository);
                reindexForClass(OrderServicesType.class, orderServicesTypeRepository, orderServicesTypeSearchRepository);
                reindexForClass(OrderStatus.class, orderStatusRepository, orderStatusSearchRepository);
                reindexForClass(OrderSubServices.class, orderSubServicesRepository, orderSubServicesSearchRepository);
                reindexForClass(OrderSubServicesType.class, orderSubServicesTypeRepository, orderSubServicesTypeSearchRepository);
                reindexForClass(OrderUserRoute.class, orderUserRouteRepository, orderUserRouteSearchRepository);
                reindexForClass(OrderUserRouteType.class, orderUserRouteTypeRepository, orderUserRouteTypeSearchRepository);
                reindexForClass(PaymentType.class, paymentTypeRepository, paymentTypeSearchRepository);
                reindexForClass(PostOffice.class, postOfficeRepository, postOfficeSearchRepository);
                reindexForClass(Province.class, provinceRepository, provinceSearchRepository);
                reindexForClass(Quotation.class, quotationRepository, quotationSearchRepository);
                reindexForClass(QuotationCod.class, quotationCodRepository, quotationCodSearchRepository);
                reindexForClass(QuotationDomesticDelivery.class, quotationDomesticDeliveryRepository, quotationDomesticDeliverySearchRepository);
                reindexForClass(QuotationGiveBack.class, quotationGiveBackRepository, quotationGiveBackSearchRepository);
                reindexForClass(QuotationInsurance.class, quotationInsuranceRepository, quotationInsuranceSearchRepository);
                reindexForClass(QuotationItem.class, quotationItemRepository, quotationItemSearchRepository);
                reindexForClass(QuotationItemType.class, quotationItemTypeRepository, quotationItemTypeSearchRepository);
                reindexForClass(QuotationPickup.class, quotationPickupRepository, quotationPickupSearchRepository);
                reindexForClass(QuotationReturn.class, quotationReturnRepository, quotationReturnSearchRepository);
                reindexForClass(QuotationSubServices.class, quotationSubServicesRepository, quotationSubServicesSearchRepository);
                reindexForClass(QuotationType.class, quotationTypeRepository, quotationTypeSearchRepository);
                reindexForClass(Region.class, regionRepository, regionSearchRepository);
                reindexForClass(UserExtraInfo.class, userExtraInfoRepository, userExtraInfoSearchRepository);
                reindexForClass(UserGroup.class, userGroupRepository, userGroupSearchRepository);
                reindexForClass(UserPostOffice.class, userPostOfficeRepository, userPostOfficeSearchRepository);
                reindexForClass(Ward.class, wardRepository, wardSearchRepository);
                reindexForClass(Warehouse.class, warehouseRepository, warehouseSearchRepository);
                reindexForClass(Weight.class, weightRepository, weightSearchRepository);
                reindexForClass(User.class, userRepository, userSearchRepository);

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
