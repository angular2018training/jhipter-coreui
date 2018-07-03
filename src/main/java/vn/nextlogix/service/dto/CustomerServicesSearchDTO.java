    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the CustomerServices entity.
 */
public class CustomerServicesSearchDTO implements Serializable {

private Long id;


private Double discountPercent;


private Double increasePercent;


private Double decreasePercent;

private Long companyId;



private Long orderServicesId;



private Long quotationId;



private Long customerParentId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public Double getDiscountPercent() {
    return discountPercent;
    }

public void setDiscountPercent(Double discountPercent) {
    this.discountPercent = discountPercent;
    }

public Double getIncreasePercent() {
    return increasePercent;
    }

public void setIncreasePercent(Double increasePercent) {
    this.increasePercent = increasePercent;
    }

public Double getDecreasePercent() {
    return decreasePercent;
    }

public void setDecreasePercent(Double decreasePercent) {
    this.decreasePercent = decreasePercent;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getOrderServicesId() {
    return orderServicesId;
    }

public void setOrderServicesId(Long orderServicesId) {
    this.orderServicesId = orderServicesId;
    }



public Long getQuotationId() {
    return quotationId;
    }

public void setQuotationId(Long quotationId) {
    this.quotationId = quotationId;
    }



public Long getCustomerParentId() {
    return customerParentId;
    }

public void setCustomerParentId(Long customerId) {
    this.customerParentId = customerId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

CustomerServicesSearchDTO customerServicesSearchDTO = (CustomerServicesSearchDTO) o;
    if(customerServicesSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), customerServicesSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "CustomerServicesSearchDTO{" +
    "id=" + getId() +
    ", discountPercent=" + getDiscountPercent() +
    ", increasePercent=" + getIncreasePercent() +
    ", decreasePercent=" + getDecreasePercent() +
    "}";
    }
    }
