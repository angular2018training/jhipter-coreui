package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationInsurance.
 */
@Entity
@Table(name = "quotation_insurance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotationinsurance")
public class QuotationInsurance implements Serializable {

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
    @Column(name = "fee_percent", nullable = false)
    private Double feePercent;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private DistrictType districtType;

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

    public QuotationInsurance amountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
        return this;
    }

    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Double getAmountTo() {
        return amountTo;
    }

    public QuotationInsurance amountTo(Double amountTo) {
        this.amountTo = amountTo;
        return this;
    }

    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }

    public Double getFeeAmountMin() {
        return feeAmountMin;
    }

    public QuotationInsurance feeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
        return this;
    }

    public void setFeeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
    }

    public Double getFeeAmountMax() {
        return feeAmountMax;
    }

    public QuotationInsurance feeAmountMax(Double feeAmountMax) {
        this.feeAmountMax = feeAmountMax;
        return this;
    }

    public void setFeeAmountMax(Double feeAmountMax) {
        this.feeAmountMax = feeAmountMax;
    }

    public Double getFeePercent() {
        return feePercent;
    }

    public QuotationInsurance feePercent(Double feePercent) {
        this.feePercent = feePercent;
        return this;
    }

    public void setFeePercent(Double feePercent) {
        this.feePercent = feePercent;
    }

    public Company getCompany() {
        return company;
    }

    public QuotationInsurance company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DistrictType getDistrictType() {
        return districtType;
    }

    public QuotationInsurance districtType(DistrictType districtType) {
        this.districtType = districtType;
        return this;
    }

    public void setDistrictType(DistrictType districtType) {
        this.districtType = districtType;
    }

    public Quotation getQuotationParent() {
        return quotationParent;
    }

    public QuotationInsurance quotationParent(Quotation quotation) {
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
        QuotationInsurance quotationInsurance = (QuotationInsurance) o;
        if (quotationInsurance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationInsurance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationInsurance{" +
            "id=" + getId() +
            ", amountFrom=" + getAmountFrom() +
            ", amountTo=" + getAmountTo() +
            ", feeAmountMin=" + getFeeAmountMin() +
            ", feeAmountMax=" + getFeeAmountMax() +
            ", feePercent=" + getFeePercent() +
            "}";
    }
}
