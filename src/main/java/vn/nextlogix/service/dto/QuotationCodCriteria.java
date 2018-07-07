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
 * Criteria class for the QuotationCod entity. This class is used in QuotationCodResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotation-cods?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationCodCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter amountFrom;

    private DoubleFilter amountTo;

    private DoubleFilter feeAmountMin;

    private DoubleFilter feeAmount;

    private LongFilter companyId;

    private LongFilter districtTypeId;

    private LongFilter quotationParentId;

    public QuotationCodCriteria() {
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

    public DoubleFilter getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(DoubleFilter feeAmount) {
        this.feeAmount = feeAmount;
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
        return "QuotationCodCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (amountFrom != null ? "amountFrom=" + amountFrom + ", " : "") +
                (amountTo != null ? "amountTo=" + amountTo + ", " : "") +
                (feeAmountMin != null ? "feeAmountMin=" + feeAmountMin + ", " : "") +
                (feeAmount != null ? "feeAmount=" + feeAmount + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (districtTypeId != null ? "districtTypeId=" + districtTypeId + ", " : "") +
                (quotationParentId != null ? "quotationParentId=" + quotationParentId + ", " : "") +
            "}";
    }

}
