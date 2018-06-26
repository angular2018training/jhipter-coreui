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
 * Criteria class for the Customer entity. This class is used in CustomerResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /customers?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CustomerCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private StringFilter address;

    private StringFilter email;

    private StringFilter phone;

    private StringFilter password;

    private BooleanFilter isActive;

    private InstantFilter createDate;

    private InstantFilter lastLoginDate;

    private StringFilter apiToken;

    private LongFilter legalId;

    private LongFilter paymentId;

    private LongFilter warehouseId;

    private LongFilter customerPostOfficeId;

    private LongFilter companyId;

    private LongFilter manageUserId;

    private LongFilter saleUserId;

    private LongFilter provinceId;

    private LongFilter districtId;

    private LongFilter customerTypeId;

    private LongFilter customerSourceId;

    public CustomerCriteria() {
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

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public StringFilter getPassword() {
        return password;
    }

    public void setPassword(StringFilter password) {
        this.password = password;
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

    public InstantFilter getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(InstantFilter lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public StringFilter getApiToken() {
        return apiToken;
    }

    public void setApiToken(StringFilter apiToken) {
        this.apiToken = apiToken;
    }

    public LongFilter getLegalId() {
        return legalId;
    }

    public void setLegalId(LongFilter legalId) {
        this.legalId = legalId;
    }

    public LongFilter getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(LongFilter paymentId) {
        this.paymentId = paymentId;
    }

    public LongFilter getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(LongFilter warehouseId) {
        this.warehouseId = warehouseId;
    }

    public LongFilter getCustomerPostOfficeId() {
        return customerPostOfficeId;
    }

    public void setCustomerPostOfficeId(LongFilter customerPostOfficeId) {
        this.customerPostOfficeId = customerPostOfficeId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getManageUserId() {
        return manageUserId;
    }

    public void setManageUserId(LongFilter manageUserId) {
        this.manageUserId = manageUserId;
    }

    public LongFilter getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(LongFilter saleUserId) {
        this.saleUserId = saleUserId;
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

    public LongFilter getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(LongFilter customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public LongFilter getCustomerSourceId() {
        return customerSourceId;
    }

    public void setCustomerSourceId(LongFilter customerSourceId) {
        this.customerSourceId = customerSourceId;
    }

    @Override
    public String toString() {
        return "CustomerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (password != null ? "password=" + password + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (lastLoginDate != null ? "lastLoginDate=" + lastLoginDate + ", " : "") +
                (apiToken != null ? "apiToken=" + apiToken + ", " : "") +
                (legalId != null ? "legalId=" + legalId + ", " : "") +
                (paymentId != null ? "paymentId=" + paymentId + ", " : "") +
                (warehouseId != null ? "warehouseId=" + warehouseId + ", " : "") +
                (customerPostOfficeId != null ? "customerPostOfficeId=" + customerPostOfficeId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (manageUserId != null ? "manageUserId=" + manageUserId + ", " : "") +
                (saleUserId != null ? "saleUserId=" + saleUserId + ", " : "") +
                (provinceId != null ? "provinceId=" + provinceId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (customerTypeId != null ? "customerTypeId=" + customerTypeId + ", " : "") +
                (customerSourceId != null ? "customerSourceId=" + customerSourceId + ", " : "") +
            "}";
    }

}
