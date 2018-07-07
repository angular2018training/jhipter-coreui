    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the QuotationPickup entity.
 */
public class QuotationPickupSearchDTO implements Serializable {

private Long id;


private Double amount;

private Long companyId;



private Long provinceId;



private Long districtTypeId;



private Long quotationParentId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public Double getAmount() {
    return amount;
    }

public void setAmount(Double amount) {
    this.amount = amount;
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



public Long getDistrictTypeId() {
    return districtTypeId;
    }

public void setDistrictTypeId(Long districtTypeId) {
    this.districtTypeId = districtTypeId;
    }



public Long getQuotationParentId() {
    return quotationParentId;
    }

public void setQuotationParentId(Long quotationId) {
    this.quotationParentId = quotationId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

QuotationPickupSearchDTO quotationPickupSearchDTO = (QuotationPickupSearchDTO) o;
    if(quotationPickupSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), quotationPickupSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "QuotationPickupSearchDTO{" +
    "id=" + getId() +
    ", amount=" + getAmount() +
    "}";
    }
    }
