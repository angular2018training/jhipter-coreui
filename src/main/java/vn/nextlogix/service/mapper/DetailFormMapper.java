package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.DetailFormDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DetailForm and its DTO DetailFormDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceMapper.class, DistrictMapper.class, MasterFormMapper.class})
public interface DetailFormMapper extends EntityMapper<DetailFormDTO, DetailForm> {

    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "masterFormParent.id", target = "masterFormParentId")
    DetailFormDTO toDto(DetailForm detailForm);

    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    @Mapping(source = "masterFormParentId", target = "masterFormParent")
    DetailForm toEntity(DetailFormDTO detailFormDTO);

    default DetailForm fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailForm detailForm = new DetailForm();
        detailForm.setId(id);
        return detailForm;
    }
}
