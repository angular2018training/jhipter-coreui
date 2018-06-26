package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationItemTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationItemType and its DTO QuotationItemTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface QuotationItemTypeMapper extends EntityMapper<QuotationItemTypeDTO, QuotationItemType> {

    @Mapping(source = "company.id", target = "companyId")
    QuotationItemTypeDTO toDto(QuotationItemType quotationItemType);

    @Mapping(source = "companyId", target = "company")
    QuotationItemType toEntity(QuotationItemTypeDTO quotationItemTypeDTO);

    default QuotationItemType fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationItemType quotationItemType = new QuotationItemType();
        quotationItemType.setId(id);
        return quotationItemType;
    }
}
