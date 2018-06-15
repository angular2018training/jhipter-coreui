package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerLegalMapper.class, CustomerPaymentMapper.class, UserExtraInfoMapper.class, ProvinceMapper.class, DistrictMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "legal.id", target = "legalId")
    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "manageUser.id", target = "manageUserId")
    @Mapping(source = "saleUser.id", target = "saleUserId")
    @Mapping(source = "debtUser.id", target = "debtUserId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    CustomerDTO toDto(Customer customer);

    @Mapping(source = "legalId", target = "legal")
    @Mapping(source = "paymentId", target = "payment")
    @Mapping(target = "customerPostOffices", ignore = true)
    @Mapping(source = "manageUserId", target = "manageUser")
    @Mapping(source = "saleUserId", target = "saleUser")
    @Mapping(source = "debtUserId", target = "debtUser")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
