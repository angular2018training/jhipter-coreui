package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerLegalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerLegal and its DTO CustomerLegalDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, ProvinceMapper.class, DistrictMapper.class, FileUploadMapper.class})
public interface CustomerLegalMapper extends EntityMapper<CustomerLegalDTO, CustomerLegal> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "district.id", target = "districtId")
    CustomerLegalDTO toDto(CustomerLegal customerLegal);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "districtId", target = "district")
    CustomerLegal toEntity(CustomerLegalDTO customerLegalDTO);

    default CustomerLegal fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerLegal customerLegal = new CustomerLegal();
        customerLegal.setId(id);
        return customerLegal;
    }
}
