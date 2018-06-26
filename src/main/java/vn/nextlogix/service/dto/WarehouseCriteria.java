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
 * Criteria class for the Warehouse entity. This class is used in WarehouseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /warehouses?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class WarehouseCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private StringFilter contactName;

    private StringFilter contactPhone;

    private StringFilter address;

    private InstantFilter createDate;

    private LongFilter companyId;

    private LongFilter provinceId;

    private LongFilter districtId;

    private LongFilter wardId;

    public WarehouseCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getContactName() {
        return contactName;
    }

    public void setContactName(StringFilter contactName) {
        this.contactName = contactName;
    }

    public StringFilter getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(StringFilter contactPhone) {
        this.contactPhone = contactPhone;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(LongFilter provinceId) {
        this.provinceId = provinceId;
    }

    public LongFilter getDistrictId() {
        return districtId;
    }

    public void setDistrictId(LongFilter districtId) {
        this.districtId = districtId;
    }

    public LongFilter getWardId() {
        return wardId;
    }

    public void setWardId(LongFilter wardId) {
        this.wardId = wardId;
    }

    @Override
    public String toString() {
        return "WarehouseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (contactName != null ? "contactName=" + contactName + ", " : "") +
                (contactPhone != null ? "contactPhone=" + contactPhone + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (wardId != null ? "wardId=" + wardId + ", " : "") +
            "}";
    }

}
