package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationItem and its DTO QuotationItemDTO.
 */
@Mapper(componentModel = "spring", uses = {QuotationMapper.class})
public interface QuotationItemMapper extends EntityMapper<QuotationItemDTO, QuotationItem> {

    @Mapping(source = "quotation.id", target = "quotationId")
    QuotationItemDTO toDto(QuotationItem quotationItem);

    @Mapping(source = "quotationId", target = "quotation")
    QuotationItem toEntity(QuotationItemDTO quotationItemDTO);

    default QuotationItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationItem quotationItem = new QuotationItem();
        quotationItem.setId(id);
        return quotationItem;
    }
}
