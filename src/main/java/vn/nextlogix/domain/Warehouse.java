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
 * A Warehouse.
 */
@Entity
@Table(name = "warehouse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "warehouse")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @NotNull
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private Province province;

    @ManyToOne(optional = false)
    @NotNull
    private District district;

    @ManyToOne(optional = false)
    @NotNull
    private Ward ward;

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

    public Warehouse name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public Warehouse contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Warehouse contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public Warehouse address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Warehouse createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Company getCompany() {
        return company;
    }

    public Warehouse company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Province getProvince() {
        return province;
    }

    public Warehouse province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public Warehouse district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward getWard() {
        return ward;
    }

    public Warehouse ward(Ward ward) {
        this.ward = ward;
        return this;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
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
        Warehouse warehouse = (Warehouse) o;
        if (warehouse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), warehouse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Warehouse{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
