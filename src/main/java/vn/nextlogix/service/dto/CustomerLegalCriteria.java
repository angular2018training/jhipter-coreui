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
 * Criteria class for the CustomerLegal entity. This class is used in CustomerLegalResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /customer-legals?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CustomerLegalCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter contractCustomerName;

    private StringFilter contractAddress;

    private StringFilter contractContactName;

    private StringFilter contractContactPhone;

    private StringFilter taxCode;

    private StringFilter contractExpirationDate;

    private LongFilter companyId;

    private LongFilter provinceId;

    private LongFilter districtId;

    private LongFilter fileUploadId;

    public CustomerLegalCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getContractCustomerName() {
        return contractCustomerName;
    }

    public void setContractCustomerName(StringFilter contractCustomerName) {
        this.contractCustomerName = contractCustomerName;
    }

    public StringFilter getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(StringFilter contractAddress) {
        this.contractAddress = contractAddress;
    }

    public StringFilter getContractContactName() {
        return contractContactName;
    }

    public void setContractContactName(StringFilter contractContactName) {
        this.contractContactName = contractContactName;
    }

    public StringFilter getContractContactPhone() {
        return contractContactPhone;
    }

    public void setContractContactPhone(StringFilter contractContactPhone) {
        this.contractContactPhone = contractContactPhone;
    }

    public StringFilter getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(StringFilter taxCode) {
        this.taxCode = taxCode;
    }

    public StringFilter getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(StringFilter contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
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

    public LongFilter getDistrictId() {
        return districtId;
    }

    public void setDistrictId(LongFilter districtId) {
        this.districtId = districtId;
    }

    public LongFilter getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(LongFilter fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    @Override
    public String toString() {
        return "CustomerLegalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (contractCustomerName != null ? "contractCustomerName=" + contractCustomerName + ", " : "") +
                (contractAddress != null ? "contractAddress=" + contractAddress + ", " : "") +
                (contractContactName != null ? "contractContactName=" + contractContactName + ", " : "") +
                (contractContactPhone != null ? "contractContactPhone=" + contractContactPhone + ", " : "") +
                (taxCode != null ? "taxCode=" + taxCode + ", " : "") +
                (contractExpirationDate != null ? "contractExpirationDate=" + contractExpirationDate + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (fileUploadId != null ? "fileUploadId=" + fileUploadId + ", " : "") +
            "}";
    }

}
