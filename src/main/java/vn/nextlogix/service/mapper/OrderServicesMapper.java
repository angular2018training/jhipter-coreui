package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderServices and its DTO OrderServicesDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, OrderServicesTypeMapper.class, QuotationMapper.class})
public interface OrderServicesMapper extends EntityMapper<OrderServicesDTO, OrderServices> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "orderServicesType.id", target = "orderServicesTypeId")
    OrderServicesDTO toDto(OrderServices orderServices);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "orderServicesTypeId", target = "orderServicesType")
    OrderServices toEntity(OrderServicesDTO orderServicesDTO);

    default OrderServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderServices orderServices = new OrderServices();
        orderServices.setId(id);
        return orderServices;
    }
}
