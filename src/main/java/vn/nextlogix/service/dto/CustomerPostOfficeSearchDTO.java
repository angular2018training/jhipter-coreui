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
public class CustomerPostOfficeSearchDTO implements Serializable {

private Long id;


private String code;


private Boolean isActive;


private Instant createDate;

private Long customerId;



private Long companyId;



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


public Long getCustomerId() {
    return customerId;
    }

public void setCustomerId(Long customerId) {
    this.customerId = customerId;
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


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

CustomerPostOfficeSearchDTO customerPostOfficeSearchDTO = (CustomerPostOfficeSearchDTO) o;
    if(customerPostOfficeSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), customerPostOfficeSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "CustomerPostOfficeSearchDTO{" +
    "id=" + getId() +
    ", code='" + getCode() + "'" +
    ", isActive='" + isIsActive() + "'" +
    ", createDate='" + getCreateDate() + "'" +
    "}";
    }
    }
