    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the QuotationInsurance entity.
 */
public class QuotationInsuranceSearchDTO implements Serializable {

private Long id;


private Double amountFrom;


private Double amountTo;


private Double feeAmountMin;


private Double feeAmountMax;


private Double feePercent;

private Long companyId;



private Long districtTypeId;



private Long quotationParentId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public Double getAmountFrom() {
    return amountFrom;
    }

public void setAmountFrom(Double amountFrom) {
    this.amountFrom = amountFrom;
    }

public Double getAmountTo() {
    return amountTo;
    }

public void setAmountTo(Double amountTo) {
    this.amountTo = amountTo;
    }

public Double getFeeAmountMin() {
    return feeAmountMin;
    }

public void setFeeAmountMin(Double feeAmountMin) {
    this.feeAmountMin = feeAmountMin;
    }

public Double getFeeAmountMax() {
    return feeAmountMax;
    }

public void setFeeAmountMax(Double feeAmountMax) {
    this.feeAmountMax = feeAmountMax;
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

QuotationInsuranceSearchDTO quotationInsuranceSearchDTO = (QuotationInsuranceSearchDTO) o;
    if(quotationInsuranceSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), quotationInsuranceSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "QuotationInsuranceSearchDTO{" +
    "id=" + getId() +
    ", amountFrom=" + getAmountFrom() +
    ", amountTo=" + getAmountTo() +
    ", feeAmountMin=" + getFeeAmountMin() +
    ", feeAmountMax=" + getFeeAmountMax() +
    ", feePercent=" + getFeePercent() +
    "}";
    }
    }
