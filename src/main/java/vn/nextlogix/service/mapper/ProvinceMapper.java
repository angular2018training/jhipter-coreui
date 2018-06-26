package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.ProvinceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Province and its DTO ProvinceDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, Province> {

    @Mapping(source = "company.id", target = "companyId")
    ProvinceDTO toDto(Province province);

    @Mapping(source = "companyId", target = "company")
    Province toEntity(ProvinceDTO provinceDTO);

    default Province fromId(Long id) {
        if (id == null) {
            return null;
        }
        Province province = new Province();
        province.setId(id);
        return province;
    }
}
