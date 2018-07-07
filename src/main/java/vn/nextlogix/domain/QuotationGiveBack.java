package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationGiveBack.
 */
@Entity
@Table(name = "quotation_give_back")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotationgiveback")
public class QuotationGiveBack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fee_percent", nullable = false)
    private Double feePercent;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private DistrictType districtType;

    @ManyToOne(optional = false)
    @NotNull
    private Region region;

    @ManyToOne
    private Quotation quotationParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFeePercent() {
        return feePercent;
    }

    public QuotationGiveBack feePercent(Double feePercent) {
        this.feePercent = feePercent;
        return this;
    }

    public void setFeePercent(Double feePercent) {
        this.feePercent = feePercent;
    }

    public Company getCompany() {
        return company;
    }

    public QuotationGiveBack company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DistrictType getDistrictType() {
        return districtType;
    }

    public QuotationGiveBack districtType(DistrictType districtType) {
        this.districtType = districtType;
        return this;
    }

    public void setDistrictType(DistrictType districtType) {
        this.districtType = districtType;
    }

    public Region getRegion() {
        return region;
    }

    public QuotationGiveBack region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Quotation getQuotationParent() {
        return quotationParent;
    }

    public QuotationGiveBack quotationParent(Quotation quotation) {
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
        QuotationGiveBack quotationGiveBack = (QuotationGiveBack) o;
        if (quotationGiveBack.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationGiveBack.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationGiveBack{" +
            "id=" + getId() +
            ", feePercent=" + getFeePercent() +
            "}";
    }
}
