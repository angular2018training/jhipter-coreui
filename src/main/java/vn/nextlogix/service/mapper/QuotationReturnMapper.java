package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationReturnDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationReturn and its DTO QuotationReturnDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, DistrictTypeMapper.class, RegionMapper.class, QuotationMapper.class})
public interface QuotationReturnMapper extends EntityMapper<QuotationReturnDTO, QuotationReturn> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "districtType.id", target = "districtTypeId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationReturnDTO toDto(QuotationReturn quotationReturn);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "districtTypeId", target = "districtType")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationReturn toEntity(QuotationReturnDTO quotationReturnDTO);

    default QuotationReturn fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationReturn quotationReturn = new QuotationReturn();
        quotationReturn.setId(id);
        return quotationReturn;
    }
}
