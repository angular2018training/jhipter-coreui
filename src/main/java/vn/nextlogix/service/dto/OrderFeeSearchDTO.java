    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the OrderFee entity.
 */
public class OrderFeeSearchDTO implements Serializable {

private Long id;


private Double deliveryFee;


private Double pickupFee;


private Double codFee;


private Double insuranceFee;


private Double otherFee;


private Double discount;


private Double totalFee;

private Long companyId;



private Long quotationId;



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


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
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

OrderFeeSearchDTO orderFeeSearchDTO = (OrderFeeSearchDTO) o;
    if(orderFeeSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), orderFeeSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "OrderFeeSearchDTO{" +
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
