package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderSubServicesTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderSubServicesType and its DTO OrderSubServicesTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface OrderSubServicesTypeMapper extends EntityMapper<OrderSubServicesTypeDTO, OrderSubServicesType> {

    @Mapping(source = "company.id", target = "companyId")
    OrderSubServicesTypeDTO toDto(OrderSubServicesType orderSubServicesType);

    @Mapping(source = "companyId", target = "company")
    OrderSubServicesType toEntity(OrderSubServicesTypeDTO orderSubServicesTypeDTO);

    default OrderSubServicesType fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderSubServicesType orderSubServicesType = new OrderSubServicesType();
        orderSubServicesType.setId(id);
        return orderSubServicesType;
    }
}
