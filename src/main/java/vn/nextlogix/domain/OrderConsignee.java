package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderConsignee.
 */
@Entity
@Table(name = "order_consignee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderconsignee")
public class OrderConsignee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "consignee_phone", nullable = false)
    private String consigneePhone;

    @Column(name = "consignee_name")
    private String consigneeName;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private District district;

    @ManyToOne(optional = false)
    @NotNull
    private Province province;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public OrderConsignee address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public OrderConsignee consigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
        return this;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public OrderConsignee consigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
        return this;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public Company getCompany() {
        return company;
    }

    public OrderConsignee company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public District getDistrict() {
        return district;
    }

    public OrderConsignee district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public OrderConsignee province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
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
        OrderConsignee orderConsignee = (OrderConsignee) o;
        if (orderConsignee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderConsignee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderConsignee{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", consigneePhone='" + getConsigneePhone() + "'" +
            ", consigneeName='" + getConsigneeName() + "'" +
            "}";
    }
}
