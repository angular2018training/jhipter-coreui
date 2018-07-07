package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationGiveBackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationGiveBack and its DTO QuotationGiveBackDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, DistrictTypeMapper.class, RegionMapper.class, QuotationMapper.class})
public interface QuotationGiveBackMapper extends EntityMapper<QuotationGiveBackDTO, QuotationGiveBack> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "districtType.id", target = "districtTypeId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationGiveBackDTO toDto(QuotationGiveBack quotationGiveBack);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "districtTypeId", target = "districtType")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationGiveBack toEntity(QuotationGiveBackDTO quotationGiveBackDTO);

    default QuotationGiveBack fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationGiveBack quotationGiveBack = new QuotationGiveBack();
        quotationGiveBack.setId(id);
        return quotationGiveBack;
    }
}
