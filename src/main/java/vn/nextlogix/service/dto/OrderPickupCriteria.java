package vn.nextlogix.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the OrderPickup entity. This class is used in OrderPickupResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-pickups?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderPickupCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter address;

    private StringFilter contactPhone;

    private StringFilter contactName;

    private InstantFilter assignDate;

    private InstantFilter pickupDate;

    private LongFilter companyId;

    private LongFilter wardId;

    private LongFilter districtId;

    private LongFilter provinceId;

    private LongFilter pickupUserId;

    public OrderPickupCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(StringFilter contactPhone) {
        this.contactPhone = contactPhone;
    }

    public StringFilter getContactName() {
        return contactName;
    }

    public void setContactName(StringFilter contactName) {
        this.contactName = contactName;
    }

    public InstantFilter getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(InstantFilter assignDate) {
        this.assignDate = assignDate;
    }

    public InstantFilter getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(InstantFilter pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getWardId() {
        return wardId;
    }

    public void setWardId(LongFilter wardId) {
        this.wardId = wardId;
    }

    public LongFilter getDistrictId() {
        return districtId;
    }

    public void setDistrictId(LongFilter districtId) {
        this.districtId = districtId;
    }

    public LongFilter getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(LongFilter provinceId) {
        this.provinceId = provinceId;
    }

    public LongFilter getPickupUserId() {
        return pickupUserId;
    }

    public void setPickupUserId(LongFilter pickupUserId) {
        this.pickupUserId = pickupUserId;
    }

    @Override
    public String toString() {
        return "OrderPickupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (contactPhone != null ? "contactPhone=" + contactPhone + ", " : "") +
                (contactName != null ? "contactName=" + contactName + ", " : "") +
                (assignDate != null ? "assignDate=" + assignDate + ", " : "") +
                (pickupDate != null ? "pickupDate=" + pickupDate + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (wardId != null ? "wardId=" + wardId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (pickupUserId != null ? "pickupUserId=" + pickupUserId + ", " : "") +
            "}";
    }

}
