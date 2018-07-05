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
 * Criteria class for the District entity. This class is used in DistrictResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /districts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DistrictCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private BooleanFilter pickupActive;

    private BooleanFilter deliveryActive;

    private StringFilter description;

    private LongFilter companyId;

    private LongFilter provinceId;

    public DistrictCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BooleanFilter getPickupActive() {
        return pickupActive;
    }

    public void setPickupActive(BooleanFilter pickupActive) {
        this.pickupActive = pickupActive;
    }

    public BooleanFilter getDeliveryActive() {
        return deliveryActive;
    }

    public void setDeliveryActive(BooleanFilter deliveryActive) {
        this.deliveryActive = deliveryActive;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "DistrictCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (pickupActive != null ? "pickupActive=" + pickupActive + ", " : "") +
                (deliveryActive != null ? "deliveryActive=" + deliveryActive + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
            "}";
    }

}
