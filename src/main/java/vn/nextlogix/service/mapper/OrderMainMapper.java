package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderMainDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderMain and its DTO OrderMainDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderPickupMapper.class, OrderConsigneeMapper.class, OrderFeeMapper.class, OrderDeliveryMapper.class, UserExtraInfoMapper.class, OrderStatusMapper.class, CustomerMapper.class, OrderServiceMapper.class, PostOfficeMapper.class})
public interface OrderMainMapper extends EntityMapper<OrderMainDTO, OrderMain> {

    @Mapping(source = "orderPickup.id", target = "orderPickupId")
    @Mapping(source = "orderConsignee.id", target = "orderConsigneeId")
    @Mapping(source = "orderFee.id", target = "orderFeeId")
    @Mapping(source = "orderDelivery.id", target = "orderDeliveryId")
    @Mapping(source = "createUser.id", target = "createUserId")
    @Mapping(source = "orderStatus.id", target = "orderStatusId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "mainService.id", target = "mainServiceId")
    @Mapping(source = "createPostOffice.id", target = "createPostOfficeId")
    @Mapping(source = "currentPostOffice.id", target = "currentPostOfficeId")
    OrderMainDTO toDto(OrderMain orderMain);

    @Mapping(source = "orderPickupId", target = "orderPickup")
    @Mapping(source = "orderConsigneeId", target = "orderConsignee")
    @Mapping(source = "orderFeeId", target = "orderFee")
    @Mapping(source = "orderDeliveryId", target = "orderDelivery")
    @Mapping(source = "createUserId", target = "createUser")
    @Mapping(source = "orderStatusId", target = "orderStatus")
    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "mainServiceId", target = "mainService")
    @Mapping(source = "createPostOfficeId", target = "createPostOffice")
    @Mapping(source = "currentPostOfficeId", target = "currentPostOffice")
    OrderMain toEntity(OrderMainDTO orderMainDTO);

    default OrderMain fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderMain orderMain = new OrderMain();
        orderMain.setId(id);
        return orderMain;
    }
}
