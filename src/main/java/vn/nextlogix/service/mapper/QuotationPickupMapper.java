package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationPickupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationPickup and its DTO QuotationPickupDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, ProvinceMapper.class, DistrictTypeMapper.class, QuotationMapper.class})
public interface QuotationPickupMapper extends EntityMapper<QuotationPickupDTO, QuotationPickup> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "districtType.id", target = "districtTypeId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationPickupDTO toDto(QuotationPickup quotationPickup);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtTypeId", target = "districtType")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationPickup toEntity(QuotationPickupDTO quotationPickupDTO);

    default QuotationPickup fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationPickup quotationPickup = new QuotationPickup();
        quotationPickup.setId(id);
        return quotationPickup;
    }
}
