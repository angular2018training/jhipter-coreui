package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.UserPositionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserPosition and its DTO UserPositionDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, PostOfficeMapper.class, PositionMapper.class, UserGroupMapper.class, UserExtraInfoMapper.class})
public interface UserPositionMapper extends EntityMapper<UserPositionDTO, UserPosition> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "postOffice.id", target = "postOfficeId")
    @Mapping(source = "position.id", target = "positionId")
    @Mapping(source = "userExtraInfo.id", target = "userExtraInfoId")
    UserPositionDTO toDto(UserPosition userPosition);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "postOfficeId", target = "postOffice")
    @Mapping(source = "positionId", target = "position")
    @Mapping(source = "userExtraInfoId", target = "userExtraInfo")
    UserPosition toEntity(UserPositionDTO userPositionDTO);

    default UserPosition fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserPosition userPosition = new UserPosition();
        userPosition.setId(id);
        return userPosition;
    }
}
