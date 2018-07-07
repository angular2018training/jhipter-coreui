package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationCodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationCod and its DTO QuotationCodDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, DistrictTypeMapper.class, QuotationMapper.class})
public interface QuotationCodMapper extends EntityMapper<QuotationCodDTO, QuotationCod> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "districtType.id", target = "districtTypeId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationCodDTO toDto(QuotationCod quotationCod);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "districtTypeId", target = "districtType")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationCod toEntity(QuotationCodDTO quotationCodDTO);

    default QuotationCod fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationCod quotationCod = new QuotationCod();
        quotationCod.setId(id);
        return quotationCod;
    }
}
