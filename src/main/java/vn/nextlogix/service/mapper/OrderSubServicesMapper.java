package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderSubServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderSubServices and its DTO OrderSubServicesDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface OrderSubServicesMapper extends EntityMapper<OrderSubServicesDTO, OrderSubServices> {

    @Mapping(source = "company.id", target = "companyId")
    OrderSubServicesDTO toDto(OrderSubServices orderSubServices);

    @Mapping(source = "companyId", target = "company")
    OrderSubServices toEntity(OrderSubServicesDTO orderSubServicesDTO);

    default OrderSubServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderSubServices orderSubServices = new OrderSubServices();
        orderSubServices.setId(id);
        return orderSubServices;
    }
}
