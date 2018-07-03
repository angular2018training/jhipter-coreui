    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the UserPostOffice entity.
 */
public class UserPostOfficeSearchDTO implements Serializable {

private Long id;

private Long companyId;



private Long postOfficeId;



private Set<UserGroupDTO> userGroups = new HashSet<>();

private Long userExtraInfoParentId;



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


public Set<UserGroupDTO> getUserGroups() {
    return userGroups;
    }

public void setUserGroups(Set<UserGroupDTO> userGroups) {
    this.userGroups = userGroups;
    }


public Long getUserExtraInfoParentId() {
    return userExtraInfoParentId;
    }

public void setUserExtraInfoParentId(Long userExtraInfoId) {
    this.userExtraInfoParentId = userExtraInfoId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

UserPostOfficeSearchDTO userPostOfficeSearchDTO = (UserPostOfficeSearchDTO) o;
    if(userPostOfficeSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), userPostOfficeSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "UserPostOfficeSearchDTO{" +
    "id=" + getId() +
    "}";
    }
    }
