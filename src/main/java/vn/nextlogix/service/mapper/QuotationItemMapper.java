package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationItem and its DTO QuotationItemDTO.
 */
@Mapper(componentModel = "spring", uses = {QuotationMapper.class, CompanyMapper.class})
public interface QuotationItemMapper extends EntityMapper<QuotationItemDTO, QuotationItem> {

    @Mapping(source = "quotation.id", target = "quotationId")
    @Mapping(source = "company.id", target = "companyId")
    QuotationItemDTO toDto(QuotationItem quotationItem);

    @Mapping(source = "quotationId", target = "quotation")
    @Mapping(source = "companyId", target = "company")
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
