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
 * Criteria class for the QuotationPickup entity. This class is used in QuotationPickupResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotation-pickups?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationPickupCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter amount;

    private LongFilter companyId;

    private LongFilter provinceId;

    private LongFilter districtTypeId;

    private LongFilter quotationParentId;

    public QuotationPickupCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getAmount() {
        return amount;
    }

    public void setAmount(DoubleFilter amount) {
        this.amount = amount;
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
        return "QuotationPickupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (districtTypeId != null ? "districtTypeId=" + districtTypeId + ", " : "") +
                (quotationParentId != null ? "quotationParentId=" + quotationParentId + ", " : "") +
            "}";
    }

}
