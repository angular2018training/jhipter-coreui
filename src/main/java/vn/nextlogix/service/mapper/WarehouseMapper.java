package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.WarehouseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Warehouse and its DTO WarehouseDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, ProvinceMapper.class, DistrictMapper.class, WardMapper.class})
public interface WarehouseMapper extends EntityMapper<WarehouseDTO, Warehouse> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "ward.id", target = "wardId")
    WarehouseDTO toDto(Warehouse warehouse);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    @Mapping(source = "wardId", target = "ward")
    Warehouse toEntity(WarehouseDTO warehouseDTO);

    default Warehouse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        return warehouse;
    }
}
