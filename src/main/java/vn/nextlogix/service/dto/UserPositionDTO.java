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

    private Long postOfficeId;

    private Long positionId;

    private Long userExtraInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
