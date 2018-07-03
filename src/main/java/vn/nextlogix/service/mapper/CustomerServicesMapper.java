package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerServices and its DTO CustomerServicesDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, OrderServicesMapper.class, QuotationMapper.class, CustomerMapper.class})
public interface CustomerServicesMapper extends EntityMapper<CustomerServicesDTO, CustomerServices> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "orderServices.id", target = "orderServicesId")
    @Mapping(source = "quotation.id", target = "quotationId")
    @Mapping(source = "customerParent.id", target = "customerParentId")
    CustomerServicesDTO toDto(CustomerServices customerServices);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "orderServicesId", target = "orderServices")
    @Mapping(source = "quotationId", target = "quotation")
    @Mapping(source = "customerParentId", target = "customerParent")
    CustomerServices toEntity(CustomerServicesDTO customerServicesDTO);

    default CustomerServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerServices customerServices = new CustomerServices();
        customerServices.setId(id);
        return customerServices;
    }
}
