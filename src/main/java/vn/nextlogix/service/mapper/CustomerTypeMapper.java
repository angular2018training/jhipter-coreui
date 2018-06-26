package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerType and its DTO CustomerTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface CustomerTypeMapper extends EntityMapper<CustomerTypeDTO, CustomerType> {

    @Mapping(source = "company.id", target = "companyId")
    CustomerTypeDTO toDto(CustomerType customerType);

    @Mapping(source = "companyId", target = "company")
    CustomerType toEntity(CustomerTypeDTO customerTypeDTO);

    default CustomerType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerType customerType = new CustomerType();
        customerType.setId(id);
        return customerType;
    }
}
