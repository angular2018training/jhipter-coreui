package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerSourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerSource and its DTO CustomerSourceDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface CustomerSourceMapper extends EntityMapper<CustomerSourceDTO, CustomerSource> {

    @Mapping(source = "company.id", target = "companyId")
    CustomerSourceDTO toDto(CustomerSource customerSource);

    @Mapping(source = "companyId", target = "company")
    CustomerSource toEntity(CustomerSourceDTO customerSourceDTO);

    default CustomerSource fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerSource customerSource = new CustomerSource();
        customerSource.setId(id);
        return customerSource;
    }
}
