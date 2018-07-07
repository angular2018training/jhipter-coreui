package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the QuotationSubServices entity.
 */
public class QuotationSubServicesDTO implements Serializable {

    private Long id;

    @NotNull
    private Double amountFrom;

    @NotNull
    private Double amountTo;

    @NotNull
    private Double feeAmountMin;

    @NotNull
    private Double feeAmountMax;

    @NotNull
    private Double feeAmount;

    @NotNull
    private Boolean autoSelect;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long orderSubServicesTypeId;

    private OrderSubServicesTypeDTO  orderSubServicesTypeDTO;


    private Long orderSubServicesId;

    private OrderSubServicesDTO  orderSubServicesDTO;


    private Long quotationParentId;

    private QuotationDTO  quotationParentDTO;


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

    public CompanyDTO getCompanyDTO() {
        return this.companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO ) {
        this.companyDTO = companyDTO;
    }
    public Long getCompanyId() {
        return companyId;
        }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
        }


    public OrderSubServicesTypeDTO getOrderSubServicesTypeDTO() {
        return this.orderSubServicesTypeDTO;
    }

    public void setOrderSubServicesTypeDTO(OrderSubServicesTypeDTO orderSubServicesTypeDTO ) {
        this.orderSubServicesTypeDTO = orderSubServicesTypeDTO;
    }
    public Long getOrderSubServicesTypeId() {
        return orderSubServicesTypeId;
        }

    public void setOrderSubServicesTypeId(Long orderSubServicesTypeId) {
        this.orderSubServicesTypeId = orderSubServicesTypeId;
        }


    public OrderSubServicesDTO getOrderSubServicesDTO() {
        return this.orderSubServicesDTO;
    }

    public void setOrderSubServicesDTO(OrderSubServicesDTO orderSubServicesDTO ) {
        this.orderSubServicesDTO = orderSubServicesDTO;
    }
    public Long getOrderSubServicesId() {
        return orderSubServicesId;
        }

    public void setOrderSubServicesId(Long orderSubServicesId) {
        this.orderSubServicesId = orderSubServicesId;
        }


    public QuotationDTO getQuotationParentDTO() {
        return this.quotationParentDTO;
    }

    public void setQuotationParentDTO(QuotationDTO quotationParentDTO ) {
        this.quotationParentDTO = quotationParentDTO;
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

        QuotationSubServicesDTO quotationSubServicesDTO = (QuotationSubServicesDTO) o;
        if(quotationSubServicesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationSubServicesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationSubServicesDTO{" +
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
