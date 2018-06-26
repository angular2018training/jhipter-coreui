package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the UserPosition entity.
 */
public class UserPositionDTO implements Serializable {

    private Long id;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long postOfficeId;

    private PostOfficeDTO  postOfficeDTO;


    private Long positionId;

    private PositionDTO  positionDTO;


    private Set<UserGroupDTO> userGroups = new HashSet<>();

    private Long userExtraInfoId;

    private UserExtraInfoDTO  userExtraInfoDTO;


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


    public PositionDTO getPositionDTO() {
        return this.positionDTO;
    }

    public void setPositionDTO(PositionDTO positionDTO ) {
        this.positionDTO = positionDTO;
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

    public UserExtraInfoDTO getUserExtraInfoDTO() {
        return this.userExtraInfoDTO;
    }

    public void setUserExtraInfoDTO(UserExtraInfoDTO userExtraInfoDTO ) {
        this.userExtraInfoDTO = userExtraInfoDTO;
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

        UserPositionDTO userPositionDTO = (UserPositionDTO) o;
        if(userPositionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userPositionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserPositionDTO{" +
            "id=" + getId() +
            "}";
    }
}
