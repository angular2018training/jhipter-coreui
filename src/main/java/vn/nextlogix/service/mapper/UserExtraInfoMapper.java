package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.UserExtraInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserExtraInfo and its DTO UserExtraInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserExtraInfoMapper extends EntityMapper<UserExtraInfoDTO, UserExtraInfo> {

    @Mapping(source = "company.id", target = "companyId")
    UserExtraInfoDTO toDto(UserExtraInfo userExtraInfo);

    @Mapping(source = "companyId", target = "company")
    UserExtraInfo toEntity(UserExtraInfoDTO userExtraInfoDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLoginDate", ignore = true)
    void updateEntity(UserExtraInfoDTO userExtraInfoDTO,@MappingTarget UserExtraInfo entity);
    

    default UserExtraInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserExtraInfo userExtraInfo = new UserExtraInfo();
        userExtraInfo.setId(id);
        return userExtraInfo;
    }
}
