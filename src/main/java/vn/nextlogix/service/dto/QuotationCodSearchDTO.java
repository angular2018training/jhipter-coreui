    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the QuotationCod entity.
 */
public class QuotationCodSearchDTO implements Serializable {

private Long id;


private Double amountFrom;


private Double amountTo;


private Double feeAmountMin;


private Double feeAmount;

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

public Double getFeeAmount() {
    return feeAmount;
    }

public void setFeeAmount(Double feeAmount) {
    this.feeAmount = feeAmount;
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

QuotationCodSearchDTO quotationCodSearchDTO = (QuotationCodSearchDTO) o;
    if(quotationCodSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), quotationCodSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "QuotationCodSearchDTO{" +
    "id=" + getId() +
    ", amountFrom=" + getAmountFrom() +
    ", amountTo=" + getAmountTo() +
    ", feeAmountMin=" + getFeeAmountMin() +
    ", feeAmount=" + getFeeAmount() +
    "}";
    }
    }
