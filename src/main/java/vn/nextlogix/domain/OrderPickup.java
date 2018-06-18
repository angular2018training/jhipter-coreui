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
 * A OrderPickup.
 */
@Entity
@Table(name = "order_pickup")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderpickup")
public class OrderPickup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "assign_date")
    private Instant assignDate;

    @Column(name = "pickup_date")
    private Instant pickupDate;

    @ManyToOne(optional = false)
    @NotNull
    private Ward ward;

    @ManyToOne(optional = false)
    @NotNull
    private District district;

    @ManyToOne(optional = false)
    @NotNull
    private Province province;

    @ManyToOne
    private UserExtraInfo pickupUser;

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

    public OrderPickup address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public OrderPickup contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public OrderPickup contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Instant getAssignDate() {
        return assignDate;
    }

    public OrderPickup assignDate(Instant assignDate) {
        this.assignDate = assignDate;
        return this;
    }

    public void setAssignDate(Instant assignDate) {
        this.assignDate = assignDate;
    }

    public Instant getPickupDate() {
        return pickupDate;
    }

    public OrderPickup pickupDate(Instant pickupDate) {
        this.pickupDate = pickupDate;
        return this;
    }

    public void setPickupDate(Instant pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Ward getWard() {
        return ward;
    }

    public OrderPickup ward(Ward ward) {
        this.ward = ward;
        return this;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public District getDistrict() {
        return district;
    }

    public OrderPickup district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public OrderPickup province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public UserExtraInfo getPickupUser() {
        return pickupUser;
    }

    public OrderPickup pickupUser(UserExtraInfo userExtraInfo) {
        this.pickupUser = userExtraInfo;
        return this;
    }

    public void setPickupUser(UserExtraInfo userExtraInfo) {
        this.pickupUser = userExtraInfo;
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
        OrderPickup orderPickup = (OrderPickup) o;
        if (orderPickup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderPickup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderPickup{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", assignDate='" + getAssignDate() + "'" +
            ", pickupDate='" + getPickupDate() + "'" +
            "}";
    }
}
