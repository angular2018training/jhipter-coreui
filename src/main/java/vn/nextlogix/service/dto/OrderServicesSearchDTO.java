    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the OrderServices entity.
 */
public class OrderServicesSearchDTO implements Serializable {

private Long id;


private String code;


private String name;


private Boolean isActive;


private String description;

private Long companyId;



private Long orderServicesTypeId;



private Set<QuotationDTO> quotations = new HashSet<>();

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

public String getDescription() {
    return description;
    }

public void setDescription(String description) {
    this.description = description;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getOrderServicesTypeId() {
    return orderServicesTypeId;
    }

public void setOrderServicesTypeId(Long orderServicesTypeId) {
    this.orderServicesTypeId = orderServicesTypeId;
    }


public Set<QuotationDTO> getQuotations() {
    return quotations;
    }

public void setQuotations(Set<QuotationDTO> quotations) {
    this.quotations = quotations;
    }

@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

OrderServicesSearchDTO orderServicesSearchDTO = (OrderServicesSearchDTO) o;
    if(orderServicesSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), orderServicesSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "OrderServicesSearchDTO{" +
    "id=" + getId() +
    ", code='" + getCode() + "'" +
    ", name='" + getName() + "'" +
    ", isActive='" + isIsActive() + "'" +
    ", description='" + getDescription() + "'" +
    "}";
    }
    }
