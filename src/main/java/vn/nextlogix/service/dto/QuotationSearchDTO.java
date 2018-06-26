    package vn.nextlogix.service.dto;


    import java.time.Instant;
    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the Quotation entity.
 */
public class QuotationSearchDTO implements Serializable {

private Long id;


private String name;


private Boolean isActive;


private Instant createDate;

private Long companyId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public String getName() {
    return name;
    }

public void setName(String name) {
    this.name = name;
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


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

QuotationSearchDTO quotationSearchDTO = (QuotationSearchDTO) o;
    if(quotationSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), quotationSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "QuotationSearchDTO{" +
    "id=" + getId() +
    ", name='" + getName() + "'" +
    ", isActive='" + isIsActive() + "'" +
    ", createDate='" + getCreateDate() + "'" +
    "}";
    }
    }