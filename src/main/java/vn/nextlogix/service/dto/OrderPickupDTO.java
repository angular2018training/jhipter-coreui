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

    private Long wardId;

    private Long districtId;

    private Long provinceId;

    private Long pickupUserId;

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

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
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
