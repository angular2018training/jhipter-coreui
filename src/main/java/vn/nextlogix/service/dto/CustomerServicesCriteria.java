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
 * Criteria class for the CustomerServices entity. This class is used in CustomerServicesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /customer-services?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CustomerServicesCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter discountPercent;

    private DoubleFilter increasePercent;

    private DoubleFilter decreasePercent;

    private LongFilter companyId;

    private LongFilter orderServicesId;

    private LongFilter quotationId;

    private LongFilter customerParentId;

    public CustomerServicesCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(DoubleFilter discountPercent) {
        this.discountPercent = discountPercent;
    }

    public DoubleFilter getIncreasePercent() {
        return increasePercent;
    }

    public void setIncreasePercent(DoubleFilter increasePercent) {
        this.increasePercent = increasePercent;
    }

    public DoubleFilter getDecreasePercent() {
        return decreasePercent;
    }

    public void setDecreasePercent(DoubleFilter decreasePercent) {
        this.decreasePercent = decreasePercent;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getOrderServicesId() {
        return orderServicesId;
    }

    public void setOrderServicesId(LongFilter orderServicesId) {
        this.orderServicesId = orderServicesId;
    }

    public LongFilter getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(LongFilter quotationId) {
        this.quotationId = quotationId;
    }

    public LongFilter getCustomerParentId() {
        return customerParentId;
    }

    public void setCustomerParentId(LongFilter customerParentId) {
        this.customerParentId = customerParentId;
    }

    @Override
    public String toString() {
        return "CustomerServicesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (discountPercent != null ? "discountPercent=" + discountPercent + ", " : "") +
                (increasePercent != null ? "increasePercent=" + increasePercent + ", " : "") +
                (decreasePercent != null ? "decreasePercent=" + decreasePercent + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (orderServicesId != null ? "orderServicesId=" + orderServicesId + ", " : "") +
                (quotationId != null ? "quotationId=" + quotationId + ", " : "") +
                (customerParentId != null ? "customerParentId=" + customerParentId + ", " : "") +
            "}";
    }

}
