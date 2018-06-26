package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.UserGroupDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;

/**
 * Mapper for the entity UserGroup and its DTO UserGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserGroupMapper extends EntityMapper<UserGroupDTO, UserGroup> {

    @Mapping(source = "company.id", target = "companyId")
    UserGroupDTO toDto(UserGroup userGroup);

    @Mapping(source = "companyId", target = "company")
    UserGroup toEntity(UserGroupDTO userGroupDTO);

    default UserGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserGroup userGroup = new UserGroup();
        userGroup.setId(id);
        return userGroup;
    }

    default Authority fromId(String authorityName) {
    	 if (authorityName == null) {
             return null;
         }
         Authority authority = new Authority();
         authority.setName(authorityName);
         return authority;
    }

    default Set<Authority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }

  default Set<String> authoritiesToStrings(Set<Authority> authorities) {
    return authorities.stream().map(authority -> {
      return authority.getName();
    }).collect(Collectors.toSet());
  }


}
