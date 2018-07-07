package vn.nextlogix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Quotation.
 */
@Entity
@Table(name = "quotation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotation")
public class Quotation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Column(name = "active_from", nullable = false)
    private LocalDate activeFrom;

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationPickup> quotationPickupDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationDomesticDelivery> quotationDomesticDeliveryDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationReturn> quotationReturnDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationGiveBack> quotationGiveBackDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationInsurance> quotationInsuranceDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationCod> quotationCodDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "quotationParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationSubServices> quotationSubServicesDetailLists = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Quotation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Quotation isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public Quotation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Quotation createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public LocalDate getActiveFrom() {
        return activeFrom;
    }

    public Quotation activeFrom(LocalDate activeFrom) {
        this.activeFrom = activeFrom;
        return this;
    }

    public void setActiveFrom(LocalDate activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Set<QuotationPickup> getQuotationPickupDetailLists() {
        return quotationPickupDetailLists;
    }

    public Quotation quotationPickupDetailLists(Set<QuotationPickup> quotationPickups) {
        this.quotationPickupDetailLists = quotationPickups;
        return this;
    }

    public Quotation addQuotationPickupDetailList(QuotationPickup quotationPickup) {
        this.quotationPickupDetailLists.add(quotationPickup);
        quotationPickup.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationPickupDetailList(QuotationPickup quotationPickup) {
        this.quotationPickupDetailLists.remove(quotationPickup);
        quotationPickup.setQuotationParent(null);
        return this;
    }

    public void setQuotationPickupDetailLists(Set<QuotationPickup> quotationPickups) {
        this.quotationPickupDetailLists = quotationPickups;
    }

    public Set<QuotationDomesticDelivery> getQuotationDomesticDeliveryDetailLists() {
        return quotationDomesticDeliveryDetailLists;
    }

    public Quotation quotationDomesticDeliveryDetailLists(Set<QuotationDomesticDelivery> quotationDomesticDeliveries) {
        this.quotationDomesticDeliveryDetailLists = quotationDomesticDeliveries;
        return this;
    }

    public Quotation addQuotationDomesticDeliveryDetailList(QuotationDomesticDelivery quotationDomesticDelivery) {
        this.quotationDomesticDeliveryDetailLists.add(quotationDomesticDelivery);
        quotationDomesticDelivery.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationDomesticDeliveryDetailList(QuotationDomesticDelivery quotationDomesticDelivery) {
        this.quotationDomesticDeliveryDetailLists.remove(quotationDomesticDelivery);
        quotationDomesticDelivery.setQuotationParent(null);
        return this;
    }

    public void setQuotationDomesticDeliveryDetailLists(Set<QuotationDomesticDelivery> quotationDomesticDeliveries) {
        this.quotationDomesticDeliveryDetailLists = quotationDomesticDeliveries;
    }

    public Set<QuotationReturn> getQuotationReturnDetailLists() {
        return quotationReturnDetailLists;
    }

    public Quotation quotationReturnDetailLists(Set<QuotationReturn> quotationReturns) {
        this.quotationReturnDetailLists = quotationReturns;
        return this;
    }

    public Quotation addQuotationReturnDetailList(QuotationReturn quotationReturn) {
        this.quotationReturnDetailLists.add(quotationReturn);
        quotationReturn.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationReturnDetailList(QuotationReturn quotationReturn) {
        this.quotationReturnDetailLists.remove(quotationReturn);
        quotationReturn.setQuotationParent(null);
        return this;
    }

    public void setQuotationReturnDetailLists(Set<QuotationReturn> quotationReturns) {
        this.quotationReturnDetailLists = quotationReturns;
    }

    public Set<QuotationGiveBack> getQuotationGiveBackDetailLists() {
        return quotationGiveBackDetailLists;
    }

    public Quotation quotationGiveBackDetailLists(Set<QuotationGiveBack> quotationGiveBacks) {
        this.quotationGiveBackDetailLists = quotationGiveBacks;
        return this;
    }

    public Quotation addQuotationGiveBackDetailList(QuotationGiveBack quotationGiveBack) {
        this.quotationGiveBackDetailLists.add(quotationGiveBack);
        quotationGiveBack.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationGiveBackDetailList(QuotationGiveBack quotationGiveBack) {
        this.quotationGiveBackDetailLists.remove(quotationGiveBack);
        quotationGiveBack.setQuotationParent(null);
        return this;
    }

    public void setQuotationGiveBackDetailLists(Set<QuotationGiveBack> quotationGiveBacks) {
        this.quotationGiveBackDetailLists = quotationGiveBacks;
    }

    public Set<QuotationInsurance> getQuotationInsuranceDetailLists() {
        return quotationInsuranceDetailLists;
    }

    public Quotation quotationInsuranceDetailLists(Set<QuotationInsurance> quotationInsurances) {
        this.quotationInsuranceDetailLists = quotationInsurances;
        return this;
    }

    public Quotation addQuotationInsuranceDetailList(QuotationInsurance quotationInsurance) {
        this.quotationInsuranceDetailLists.add(quotationInsurance);
        quotationInsurance.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationInsuranceDetailList(QuotationInsurance quotationInsurance) {
        this.quotationInsuranceDetailLists.remove(quotationInsurance);
        quotationInsurance.setQuotationParent(null);
        return this;
    }

    public void setQuotationInsuranceDetailLists(Set<QuotationInsurance> quotationInsurances) {
        this.quotationInsuranceDetailLists = quotationInsurances;
    }

    public Set<QuotationCod> getQuotationCodDetailLists() {
        return quotationCodDetailLists;
    }

    public Quotation quotationCodDetailLists(Set<QuotationCod> quotationCods) {
        this.quotationCodDetailLists = quotationCods;
        return this;
    }

    public Quotation addQuotationCodDetailList(QuotationCod quotationCod) {
        this.quotationCodDetailLists.add(quotationCod);
        quotationCod.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationCodDetailList(QuotationCod quotationCod) {
        this.quotationCodDetailLists.remove(quotationCod);
        quotationCod.setQuotationParent(null);
        return this;
    }

    public void setQuotationCodDetailLists(Set<QuotationCod> quotationCods) {
        this.quotationCodDetailLists = quotationCods;
    }

    public Set<QuotationSubServices> getQuotationSubServicesDetailLists() {
        return quotationSubServicesDetailLists;
    }

    public Quotation quotationSubServicesDetailLists(Set<QuotationSubServices> quotationSubServices) {
        this.quotationSubServicesDetailLists = quotationSubServices;
        return this;
    }

    public Quotation addQuotationSubServicesDetailList(QuotationSubServices quotationSubServices) {
        this.quotationSubServicesDetailLists.add(quotationSubServices);
        quotationSubServices.setQuotationParent(this);
        return this;
    }

    public Quotation removeQuotationSubServicesDetailList(QuotationSubServices quotationSubServices) {
        this.quotationSubServicesDetailLists.remove(quotationSubServices);
        quotationSubServices.setQuotationParent(null);
        return this;
    }

    public void setQuotationSubServicesDetailLists(Set<QuotationSubServices> quotationSubServices) {
        this.quotationSubServicesDetailLists = quotationSubServices;
    }

    public Company getCompany() {
        return company;
    }

    public Quotation company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        Quotation quotation = (Quotation) o;
        if (quotation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quotation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", description='" + getDescription() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", activeFrom='" + getActiveFrom() + "'" +
            "}";
    }
}
