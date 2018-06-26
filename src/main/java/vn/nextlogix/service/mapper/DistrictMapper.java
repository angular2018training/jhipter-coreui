package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.DistrictDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity District and its DTO DistrictDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, ProvinceMapper.class})
public interface DistrictMapper extends EntityMapper<DistrictDTO, District> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "province.id", target = "provinceId")
    DistrictDTO toDto(District district);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "provinceId", target = "province")
    District toEntity(DistrictDTO districtDTO);

    default District fromId(Long id) {
        if (id == null) {
            return null;
        }
        District district = new District();
        district.setId(id);
        return district;
    }
}
