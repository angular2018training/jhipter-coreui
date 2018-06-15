package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderDeliveryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderDelivery and its DTO OrderDeliveryDTO.
 */
@Mapper(componentModel = "spring", uses = {UserExtraInfoMapper.class, OrderStatusMapper.class})
public interface OrderDeliveryMapper extends EntityMapper<OrderDeliveryDTO, OrderDelivery> {

    @Mapping(source = "createUser.id", target = "createUserId")
    @Mapping(source = "orderStatus.id", target = "orderStatusId")
    OrderDeliveryDTO toDto(OrderDelivery orderDelivery);

    @Mapping(source = "createUserId", target = "createUser")
    @Mapping(source = "orderStatusId", target = "orderStatus")
    OrderDelivery toEntity(OrderDeliveryDTO orderDeliveryDTO);

    default OrderDelivery fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setId(id);
        return orderDelivery;
    }
}
