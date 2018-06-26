package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderPickup entity.
 */
public class OrderPickupDTO implements Serializable {

    private Long id;

    @NotNull
    private String address;

    @NotNull
    private String contactPhone;

    private String contactName;

    private Instant assignDate;

    private Instant pickupDate;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long wardId;

    private WardDTO  wardDTO;


    private Long districtId;

    private DistrictDTO  districtDTO;


    private Long provinceId;

    private ProvinceDTO  provinceDTO;


    private Long pickupUserId;

    private UserExtraInfoDTO  pickupUserDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Instant getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Instant assignDate) {
        this.assignDate = assignDate;
    }

    public Instant getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Instant pickupDate) {
        this.pickupDate = pickupDate;
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


    public WardDTO getWardDTO() {
        return this.wardDTO;
    }

    public void setWardDTO(WardDTO wardDTO ) {
        this.wardDTO = wardDTO;
    }
    public Long getWardId() {
        return wardId;
        }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
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


    public UserExtraInfoDTO getPickupUserDTO() {
        return this.pickupUserDTO;
    }

    public void setPickupUserDTO(UserExtraInfoDTO pickupUserDTO ) {
        this.pickupUserDTO = pickupUserDTO;
    }
    public Long getPickupUserId() {
        return pickupUserId;
        }

    public void setPickupUserId(Long userExtraInfoId) {
        this.pickupUserId = userExtraInfoId;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderPickupDTO orderPickupDTO = (OrderPickupDTO) o;
        if(orderPickupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderPickupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderPickupDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", assignDate='" + getAssignDate() + "'" +
            ", pickupDate='" + getPickupDate() + "'" +
            "}";
    }
}
