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
    private Boolean isActive;

    @NotNull
    private Instant createDate;

    private Long customerId;

    private CustomerDTO  customerDTO;


    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long postOfficeId;

    private PostOfficeDTO  postOfficeDTO;


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

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO ) {
        this.customerDTO = customerDTO;
    }
    public Long getCustomerId() {
        return customerId;
        }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
            ", isActive='" + isIsActive() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
