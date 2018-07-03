package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerLegalFileUpload entity.
 */
public class CustomerLegalFileUploadDTO implements Serializable {

    private Long id;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long fileUploadId;

    private FileUploadDTO  fileUploadDTO;


    private Long customerLegalParentId;

    private CustomerLegalDTO  customerLegalParentDTO;


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


    public FileUploadDTO getFileUploadDTO() {
        return this.fileUploadDTO;
    }

    public void setFileUploadDTO(FileUploadDTO fileUploadDTO ) {
        this.fileUploadDTO = fileUploadDTO;
    }
    public Long getFileUploadId() {
        return fileUploadId;
        }

    public void setFileUploadId(Long fileUploadId) {
        this.fileUploadId = fileUploadId;
        }


    public CustomerLegalDTO getCustomerLegalParentDTO() {
        return this.customerLegalParentDTO;
    }

    public void setCustomerLegalParentDTO(CustomerLegalDTO customerLegalParentDTO ) {
        this.customerLegalParentDTO = customerLegalParentDTO;
    }
    public Long getCustomerLegalParentId() {
        return customerLegalParentId;
        }

    public void setCustomerLegalParentId(Long customerLegalId) {
        this.customerLegalParentId = customerLegalId;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerLegalFileUploadDTO customerLegalFileUploadDTO = (CustomerLegalFileUploadDTO) o;
        if(customerLegalFileUploadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerLegalFileUploadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerLegalFileUploadDTO{" +
            "id=" + getId() +
            "}";
    }
}
