    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the UserPosition entity.
 */
public class UserPositionSearchDTO implements Serializable {

private Long id;

private Long companyId;



private Long postOfficeId;



private Long positionId;



private Set<UserGroupDTO> userGroups = new HashSet<>();

private Long userExtraInfoId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getPostOfficeId() {
    return postOfficeId;
    }

public void setPostOfficeId(Long postOfficeId) {
    this.postOfficeId = postOfficeId;
    }



public Long getPositionId() {
    return positionId;
    }

public void setPositionId(Long positionId) {
    this.positionId = positionId;
    }


public Set<UserGroupDTO> getUserGroups() {
    return userGroups;
    }

public void setUserGroups(Set<UserGroupDTO> userGroups) {
    this.userGroups = userGroups;
    }


public Long getUserExtraInfoId() {
    return userExtraInfoId;
    }

public void setUserExtraInfoId(Long userExtraInfoId) {
    this.userExtraInfoId = userExtraInfoId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

UserPositionSearchDTO userPositionSearchDTO = (UserPositionSearchDTO) o;
    if(userPositionSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), userPositionSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "UserPositionSearchDTO{" +
    "id=" + getId() +
    "}";
    }
    }
