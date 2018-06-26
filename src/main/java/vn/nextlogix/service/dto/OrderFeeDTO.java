package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderFee entity.
 */
public class OrderFeeDTO implements Serializable {

    private Long id;

    @NotNull
    private Double deliveryFee;

    private Double pickupFee;

    private Double codFee;

    private Double insuranceFee;

    private Double otherFee;

    private Double discount;

    @NotNull
    private Double totalFee;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long quotationId;

    private QuotationDTO  quotationDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getPickupFee() {
        return pickupFee;
    }

    public void setPickupFee(Double pickupFee) {
        this.pickupFee = pickupFee;
    }

    public Double getCodFee() {
        return codFee;
    }

    public void setCodFee(Double codFee) {
        this.codFee = codFee;
    }

    public Double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(Double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
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


    public QuotationDTO getQuotationDTO() {
        return this.quotationDTO;
    }

    public void setQuotationDTO(QuotationDTO quotationDTO ) {
        this.quotationDTO = quotationDTO;
    }
    public Long getQuotationId() {
        return quotationId;
        }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderFeeDTO orderFeeDTO = (OrderFeeDTO) o;
        if(orderFeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderFeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderFeeDTO{" +
            "id=" + getId() +
            ", deliveryFee=" + getDeliveryFee() +
            ", pickupFee=" + getPickupFee() +
            ", codFee=" + getCodFee() +
            ", insuranceFee=" + getInsuranceFee() +
            ", otherFee=" + getOtherFee() +
            ", discount=" + getDiscount() +
            ", totalFee=" + getTotalFee() +
            "}";
    }
}
