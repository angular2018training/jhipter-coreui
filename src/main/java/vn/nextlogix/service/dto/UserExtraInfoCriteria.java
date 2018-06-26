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
 * Criteria class for the UserExtraInfo entity. This class is used in UserExtraInfoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /user-extra-infos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UserExtraInfoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter email;

    private StringFilter phone;

    private StringFilter address;

    private InstantFilter validDate;

    private InstantFilter lastLoginDate;

    private InstantFilter contractExpirationDate;

    private LongFilter userPositionId;

    private LongFilter companyId;

    public UserExtraInfoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public InstantFilter getValidDate() {
        return validDate;
    }

    public void setValidDate(InstantFilter validDate) {
        this.validDate = validDate;
    }

    public InstantFilter getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(InstantFilter lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public InstantFilter getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(InstantFilter contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }

    public LongFilter getUserPositionId() {
        return userPositionId;
    }

    public void setUserPositionId(LongFilter userPositionId) {
        this.userPositionId = userPositionId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserExtraInfoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (validDate != null ? "validDate=" + validDate + ", " : "") +
                (lastLoginDate != null ? "lastLoginDate=" + lastLoginDate + ", " : "") +
                (contractExpirationDate != null ? "contractExpirationDate=" + contractExpirationDate + ", " : "") +
                (userPositionId != null ? "userPositionId=" + userPositionId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
            "}";
    }

}
