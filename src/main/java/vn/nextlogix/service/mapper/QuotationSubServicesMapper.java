package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationSubServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationSubServices and its DTO QuotationSubServicesDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, OrderSubServicesTypeMapper.class, OrderSubServicesMapper.class, QuotationMapper.class})
public interface QuotationSubServicesMapper extends EntityMapper<QuotationSubServicesDTO, QuotationSubServices> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "orderSubServicesType.id", target = "orderSubServicesTypeId")
    @Mapping(source = "orderSubServices.id", target = "orderSubServicesId")
    @Mapping(source = "quotationParent.id", target = "quotationParentId")
    QuotationSubServicesDTO toDto(QuotationSubServices quotationSubServices);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "orderSubServicesTypeId", target = "orderSubServicesType")
    @Mapping(source = "orderSubServicesId", target = "orderSubServices")
    @Mapping(source = "quotationParentId", target = "quotationParent")
    QuotationSubServices toEntity(QuotationSubServicesDTO quotationSubServicesDTO);

    default QuotationSubServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationSubServices quotationSubServices = new QuotationSubServices();
        quotationSubServices.setId(id);
        return quotationSubServices;
    }
}
