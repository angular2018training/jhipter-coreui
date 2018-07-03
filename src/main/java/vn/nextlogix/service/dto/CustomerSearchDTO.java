    package vn.nextlogix.service.dto;


    import java.time.Instant;
    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the Customer entity.
 */
public class CustomerSearchDTO implements Serializable {

private Long id;


private String code;


private String name;


private String address;


private String email;


private String phone;


private String password;


private Boolean isActive;


private Instant createDate;


private Instant lastLoginDate;


private String apiToken;

private Long legalId;



private Long paymentId;



private Long companyId;



private Long manageUserId;



private Long saleUserId;



private Long provinceId;



private Long districtId;



private Long customerTypeId;



private Long customerSourceId;



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

public Instant getCreateDate() {
    return createDate;
    }

public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
    }

public Instant getLastLoginDate() {
    return lastLoginDate;
    }

public void setLastLoginDate(Instant lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
    }

public String getApiToken() {
    return apiToken;
    }

public void setApiToken(String apiToken) {
    this.apiToken = apiToken;
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



public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
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



public Long getCustomerTypeId() {
    return customerTypeId;
    }

public void setCustomerTypeId(Long customerTypeId) {
    this.customerTypeId = customerTypeId;
    }



public Long getCustomerSourceId() {
    return customerSourceId;
    }

public void setCustomerSourceId(Long customerSourceId) {
    this.customerSourceId = customerSourceId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

CustomerSearchDTO customerSearchDTO = (CustomerSearchDTO) o;
    if(customerSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), customerSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "CustomerSearchDTO{" +
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
