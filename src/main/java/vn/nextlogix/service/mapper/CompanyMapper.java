package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Company and its DTO CompanyDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceMapper.class, DistrictMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    CompanyDTO toDto(Company company);

    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    Company toEntity(CompanyDTO companyDTO);

    default Company fromId(Long id) {
        if (id == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
