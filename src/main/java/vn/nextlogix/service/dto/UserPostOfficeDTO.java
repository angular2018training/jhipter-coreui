package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the UserPostOffice entity.
 */
public class UserPostOfficeDTO implements Serializable {

    private Long id;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long postOfficeId;

    private PostOfficeDTO  postOfficeDTO;


    private Set<UserGroupDTO> userGroups = new HashSet<>();

    private Long userExtraInfoParentId;

    private UserExtraInfoDTO  userExtraInfoParentDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyDTO getCompanyDTO() {
        return this.companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO ) {
        this.companyDTO = companyDTO;
    }
    public Long getCompanyId() {
        return companyId;
        }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
        }


    public PostOfficeDTO getPostOfficeDTO() {
        return this.postOfficeDTO;
    }

    public void setPostOfficeDTO(PostOfficeDTO postOfficeDTO ) {
        this.postOfficeDTO = postOfficeDTO;
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

    public UserExtraInfoDTO getUserExtraInfoParentDTO() {
        return this.userExtraInfoParentDTO;
    }

    public void setUserExtraInfoParentDTO(UserExtraInfoDTO userExtraInfoParentDTO ) {
        this.userExtraInfoParentDTO = userExtraInfoParentDTO;
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

        UserPostOfficeDTO userPostOfficeDTO = (UserPostOfficeDTO) o;
        if(userPostOfficeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userPostOfficeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserPostOfficeDTO{" +
            "id=" + getId() +
            "}";
    }
}
