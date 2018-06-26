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
 * Criteria class for the QuotationItem entity. This class is used in QuotationItemResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotation-items?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationItemCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private InstantFilter createDate;

    private LongFilter quotationId;

    private LongFilter companyId;

    public QuotationItemCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
    }

    public LongFilter getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(LongFilter quotationId) {
        this.quotationId = quotationId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "QuotationItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (quotationId != null ? "quotationId=" + quotationId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
            "}";
    }

}
