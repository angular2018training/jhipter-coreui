package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.OrderFeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderFee and its DTO OrderFeeDTO.
 */
@Mapper(componentModel = "spring", uses = {QuotationMapper.class})
public interface OrderFeeMapper extends EntityMapper<OrderFeeDTO, OrderFee> {

    @Mapping(source = "quotation.id", target = "quotationId")
    OrderFeeDTO toDto(OrderFee orderFee);

    @Mapping(source = "quotationId", target = "quotation")
    OrderFee toEntity(OrderFeeDTO orderFeeDTO);

    default OrderFee fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderFee orderFee = new OrderFee();
        orderFee.setId(id);
        return orderFee;
    }
}
