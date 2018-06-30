package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.MasterFormDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MasterForm and its DTO MasterFormDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceMapper.class, DistrictMapper.class})
public interface MasterFormMapper extends EntityMapper<MasterFormDTO, MasterForm> {

    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    MasterFormDTO toDto(MasterForm masterForm);

    @Mapping(target = "detailFormDetailLists", ignore = true)
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    MasterForm toEntity(MasterFormDTO masterFormDTO);

    default MasterForm fromId(Long id) {
        if (id == null) {
            return null;
        }
        MasterForm masterForm = new MasterForm();
        masterForm.setId(id);
        return masterForm;
    }
}
