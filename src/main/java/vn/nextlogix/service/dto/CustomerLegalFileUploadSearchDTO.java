    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the CustomerLegalFileUpload entity.
 */
public class CustomerLegalFileUploadSearchDTO implements Serializable {

private Long id;

private Long companyId;



private Long fileUploadId;



private Long customerLegalParentId;



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



public Long getFileUploadId() {
    return fileUploadId;
    }

public void setFileUploadId(Long fileUploadId) {
    this.fileUploadId = fileUploadId;
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

CustomerLegalFileUploadSearchDTO customerLegalFileUploadSearchDTO = (CustomerLegalFileUploadSearchDTO) o;
    if(customerLegalFileUploadSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), customerLegalFileUploadSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "CustomerLegalFileUploadSearchDTO{" +
    "id=" + getId() +
    "}";
    }
    }
