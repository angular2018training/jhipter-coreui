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
 * Criteria class for the Quotation entity. This class is used in QuotationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotations?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private BooleanFilter isActive;

    private InstantFilter createDate;

    private LongFilter quotationItemId;

    private LongFilter companyId;

    public QuotationCriteria() {
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

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
    }

    public LongFilter getQuotationItemId() {
        return quotationItemId;
    }

    public void setQuotationItemId(LongFilter quotationItemId) {
        this.quotationItemId = quotationItemId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "QuotationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (quotationItemId != null ? "quotationItemId=" + quotationItemId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
            "}";
    }

}
