    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the QuotationSubServices entity.
 */
public class QuotationSubServicesSearchDTO implements Serializable {

private Long id;


private Double amountFrom;


private Double amountTo;


private Double feeAmountMin;


private Double feeAmountMax;


private Double feeAmount;


private Boolean autoSelect;

private Long companyId;



private Long orderSubServicesTypeId;



private Long orderSubServicesId;



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

public Double getFeeAmount() {
    return feeAmount;
    }

public void setFeeAmount(Double feeAmount) {
    this.feeAmount = feeAmount;
    }

public Boolean isAutoSelect() {
    return autoSelect;
    }

public void setAutoSelect(Boolean autoSelect) {
    this.autoSelect = autoSelect;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getOrderSubServicesTypeId() {
    return orderSubServicesTypeId;
    }

public void setOrderSubServicesTypeId(Long orderSubServicesTypeId) {
    this.orderSubServicesTypeId = orderSubServicesTypeId;
    }



public Long getOrderSubServicesId() {
    return orderSubServicesId;
    }

public void setOrderSubServicesId(Long orderSubServicesId) {
    this.orderSubServicesId = orderSubServicesId;
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

QuotationSubServicesSearchDTO quotationSubServicesSearchDTO = (QuotationSubServicesSearchDTO) o;
    if(quotationSubServicesSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), quotationSubServicesSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "QuotationSubServicesSearchDTO{" +
    "id=" + getId() +
    ", amountFrom=" + getAmountFrom() +
    ", amountTo=" + getAmountTo() +
    ", feeAmountMin=" + getFeeAmountMin() +
    ", feeAmountMax=" + getFeeAmountMax() +
    ", feeAmount=" + getFeeAmount() +
    ", autoSelect='" + isAutoSelect() + "'" +
    "}";
    }
    }
