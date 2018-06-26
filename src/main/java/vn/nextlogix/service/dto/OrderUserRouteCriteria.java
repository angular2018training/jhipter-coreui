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
 * Criteria class for the OrderUserRoute entity. This class is used in OrderUserRouteResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-user-routes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderUserRouteCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter companyId;

    private LongFilter userId;

    private LongFilter provinceId;

    private LongFilter districtId;

    private LongFilter orderUserRouteTypeId;

    private LongFilter wardId;

    private LongFilter customerId;

    public OrderUserRouteCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
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

    public LongFilter getOrderUserRouteTypeId() {
        return orderUserRouteTypeId;
    }

    public void setOrderUserRouteTypeId(LongFilter orderUserRouteTypeId) {
        this.orderUserRouteTypeId = orderUserRouteTypeId;
    }

    public LongFilter getWardId() {
        return wardId;
    }

    public void setWardId(LongFilter wardId) {
        this.wardId = wardId;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderUserRouteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (orderUserRouteTypeId != null ? "orderUserRouteTypeId=" + orderUserRouteTypeId + ", " : "") +
                (wardId != null ? "wardId=" + wardId + ", " : "") +
                (customerId != null ? "customerId=" + customerId + ", " : "") +
            "}";
    }

}
