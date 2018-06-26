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
 * Criteria class for the CustomerPostOffice entity. This class is used in CustomerPostOfficeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /customer-post-offices?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CustomerPostOfficeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter code;

    private BooleanFilter isActive;

    private InstantFilter createDate;

    private LongFilter customerId;

    private LongFilter companyId;

    private LongFilter postOfficeId;

    public CustomerPostOfficeCriteria() {
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

    public LongFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(LongFilter postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    @Override
    public String toString() {
        return "CustomerPostOfficeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (customerId != null ? "customerId=" + customerId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (postOfficeId != null ? "postOfficeId=" + postOfficeId + ", " : "") +
            "}";
    }

}
