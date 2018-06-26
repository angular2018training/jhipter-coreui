package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.PaymentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PaymentType and its DTO PaymentTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface PaymentTypeMapper extends EntityMapper<PaymentTypeDTO, PaymentType> {

    @Mapping(source = "company.id", target = "companyId")
    PaymentTypeDTO toDto(PaymentType paymentType);

    @Mapping(source = "companyId", target = "company")
    PaymentType toEntity(PaymentTypeDTO paymentTypeDTO);

    default PaymentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentType paymentType = new PaymentType();
        paymentType.setId(id);
        return paymentType;
    }
}
