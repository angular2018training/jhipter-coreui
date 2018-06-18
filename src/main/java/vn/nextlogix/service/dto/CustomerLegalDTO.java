package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerLegal entity.
 */
public class CustomerLegalDTO implements Serializable {

    private Long id;

    @NotNull
    private String contractCustomerName;

    private String contractContactName;

    private String contractContactPhone;

    private String taxCode;

    @NotNull
    private String contractExpirationDate;

    private Set<FileUploadDTO> fileUploads = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractCustomerName() {
        return contractCustomerName;
    }

    public void setContractCustomerName(String contractCustomerName) {
        this.contractCustomerName = contractCustomerName;
    }

    public String getContractContactName() {
        return contractContactName;
    }

    public void setContractContactName(String contractContactName) {
        this.contractContactName = contractContactName;
    }

    public String getContractContactPhone() {
        return contractContactPhone;
    }

    public void setContractContactPhone(String contractContactPhone) {
        this.contractContactPhone = contractContactPhone;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(String contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }

    public Set<FileUploadDTO> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(Set<FileUploadDTO> fileUploads) {
        this.fileUploads = fileUploads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerLegalDTO customerLegalDTO = (CustomerLegalDTO) o;
        if(customerLegalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerLegalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerLegalDTO{" +
            "id=" + getId() +
            ", contractCustomerName='" + getContractCustomerName() + "'" +
            ", contractContactName='" + getContractContactName() + "'" +
            ", contractContactPhone='" + getContractContactPhone() + "'" +
            ", taxCode='" + getTaxCode() + "'" +
            ", contractExpirationDate='" + getContractExpirationDate() + "'" +
            "}";
    }
}
