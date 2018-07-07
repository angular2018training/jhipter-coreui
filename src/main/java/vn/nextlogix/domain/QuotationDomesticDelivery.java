package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationDomesticDelivery.
 */
@Entity
@Table(name = "quotation_domestic_delivery")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotationdomesticdelivery")
public class QuotationDomesticDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private DistrictType districtType;

    @ManyToOne(optional = false)
    @NotNull
    private Region region;

    @ManyToOne(optional = false)
    @NotNull
    private Weight weight;

    @ManyToOne
    private Quotation quotationParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public QuotationDomesticDelivery amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Company getCompany() {
        return company;
    }

    public QuotationDomesticDelivery company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DistrictType getDistrictType() {
        return districtType;
    }

    public QuotationDomesticDelivery districtType(DistrictType districtType) {
        this.districtType = districtType;
        return this;
    }

    public void setDistrictType(DistrictType districtType) {
        this.districtType = districtType;
    }

    public Region getRegion() {
        return region;
    }

    public QuotationDomesticDelivery region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Weight getWeight() {
        return weight;
    }

    public QuotationDomesticDelivery weight(Weight weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Quotation getQuotationParent() {
        return quotationParent;
    }

    public QuotationDomesticDelivery quotationParent(Quotation quotation) {
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
        QuotationDomesticDelivery quotationDomesticDelivery = (QuotationDomesticDelivery) o;
        if (quotationDomesticDelivery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationDomesticDelivery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationDomesticDelivery{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            "}";
    }
}
