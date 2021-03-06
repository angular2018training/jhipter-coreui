package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderStatus and its DTO OrderStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface OrderStatusMapper extends EntityMapper<OrderStatusDTO, OrderStatus> {

    @Mapping(source = "company.id", target = "companyId")
    OrderStatusDTO toDto(OrderStatus orderStatus);

    @Mapping(source = "companyId", target = "company")
    OrderStatus toEntity(OrderStatusDTO orderStatusDTO);

    default OrderStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(id);
        return orderStatus;
    }
}
