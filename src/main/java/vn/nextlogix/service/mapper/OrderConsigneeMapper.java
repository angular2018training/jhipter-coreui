package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderConsigneeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderConsignee and its DTO OrderConsigneeDTO.
 */
@Mapper(componentModel = "spring", uses = {DistrictMapper.class, ProvinceMapper.class})
public interface OrderConsigneeMapper extends EntityMapper<OrderConsigneeDTO, OrderConsignee> {

    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "province.id", target = "provinceId")
    OrderConsigneeDTO toDto(OrderConsignee orderConsignee);

    @Mapping(source = "districtId", target = "district")
    @Mapping(source = "provinceId", target = "province")
    OrderConsignee toEntity(OrderConsigneeDTO orderConsigneeDTO);

    default OrderConsignee fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderConsignee orderConsignee = new OrderConsignee();
        orderConsignee.setId(id);
        return orderConsignee;
    }
}
