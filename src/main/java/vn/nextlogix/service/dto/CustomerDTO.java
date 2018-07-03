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

    @NotNull
    private Instant createDate;

    private Instant lastLoginDate;

    private String apiToken;

    private Long legalId;

    private CustomerLegalDTO  legalDTO;


    private Long paymentId;

    private CustomerPaymentDTO  paymentDTO;


    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long manageUserId;

    private UserExtraInfoDTO  manageUserDTO;


    private Long saleUserId;

    private UserExtraInfoDTO  saleUserDTO;


    private Long provinceId;

    private ProvinceDTO  provinceDTO;


    private Long districtId;

    private DistrictDTO  districtDTO;


    private Long customerTypeId;

    private CustomerTypeDTO  customerTypeDTO;


    private Long customerSourceId;

    private CustomerSourceDTO  customerSourceDTO;


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

    public CustomerLegalDTO getLegalDTO() {
        return this.legalDTO;
    }

    public void setLegalDTO(CustomerLegalDTO legalDTO ) {
        this.legalDTO = legalDTO;
    }
    public Long getLegalId() {
        return legalId;
        }

    public void setLegalId(Long customerLegalId) {
        this.legalId = customerLegalId;
        }


    public CustomerPaymentDTO getPaymentDTO() {
        return this.paymentDTO;
    }

    public void setPaymentDTO(CustomerPaymentDTO paymentDTO ) {
        this.paymentDTO = paymentDTO;
    }
    public Long getPaymentId() {
        return paymentId;
        }

    public void setPaymentId(Long customerPaymentId) {
        this.paymentId = customerPaymentId;
        }


    public CompanyDTO getCompanyDTO() {
        return this.companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO ) {
        this.companyDTO = companyDTO;
    }
    public Long getCompanyId() {
        return companyId;
        }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
        }


    public UserExtraInfoDTO getManageUserDTO() {
        return this.manageUserDTO;
    }

    public void setManageUserDTO(UserExtraInfoDTO manageUserDTO ) {
        this.manageUserDTO = manageUserDTO;
    }
    public Long getManageUserId() {
        return manageUserId;
        }

    public void setManageUserId(Long userExtraInfoId) {
        this.manageUserId = userExtraInfoId;
        }


    public UserExtraInfoDTO getSaleUserDTO() {
        return this.saleUserDTO;
    }

    public void setSaleUserDTO(UserExtraInfoDTO saleUserDTO ) {
        this.saleUserDTO = saleUserDTO;
    }
    public Long getSaleUserId() {
        return saleUserId;
        }

    public void setSaleUserId(Long userExtraInfoId) {
        this.saleUserId = userExtraInfoId;
        }


    public ProvinceDTO getProvinceDTO() {
        return this.provinceDTO;
    }

    public void setProvinceDTO(ProvinceDTO provinceDTO ) {
        this.provinceDTO = provinceDTO;
    }
    public Long getProvinceId() {
        return provinceId;
        }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
        }


    public DistrictDTO getDistrictDTO() {
        return this.districtDTO;
    }

    public void setDistrictDTO(DistrictDTO districtDTO ) {
        this.districtDTO = districtDTO;
    }
    public Long getDistrictId() {
        return districtId;
        }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
        }


    public CustomerTypeDTO getCustomerTypeDTO() {
        return this.customerTypeDTO;
    }

    public void setCustomerTypeDTO(CustomerTypeDTO customerTypeDTO ) {
        this.customerTypeDTO = customerTypeDTO;
    }
    public Long getCustomerTypeId() {
        return customerTypeId;
        }

    public void setCustomerTypeId(Long customerTypeId) {
        this.customerTypeId = customerTypeId;
        }


    public CustomerSourceDTO getCustomerSourceDTO() {
        return this.customerSourceDTO;
    }

    public void setCustomerSourceDTO(CustomerSourceDTO customerSourceDTO ) {
        this.customerSourceDTO = customerSourceDTO;
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
            ", createDate='" + getCreateDate() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", apiToken='" + getApiToken() + "'" +
            "}";
    }
}
