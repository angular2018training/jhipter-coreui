package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationCod.
 */
@Entity
@Table(name = "quotation_cod")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotationcod")
public class QuotationCod implements Serializable {

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
    @Column(name = "fee_amount", nullable = false)
    private Double feeAmount;

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

    public QuotationCod amountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
        return this;
    }

    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Double getAmountTo() {
        return amountTo;
    }

    public QuotationCod amountTo(Double amountTo) {
        this.amountTo = amountTo;
        return this;
    }

    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }

    public Double getFeeAmountMin() {
        return feeAmountMin;
    }

    public QuotationCod feeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
        return this;
    }

    public void setFeeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public QuotationCod feeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
        return this;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Company getCompany() {
        return company;
    }

    public QuotationCod company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DistrictType getDistrictType() {
        return districtType;
    }

    public QuotationCod districtType(DistrictType districtType) {
        this.districtType = districtType;
        return this;
    }

    public void setDistrictType(DistrictType districtType) {
        this.districtType = districtType;
    }

    public Quotation getQuotationParent() {
        return quotationParent;
    }

    public QuotationCod quotationParent(Quotation quotation) {
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
        QuotationCod quotationCod = (QuotationCod) o;
        if (quotationCod.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationCod.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationCod{" +
            "id=" + getId() +
            ", amountFrom=" + getAmountFrom() +
            ", amountTo=" + getAmountTo() +
            ", feeAmountMin=" + getFeeAmountMin() +
            ", feeAmount=" + getFeeAmount() +
            "}";
    }
}
