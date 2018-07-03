package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderServicesTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderServicesType and its DTO OrderServicesTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderServicesTypeMapper extends EntityMapper<OrderServicesTypeDTO, OrderServicesType> {



    default OrderServicesType fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderServicesType orderServicesType = new OrderServicesType();
        orderServicesType.setId(id);
        return orderServicesType;
    }
}
