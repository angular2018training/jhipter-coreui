package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationInsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationInsurance and its DTO QuotationInsuranceDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, DistrictTypeMapper.class, QuotationMapper.class})
public interface QuotationInsuranceMapper extends EntityMapper<QuotationInsuranceDTO, QuotationInsurance> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "districtType.id", target = "districtTypeId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationInsuranceDTO toDto(QuotationInsurance quotationInsurance);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "districtTypeId", target = "districtType")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationInsurance toEntity(QuotationInsuranceDTO quotationInsuranceDTO);

    default QuotationInsurance fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationInsurance quotationInsurance = new QuotationInsurance();
        quotationInsurance.setId(id);
        return quotationInsurance;
    }
}
