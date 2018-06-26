    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the CustomerLegal entity.
 */
public class CustomerLegalSearchDTO implements Serializable {

private Long id;


private String contractCustomerName;


private String contractAddress;


private String contractContactName;


private String contractContactPhone;


private String taxCode;


private String contractExpirationDate;

private Long companyId;



private Long provinceId;



private Long districtId;



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

public String getContractAddress() {
    return contractAddress;
    }

public void setContractAddress(String contractAddress) {
    this.contractAddress = contractAddress;
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


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getProvinceId() {
    return provinceId;
    }

public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
    }



public Long getDistrictId() {
    return districtId;
    }

public void setDistrictId(Long districtId) {
    this.districtId = districtId;
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

CustomerLegalSearchDTO customerLegalSearchDTO = (CustomerLegalSearchDTO) o;
    if(customerLegalSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), customerLegalSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "CustomerLegalSearchDTO{" +
    "id=" + getId() +
    ", contractCustomerName='" + getContractCustomerName() + "'" +
    ", contractAddress='" + getContractAddress() + "'" +
    ", contractContactName='" + getContractContactName() + "'" +
    ", contractContactPhone='" + getContractContactPhone() + "'" +
    ", taxCode='" + getTaxCode() + "'" +
    ", contractExpirationDate='" + getContractExpirationDate() + "'" +
    "}";
    }
    }
