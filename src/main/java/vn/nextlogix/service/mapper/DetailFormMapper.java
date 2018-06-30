package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.DetailFormDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DetailForm and its DTO DetailFormDTO.
 */
@Mapper(componentModel = "spring", uses = {MasterFormMapper.class, ProvinceMapper.class, DistrictMapper.class})
public interface DetailFormMapper extends EntityMapper<DetailFormDTO, DetailForm> {

    @Mapping(source = "masterForm.id", target = "masterFormId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    DetailFormDTO toDto(DetailForm detailForm);

    @Mapping(source = "masterFormId", target = "masterForm")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
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
