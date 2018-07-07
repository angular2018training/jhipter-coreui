package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationDomesticDeliveryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationDomesticDelivery and its DTO QuotationDomesticDeliveryDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, DistrictTypeMapper.class, RegionMapper.class, WeightMapper.class, QuotationMapper.class})
public interface QuotationDomesticDeliveryMapper extends EntityMapper<QuotationDomesticDeliveryDTO, QuotationDomesticDelivery> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "districtType.id", target = "districtTypeId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "weight.id", target = "weightId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationDomesticDeliveryDTO toDto(QuotationDomesticDelivery quotationDomesticDelivery);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "districtTypeId", target = "districtType")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "weightId", target = "weight")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationDomesticDelivery toEntity(QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO);

    default QuotationDomesticDelivery fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationDomesticDelivery quotationDomesticDelivery = new QuotationDomesticDelivery();
        quotationDomesticDelivery.setId(id);
        return quotationDomesticDelivery;
    }
}
