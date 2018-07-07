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
 * Criteria class for the QuotationSubServices entity. This class is used in QuotationSubServicesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotation-sub-services?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationSubServicesCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter amountFrom;

    private DoubleFilter amountTo;

    private DoubleFilter feeAmountMin;

    private DoubleFilter feeAmountMax;

    private DoubleFilter feeAmount;

    private BooleanFilter autoSelect;

    private LongFilter companyId;

    private LongFilter orderSubServicesTypeId;

    private LongFilter orderSubServicesId;

    private LongFilter quotationParentId;

    public QuotationSubServicesCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(DoubleFilter amountFrom) {
        this.amountFrom = amountFrom;
    }

    public DoubleFilter getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(DoubleFilter amountTo) {
        this.amountTo = amountTo;
    }

    public DoubleFilter getFeeAmountMin() {
        return feeAmountMin;
    }

    public void setFeeAmountMin(DoubleFilter feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
    }

    public DoubleFilter getFeeAmountMax() {
        return feeAmountMax;
    }

    public void setFeeAmountMax(DoubleFilter feeAmountMax) {
        this.feeAmountMax = feeAmountMax;
    }

    public DoubleFilter getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(DoubleFilter feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BooleanFilter getAutoSelect() {
        return autoSelect;
    }

    public void setAutoSelect(BooleanFilter autoSelect) {
        this.autoSelect = autoSelect;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getOrderSubServicesTypeId() {
        return orderSubServicesTypeId;
    }

    public void setOrderSubServicesTypeId(LongFilter orderSubServicesTypeId) {
        this.orderSubServicesTypeId = orderSubServicesTypeId;
    }

    public LongFilter getOrderSubServicesId() {
        return orderSubServicesId;
    }

    public void setOrderSubServicesId(LongFilter orderSubServicesId) {
        this.orderSubServicesId = orderSubServicesId;
    }

    public LongFilter getQuotationParentId() {
        return quotationParentId;
    }

    public void setQuotationParentId(LongFilter quotationParentId) {
        this.quotationParentId = quotationParentId;
    }

    @Override
    public String toString() {
        return "QuotationSubServicesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (amountFrom != null ? "amountFrom=" + amountFrom + ", " : "") +
                (amountTo != null ? "amountTo=" + amountTo + ", " : "") +
                (feeAmountMin != null ? "feeAmountMin=" + feeAmountMin + ", " : "") +
                (feeAmountMax != null ? "feeAmountMax=" + feeAmountMax + ", " : "") +
                (feeAmount != null ? "feeAmount=" + feeAmount + ", " : "") +
                (autoSelect != null ? "autoSelect=" + autoSelect + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (orderSubServicesTypeId != null ? "orderSubServicesTypeId=" + orderSubServicesTypeId + ", " : "") +
                (orderSubServicesId != null ? "orderSubServicesId=" + orderSubServicesId + ", " : "") +
                (quotationParentId != null ? "quotationParentId=" + quotationParentId + ", " : "") +
            "}";
    }

}
