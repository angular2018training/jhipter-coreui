    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the QuotationReturn entity.
 */
public class QuotationReturnSearchDTO implements Serializable {

private Long id;


private Double feePercent;

private Long companyId;



private Long districtTypeId;



private Long regionId;



private Long quotationParentId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public Double getFeePercent() {
    return feePercent;
    }

public void setFeePercent(Double feePercent) {
    this.feePercent = feePercent;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getDistrictTypeId() {
    return districtTypeId;
    }

public void setDistrictTypeId(Long districtTypeId) {
    this.districtTypeId = districtTypeId;
    }



public Long getRegionId() {
    return regionId;
    }

public void setRegionId(Long regionId) {
    this.regionId = regionId;
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

QuotationReturnSearchDTO quotationReturnSearchDTO = (QuotationReturnSearchDTO) o;
    if(quotationReturnSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), quotationReturnSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "QuotationReturnSearchDTO{" +
    "id=" + getId() +
    ", feePercent=" + getFeePercent() +
    "}";
    }
    }
