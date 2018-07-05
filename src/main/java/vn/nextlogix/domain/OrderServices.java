package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OrderServices.
 */
@Entity
@Table(name = "order_services")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderservices")
public class OrderServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private OrderServicesType orderServicesType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "order_services_quotation",
               joinColumns = @JoinColumn(name="order_services_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="quotations_id", referencedColumnName="id"))
    private Set<Quotation> quotations = new HashSet<>();

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

    public OrderServices code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public OrderServices name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public OrderServices description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public OrderServices company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OrderServicesType getOrderServicesType() {
        return orderServicesType;
    }

    public OrderServices orderServicesType(OrderServicesType orderServicesType) {
        this.orderServicesType = orderServicesType;
        return this;
    }

    public void setOrderServicesType(OrderServicesType orderServicesType) {
        this.orderServicesType = orderServicesType;
    }

    public Set<Quotation> getQuotations() {
        return quotations;
    }

    public OrderServices quotations(Set<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }

    public OrderServices addQuotation(Quotation quotation) {
        this.quotations.add(quotation);
        return this;
    }

    public OrderServices removeQuotation(Quotation quotation) {
        this.quotations.remove(quotation);
        return this;
    }

    public void setQuotations(Set<Quotation> quotations) {
        this.quotations = quotations;
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
        OrderServices orderServices = (OrderServices) o;
        if (orderServices.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderServices.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderServices{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
