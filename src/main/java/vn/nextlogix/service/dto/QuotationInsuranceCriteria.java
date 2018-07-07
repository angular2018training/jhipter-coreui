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
 * Criteria class for the QuotationInsurance entity. This class is used in QuotationInsuranceResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotation-insurances?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationInsuranceCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter amountFrom;

    private DoubleFilter amountTo;

    private DoubleFilter feeAmountMin;

    private DoubleFilter feeAmountMax;

    private DoubleFilter feePercent;

    private LongFilter companyId;

    private LongFilter districtTypeId;

    private LongFilter quotationParentId;

    public QuotationInsuranceCriteria() {
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

    public DoubleFilter getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(DoubleFilter feePercent) {
        this.feePercent = feePercent;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getDistrictTypeId() {
        return districtTypeId;
    }

    public void setDistrictTypeId(LongFilter districtTypeId) {
        this.districtTypeId = districtTypeId;
    }

    public LongFilter getQuotationParentId() {
        return quotationParentId;
    }

    public void setQuotationParentId(LongFilter quotationParentId) {
        this.quotationParentId = quotationParentId;
    }

    @Override
    public String toString() {
        return "QuotationInsuranceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (amountFrom != null ? "amountFrom=" + amountFrom + ", " : "") +
                (amountTo != null ? "amountTo=" + amountTo + ", " : "") +
                (feeAmountMin != null ? "feeAmountMin=" + feeAmountMin + ", " : "") +
                (feeAmountMax != null ? "feeAmountMax=" + feeAmountMax + ", " : "") +
                (feePercent != null ? "feePercent=" + feePercent + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (districtTypeId != null ? "districtTypeId=" + districtTypeId + ", " : "") +
                (quotationParentId != null ? "quotationParentId=" + quotationParentId + ", " : "") +
            "}";
    }

}
