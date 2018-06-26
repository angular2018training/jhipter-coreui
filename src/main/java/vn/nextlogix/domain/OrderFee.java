package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderFee.
 */
@Entity
@Table(name = "order_fee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderfee")
public class OrderFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "delivery_fee", nullable = false)
    private Double deliveryFee;

    @Column(name = "pickup_fee")
    private Double pickupFee;

    @Column(name = "cod_fee")
    private Double codFee;

    @Column(name = "insurance_fee")
    private Double insuranceFee;

    @Column(name = "other_fee")
    private Double otherFee;

    @Column(name = "discount")
    private Double discount;

    @NotNull
    @Column(name = "total_fee", nullable = false)
    private Double totalFee;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private Quotation quotation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public OrderFee deliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getPickupFee() {
        return pickupFee;
    }

    public OrderFee pickupFee(Double pickupFee) {
        this.pickupFee = pickupFee;
        return this;
    }

    public void setPickupFee(Double pickupFee) {
        this.pickupFee = pickupFee;
    }

    public Double getCodFee() {
        return codFee;
    }

    public OrderFee codFee(Double codFee) {
        this.codFee = codFee;
        return this;
    }

    public void setCodFee(Double codFee) {
        this.codFee = codFee;
    }

    public Double getInsuranceFee() {
        return insuranceFee;
    }

    public OrderFee insuranceFee(Double insuranceFee) {
        this.insuranceFee = insuranceFee;
        return this;
    }

    public void setInsuranceFee(Double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public OrderFee otherFee(Double otherFee) {
        this.otherFee = otherFee;
        return this;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getDiscount() {
        return discount;
    }

    public OrderFee discount(Double discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public OrderFee totalFee(Double totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Company getCompany() {
        return company;
    }

    public OrderFee company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public OrderFee quotation(Quotation quotation) {
        this.quotation = quotation;
        return this;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderFee orderFee = (OrderFee) o;
        if (orderFee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderFee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderFee{" +
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
