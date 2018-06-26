package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderUserRouteTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderUserRouteType and its DTO OrderUserRouteTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface OrderUserRouteTypeMapper extends EntityMapper<OrderUserRouteTypeDTO, OrderUserRouteType> {

    @Mapping(source = "company.id", target = "companyId")
    OrderUserRouteTypeDTO toDto(OrderUserRouteType orderUserRouteType);

    @Mapping(source = "companyId", target = "company")
    OrderUserRouteType toEntity(OrderUserRouteTypeDTO orderUserRouteTypeDTO);

    default OrderUserRouteType fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderUserRouteType orderUserRouteType = new OrderUserRouteType();
        orderUserRouteType.setId(id);
        return orderUserRouteType;
    }
}
