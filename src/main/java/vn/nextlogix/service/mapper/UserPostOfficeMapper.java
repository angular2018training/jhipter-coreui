package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.UserPostOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserPostOffice and its DTO UserPostOfficeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, PostOfficeMapper.class, UserGroupMapper.class, UserExtraInfoMapper.class})
public interface UserPostOfficeMapper extends EntityMapper<UserPostOfficeDTO, UserPostOffice> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "postOffice.id", target = "postOfficeId")
    @Mapping(source = "userExtraInfoParent.id", target = "userExtraInfoParentId")
    UserPostOfficeDTO toDto(UserPostOffice userPostOffice);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "postOfficeId", target = "postOffice")
    @Mapping(source = "userExtraInfoParentId", target = "userExtraInfoParent")
    UserPostOffice toEntity(UserPostOfficeDTO userPostOfficeDTO);

    default UserPostOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserPostOffice userPostOffice = new UserPostOffice();
        userPostOffice.setId(id);
        return userPostOffice;
    }
}
