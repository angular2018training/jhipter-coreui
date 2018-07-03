package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerWarehouseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerWarehouse and its DTO CustomerWarehouseDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, WarehouseMapper.class, CustomerMapper.class})
public interface CustomerWarehouseMapper extends EntityMapper<CustomerWarehouseDTO, CustomerWarehouse> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "customerParent.id", target = "customerParentId")
    CustomerWarehouseDTO toDto(CustomerWarehouse customerWarehouse);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "customerParentId", target = "customerParent")
    CustomerWarehouse toEntity(CustomerWarehouseDTO customerWarehouseDTO);

    default CustomerWarehouse fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerWarehouse customerWarehouse = new CustomerWarehouse();
        customerWarehouse.setId(id);
        return customerWarehouse;
    }
}
