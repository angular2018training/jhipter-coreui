package vn.nextlogix.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the OrderConsignee entity. This class is used in OrderConsigneeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-consignees?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderConsigneeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter address;

    private StringFilter consigneePhone;

    private StringFilter consigneeName;

    private LongFilter companyId;

    private LongFilter districtId;

    private LongFilter provinceId;

    public OrderConsigneeCriteria() {
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

    public StringFilter getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(StringFilter consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public StringFilter getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(StringFilter consigneeName) {
        this.consigneeName = consigneeName;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
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

    @Override
    public String toString() {
        return "OrderConsigneeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (consigneePhone != null ? "consigneePhone=" + consigneePhone + ", " : "") +
                (consigneeName != null ? "consigneeName=" + consigneeName + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
            "}";
    }

}
