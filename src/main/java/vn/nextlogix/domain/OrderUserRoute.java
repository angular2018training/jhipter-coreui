package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderUserRoute.
 */
@Entity
@Table(name = "order_user_route")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderuserroute")
public class OrderUserRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private UserExtraInfo user;

    @ManyToOne(optional = false)
    @NotNull
    private Province province;

    @ManyToOne(optional = false)
    @NotNull
    private District district;

    @ManyToOne(optional = false)
    @NotNull
    private OrderUserRouteType orderUserRouteType;

    @ManyToOne
    private District ward;

    @ManyToOne
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public OrderUserRoute company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserExtraInfo getUser() {
        return user;
    }

    public OrderUserRoute user(UserExtraInfo userExtraInfo) {
        this.user = userExtraInfo;
        return this;
    }

    public void setUser(UserExtraInfo userExtraInfo) {
        this.user = userExtraInfo;
    }

    public Province getProvince() {
        return province;
    }

    public OrderUserRoute province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public OrderUserRoute district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public OrderUserRouteType getOrderUserRouteType() {
        return orderUserRouteType;
    }

    public OrderUserRoute orderUserRouteType(OrderUserRouteType orderUserRouteType) {
        this.orderUserRouteType = orderUserRouteType;
        return this;
    }

    public void setOrderUserRouteType(OrderUserRouteType orderUserRouteType) {
        this.orderUserRouteType = orderUserRouteType;
    }

    public District getWard() {
        return ward;
    }

    public OrderUserRoute ward(District district) {
        this.ward = district;
        return this;
    }

    public void setWard(District district) {
        this.ward = district;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderUserRoute customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        OrderUserRoute orderUserRoute = (OrderUserRoute) o;
        if (orderUserRoute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderUserRoute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderUserRoute{" +
            "id=" + getId() +
            "}";
    }
}
