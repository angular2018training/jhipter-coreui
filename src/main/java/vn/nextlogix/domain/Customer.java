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
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customer")
public class Customer implements Serializable {

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

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "jhi_password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "last_login_date")
    private Instant lastLoginDate;

    @Column(name = "api_token")
    private String apiToken;

    @OneToOne
    @JoinColumn(unique = true)
    private CustomerLegal legal;

    @OneToOne
    @JoinColumn(unique = true)
    private CustomerPayment payment;

    @OneToMany(mappedBy = "customerParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerPostOffice> customerPostOfficeDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "customerParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerWarehouse> customerWarehouseDetailLists = new HashSet<>();

    @OneToMany(mappedBy = "customerParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerServices> customerServicesDetailLists = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne
    private UserExtraInfo manageUser;

    @ManyToOne
    private UserExtraInfo saleUser;

    @ManyToOne(optional = false)
    @NotNull
    private Province province;

    @ManyToOne(optional = false)
    @NotNull
    private District district;

    @ManyToOne(optional = false)
    @NotNull
    private CustomerType customerType;

    @ManyToOne(optional = false)
    @NotNull
    private CustomerSource customerSource;

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

    public Customer code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Customer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public Customer password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Customer isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Customer createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public Customer lastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getApiToken() {
        return apiToken;
    }

    public Customer apiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public CustomerLegal getLegal() {
        return legal;
    }

    public Customer legal(CustomerLegal customerLegal) {
        this.legal = customerLegal;
        return this;
    }

    public void setLegal(CustomerLegal customerLegal) {
        this.legal = customerLegal;
    }

    public CustomerPayment getPayment() {
        return payment;
    }

    public Customer payment(CustomerPayment customerPayment) {
        this.payment = customerPayment;
        return this;
    }

    public void setPayment(CustomerPayment customerPayment) {
        this.payment = customerPayment;
    }

    public Set<CustomerPostOffice> getCustomerPostOfficeDetailLists() {
        return customerPostOfficeDetailLists;
    }

    public Customer customerPostOfficeDetailLists(Set<CustomerPostOffice> customerPostOffices) {
        this.customerPostOfficeDetailLists = customerPostOffices;
        return this;
    }

    public Customer addCustomerPostOfficeDetailList(CustomerPostOffice customerPostOffice) {
        this.customerPostOfficeDetailLists.add(customerPostOffice);
        customerPostOffice.setCustomerParent(this);
        return this;
    }

    public Customer removeCustomerPostOfficeDetailList(CustomerPostOffice customerPostOffice) {
        this.customerPostOfficeDetailLists.remove(customerPostOffice);
        customerPostOffice.setCustomerParent(null);
        return this;
    }

    public void setCustomerPostOfficeDetailLists(Set<CustomerPostOffice> customerPostOffices) {
        this.customerPostOfficeDetailLists = customerPostOffices;
    }

    public Set<CustomerWarehouse> getCustomerWarehouseDetailLists() {
        return customerWarehouseDetailLists;
    }

    public Customer customerWarehouseDetailLists(Set<CustomerWarehouse> customerWarehouses) {
        this.customerWarehouseDetailLists = customerWarehouses;
        return this;
    }

    public Customer addCustomerWarehouseDetailList(CustomerWarehouse customerWarehouse) {
        this.customerWarehouseDetailLists.add(customerWarehouse);
        customerWarehouse.setCustomerParent(this);
        return this;
    }

    public Customer removeCustomerWarehouseDetailList(CustomerWarehouse customerWarehouse) {
        this.customerWarehouseDetailLists.remove(customerWarehouse);
        customerWarehouse.setCustomerParent(null);
        return this;
    }

    public void setCustomerWarehouseDetailLists(Set<CustomerWarehouse> customerWarehouses) {
        this.customerWarehouseDetailLists = customerWarehouses;
    }

    public Set<CustomerServices> getCustomerServicesDetailLists() {
        return customerServicesDetailLists;
    }

    public Customer customerServicesDetailLists(Set<CustomerServices> customerServices) {
        this.customerServicesDetailLists = customerServices;
        return this;
    }

    public Customer addCustomerServicesDetailList(CustomerServices customerServices) {
        this.customerServicesDetailLists.add(customerServices);
        customerServices.setCustomerParent(this);
        return this;
    }

    public Customer removeCustomerServicesDetailList(CustomerServices customerServices) {
        this.customerServicesDetailLists.remove(customerServices);
        customerServices.setCustomerParent(null);
        return this;
    }

    public void setCustomerServicesDetailLists(Set<CustomerServices> customerServices) {
        this.customerServicesDetailLists = customerServices;
    }

    public Company getCompany() {
        return company;
    }

    public Customer company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserExtraInfo getManageUser() {
        return manageUser;
    }

    public Customer manageUser(UserExtraInfo userExtraInfo) {
        this.manageUser = userExtraInfo;
        return this;
    }

    public void setManageUser(UserExtraInfo userExtraInfo) {
        this.manageUser = userExtraInfo;
    }

    public UserExtraInfo getSaleUser() {
        return saleUser;
    }

    public Customer saleUser(UserExtraInfo userExtraInfo) {
        this.saleUser = userExtraInfo;
        return this;
    }

    public void setSaleUser(UserExtraInfo userExtraInfo) {
        this.saleUser = userExtraInfo;
    }

    public Province getProvince() {
        return province;
    }

    public Customer province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public Customer district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Customer customerType(CustomerType customerType) {
        this.customerType = customerType;
        return this;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerSource getCustomerSource() {
        return customerSource;
    }

    public Customer customerSource(CustomerSource customerSource) {
        this.customerSource = customerSource;
        return this;
    }

    public void setCustomerSource(CustomerSource customerSource) {
        this.customerSource = customerSource;
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
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", password='" + getPassword() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", apiToken='" + getApiToken() + "'" +
            "}";
    }
}
