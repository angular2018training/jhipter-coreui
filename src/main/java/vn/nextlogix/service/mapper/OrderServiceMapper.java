package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderService and its DTO OrderServiceDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface OrderServiceMapper extends EntityMapper<OrderServiceDTO, OrderService> {

    @Mapping(source = "company.id", target = "companyId")
    OrderServiceDTO toDto(OrderService orderService);

    @Mapping(source = "companyId", target = "company")
    OrderService toEntity(OrderServiceDTO orderServiceDTO);

    default OrderService fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderService orderService = new OrderService();
        orderService.setId(id);
        return orderService;
    }
}
