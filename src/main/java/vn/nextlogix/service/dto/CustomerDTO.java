package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import vn.nextlogix.domain.enumeration.CustomerType;

/**
 * A DTO for the Customer entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String address;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private Boolean isActive;

    private CustomerType customerType;

    @NotNull
    private Instant createDate;

    private Long legalId;

    private Long paymentId;

    private Long manageUserId;

    private Long saleUserId;

    private Long debtUserId;

    private Long provinceId;

    private Long districtId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Long getLegalId() {
        return legalId;
    }

    public void setLegalId(Long customerLegalId) {
        this.legalId = customerLegalId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long customerPaymentId) {
        this.paymentId = customerPaymentId;
    }

    public Long getManageUserId() {
        return manageUserId;
    }

    public void setManageUserId(Long userExtraInfoId) {
        this.manageUserId = userExtraInfoId;
    }

    public Long getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(Long userExtraInfoId) {
        this.saleUserId = userExtraInfoId;
    }

    public Long getDebtUserId() {
        return debtUserId;
    }

    public void setDebtUserId(Long userExtraInfoId) {
        this.debtUserId = userExtraInfoId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if(customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", password='" + getPassword() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
