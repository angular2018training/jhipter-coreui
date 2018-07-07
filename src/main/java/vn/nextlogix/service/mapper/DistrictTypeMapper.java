package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.DistrictTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DistrictType and its DTO DistrictTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface DistrictTypeMapper extends EntityMapper<DistrictTypeDTO, DistrictType> {

    @Mapping(source = "company.id", target = "companyId")
    DistrictTypeDTO toDto(DistrictType districtType);

    @Mapping(source = "companyId", target = "company")
    DistrictType toEntity(DistrictTypeDTO districtTypeDTO);

    default DistrictType fromId(Long id) {
        if (id == null) {
            return null;
        }
        DistrictType districtType = new DistrictType();
        districtType.setId(id);
        return districtType;
    }
}
