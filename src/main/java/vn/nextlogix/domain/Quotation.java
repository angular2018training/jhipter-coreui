package vn.nextlogix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
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

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "create_date")
    private Instant createDate;

    @OneToMany(mappedBy = "quotation")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuotationItem> quotationItems = new HashSet<>();

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

    public Set<QuotationItem> getQuotationItems() {
        return quotationItems;
    }

    public Quotation quotationItems(Set<QuotationItem> quotationItems) {
        this.quotationItems = quotationItems;
        return this;
    }

    public Quotation addQuotationItem(QuotationItem quotationItem) {
        this.quotationItems.add(quotationItem);
        quotationItem.setQuotation(this);
        return this;
    }

    public Quotation removeQuotationItem(QuotationItem quotationItem) {
        this.quotationItems.remove(quotationItem);
        quotationItem.setQuotation(null);
        return this;
    }

    public void setQuotationItems(Set<QuotationItem> quotationItems) {
        this.quotationItems = quotationItems;
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
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
