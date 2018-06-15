package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerPaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerPayment and its DTO CustomerPaymentDTO.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class, UserExtraInfoMapper.class})
public interface CustomerPaymentMapper extends EntityMapper<CustomerPaymentDTO, CustomerPayment> {

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "userVerify.id", target = "userVerifyId")
    CustomerPaymentDTO toDto(CustomerPayment customerPayment);

    @Mapping(source = "bankId", target = "bank")
    @Mapping(source = "userVerifyId", target = "userVerify")
    CustomerPayment toEntity(CustomerPaymentDTO customerPaymentDTO);

    default CustomerPayment fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerPayment customerPayment = new CustomerPayment();
        customerPayment.setId(id);
        return customerPayment;
    }
}
