package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CustomerServices.
 */
@Entity
@Table(name = "customer_services")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customerservices")
public class CustomerServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "increase_percent")
    private Double increasePercent;

    @Column(name = "decrease_percent")
    private Double decreasePercent;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private OrderServices orderServices;

    @ManyToOne
    private Quotation quotation;

    @ManyToOne
    private Customer customerParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public CustomerServices discountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getIncreasePercent() {
        return increasePercent;
    }

    public CustomerServices increasePercent(Double increasePercent) {
        this.increasePercent = increasePercent;
        return this;
    }

    public void setIncreasePercent(Double increasePercent) {
        this.increasePercent = increasePercent;
    }

    public Double getDecreasePercent() {
        return decreasePercent;
    }

    public CustomerServices decreasePercent(Double decreasePercent) {
        this.decreasePercent = decreasePercent;
        return this;
    }

    public void setDecreasePercent(Double decreasePercent) {
        this.decreasePercent = decreasePercent;
    }

    public Company getCompany() {
        return company;
    }

    public CustomerServices company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OrderServices getOrderServices() {
        return orderServices;
    }

    public CustomerServices orderServices(OrderServices orderServices) {
        this.orderServices = orderServices;
        return this;
    }

    public void setOrderServices(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public CustomerServices quotation(Quotation quotation) {
        this.quotation = quotation;
        return this;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public Customer getCustomerParent() {
        return customerParent;
    }

    public CustomerServices customerParent(Customer customer) {
        this.customerParent = customer;
        return this;
    }

    public void setCustomerParent(Customer customer) {
        this.customerParent = customer;
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
        CustomerServices customerServices = (CustomerServices) o;
        if (customerServices.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerServices.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerServices{" +
            "id=" + getId() +
            ", discountPercent=" + getDiscountPercent() +
            ", increasePercent=" + getIncreasePercent() +
            ", decreasePercent=" + getDecreasePercent() +
            "}";
    }
}
