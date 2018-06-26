package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderPickupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderPickup and its DTO OrderPickupDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, WardMapper.class, DistrictMapper.class, ProvinceMapper.class, UserExtraInfoMapper.class})
public interface OrderPickupMapper extends EntityMapper<OrderPickupDTO, OrderPickup> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "ward.id", target = "wardId")
    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "pickupUser.id", target = "pickupUserId")
    OrderPickupDTO toDto(OrderPickup orderPickup);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "wardId", target = "ward")
    @Mapping(source = "districtId", target = "district")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "pickupUserId", target = "pickupUser")
    OrderPickup toEntity(OrderPickupDTO orderPickupDTO);

    default OrderPickup fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderPickup orderPickup = new OrderPickup();
        orderPickup.setId(id);
        return orderPickup;
    }
}
