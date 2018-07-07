package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationSubServices.
 */
@Entity
@Table(name = "quotation_sub_services")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotationsubservices")
public class QuotationSubServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "amount_from", nullable = false)
    private Double amountFrom;

    @NotNull
    @Column(name = "amount_to", nullable = false)
    private Double amountTo;

    @NotNull
    @Column(name = "fee_amount_min", nullable = false)
    private Double feeAmountMin;

    @NotNull
    @Column(name = "fee_amount_max", nullable = false)
    private Double feeAmountMax;

    @NotNull
    @Column(name = "fee_amount", nullable = false)
    private Double feeAmount;

    @NotNull
    @Column(name = "auto_select", nullable = false)
    private Boolean autoSelect;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne
    private OrderSubServicesType orderSubServicesType;

    @ManyToOne
    private OrderSubServices orderSubServices;

    @ManyToOne
    private Quotation quotationParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmountFrom() {
        return amountFrom;
    }

    public QuotationSubServices amountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
        return this;
    }

    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Double getAmountTo() {
        return amountTo;
    }

    public QuotationSubServices amountTo(Double amountTo) {
        this.amountTo = amountTo;
        return this;
    }

    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }

    public Double getFeeAmountMin() {
        return feeAmountMin;
    }

    public QuotationSubServices feeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
        return this;
    }

    public void setFeeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
    }

    public Double getFeeAmountMax() {
        return feeAmountMax;
    }

    public QuotationSubServices feeAmountMax(Double feeAmountMax) {
        this.feeAmountMax = feeAmountMax;
        return this;
    }

    public void setFeeAmountMax(Double feeAmountMax) {
        this.feeAmountMax = feeAmountMax;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public QuotationSubServices feeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
        return this;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Boolean isAutoSelect() {
        return autoSelect;
    }

    public QuotationSubServices autoSelect(Boolean autoSelect) {
        this.autoSelect = autoSelect;
        return this;
    }

    public void setAutoSelect(Boolean autoSelect) {
        this.autoSelect = autoSelect;
    }

    public Company getCompany() {
        return company;
    }

    public QuotationSubServices company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OrderSubServicesType getOrderSubServicesType() {
        return orderSubServicesType;
    }

    public QuotationSubServices orderSubServicesType(OrderSubServicesType orderSubServicesType) {
        this.orderSubServicesType = orderSubServicesType;
        return this;
    }

    public void setOrderSubServicesType(OrderSubServicesType orderSubServicesType) {
        this.orderSubServicesType = orderSubServicesType;
    }

    public OrderSubServices getOrderSubServices() {
        return orderSubServices;
    }

    public QuotationSubServices orderSubServices(OrderSubServices orderSubServices) {
        this.orderSubServices = orderSubServices;
        return this;
    }

    public void setOrderSubServices(OrderSubServices orderSubServices) {
        this.orderSubServices = orderSubServices;
    }

    public Quotation getQuotationParent() {
        return quotationParent;
    }

    public QuotationSubServices quotationParent(Quotation quotation) {
        this.quotationParent = quotation;
        return this;
    }

    public void setQuotationParent(Quotation quotation) {
        this.quotationParent = quotation;
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
        QuotationSubServices quotationSubServices = (QuotationSubServices) o;
        if (quotationSubServices.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationSubServices.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationSubServices{" +
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
