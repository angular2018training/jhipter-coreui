package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderService and its DTO OrderServiceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderServiceMapper extends EntityMapper<OrderServiceDTO, OrderService> {



    default OrderService fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderService orderService = new OrderService();
        orderService.setId(id);
        return orderService;
    }
}
