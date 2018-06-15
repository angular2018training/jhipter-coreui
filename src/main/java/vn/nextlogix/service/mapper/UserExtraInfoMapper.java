package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.UserExtraInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserExtraInfo and its DTO UserExtraInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {RoleMapper.class, UserGroupMapper.class})
public interface UserExtraInfoMapper extends EntityMapper<UserExtraInfoDTO, UserExtraInfo> {


    @Mapping(target = "userPositions", ignore = true)
    UserExtraInfo toEntity(UserExtraInfoDTO userExtraInfoDTO);

    default UserExtraInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserExtraInfo userExtraInfo = new UserExtraInfo();
        userExtraInfo.setId(id);
        return userExtraInfo;
    }
}
