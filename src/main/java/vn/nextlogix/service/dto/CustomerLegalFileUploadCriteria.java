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
 * Criteria class for the CustomerLegalFileUpload entity. This class is used in CustomerLegalFileUploadResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /customer-legal-file-uploads?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CustomerLegalFileUploadCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter companyId;

    private LongFilter fileUploadId;

    private LongFilter customerLegalParentId;

    public CustomerLegalFileUploadCriteria() {
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

    public LongFilter getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(LongFilter fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    public LongFilter getCustomerLegalParentId() {
        return customerLegalParentId;
    }

    public void setCustomerLegalParentId(LongFilter customerLegalParentId) {
        this.customerLegalParentId = customerLegalParentId;
    }

    @Override
    public String toString() {
        return "CustomerLegalFileUploadCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (fileUploadId != null ? "fileUploadId=" + fileUploadId + ", " : "") +
                (customerLegalParentId != null ? "customerLegalParentId=" + customerLegalParentId + ", " : "") +
            "}";
    }

}
