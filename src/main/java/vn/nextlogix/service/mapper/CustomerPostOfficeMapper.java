package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerPostOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerPostOffice and its DTO CustomerPostOfficeDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CompanyMapper.class, PostOfficeMapper.class})
public interface CustomerPostOfficeMapper extends EntityMapper<CustomerPostOfficeDTO, CustomerPostOffice> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "postOffice.id", target = "postOfficeId")
    CustomerPostOfficeDTO toDto(CustomerPostOffice customerPostOffice);

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "postOfficeId", target = "postOffice")
    CustomerPostOffice toEntity(CustomerPostOfficeDTO customerPostOfficeDTO);

    default CustomerPostOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerPostOffice customerPostOffice = new CustomerPostOffice();
        customerPostOffice.setId(id);
        return customerPostOffice;
    }
}
