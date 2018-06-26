package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CustomerPostOffice.
 */
@Entity
@Table(name = "customer_post_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customerpostoffice")
public class CustomerPostOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private PostOffice postOffice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CustomerPostOffice code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public CustomerPostOffice isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public CustomerPostOffice createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerPostOffice customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public CustomerPostOffice company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public CustomerPostOffice postOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
        return this;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
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
        CustomerPostOffice customerPostOffice = (CustomerPostOffice) o;
        if (customerPostOffice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerPostOffice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerPostOffice{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
