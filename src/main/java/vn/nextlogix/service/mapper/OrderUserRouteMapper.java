package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderUserRouteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderUserRoute and its DTO OrderUserRouteDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, UserExtraInfoMapper.class, ProvinceMapper.class, DistrictMapper.class, OrderUserRouteTypeMapper.class, CustomerMapper.class})
public interface OrderUserRouteMapper extends EntityMapper<OrderUserRouteDTO, OrderUserRoute> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "orderUserRouteType.id", target = "orderUserRouteTypeId")
    @Mapping(source = "ward.id", target = "wardId")
    @Mapping(source = "customer.id", target = "customerId")
    OrderUserRouteDTO toDto(OrderUserRoute orderUserRoute);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    @Mapping(source = "orderUserRouteTypeId", target = "orderUserRouteType")
    @Mapping(source = "wardId", target = "ward")
    @Mapping(source = "customerId", target = "customer")
    OrderUserRoute toEntity(OrderUserRouteDTO orderUserRouteDTO);

    default OrderUserRoute fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderUserRoute orderUserRoute = new OrderUserRoute();
        orderUserRoute.setId(id);
        return orderUserRoute;
    }
}
