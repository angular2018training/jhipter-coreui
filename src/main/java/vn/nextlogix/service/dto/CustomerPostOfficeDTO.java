package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerPostOffice entity.
 */
public class CustomerPostOfficeDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private Instant createDate;

    private Long customerId;

    private Long postOfficeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(Long postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerPostOfficeDTO customerPostOfficeDTO = (CustomerPostOfficeDTO) o;
        if(customerPostOfficeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerPostOfficeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerPostOfficeDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
