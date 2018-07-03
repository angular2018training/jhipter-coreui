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
 * Criteria class for the UserPostOffice entity. This class is used in UserPostOfficeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /user-post-offices?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UserPostOfficeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter companyId;

    private LongFilter postOfficeId;

    private LongFilter userGroupId;

    private LongFilter userExtraInfoParentId;

    public UserPostOfficeCriteria() {
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

    public LongFilter getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(LongFilter postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    public LongFilter getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(LongFilter userGroupId) {
        this.userGroupId = userGroupId;
    }

    public LongFilter getUserExtraInfoParentId() {
        return userExtraInfoParentId;
    }

    public void setUserExtraInfoParentId(LongFilter userExtraInfoParentId) {
        this.userExtraInfoParentId = userExtraInfoParentId;
    }

    @Override
    public String toString() {
        return "UserPostOfficeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (postOfficeId != null ? "postOfficeId=" + postOfficeId + ", " : "") +
                (userGroupId != null ? "userGroupId=" + userGroupId + ", " : "") +
                (userExtraInfoParentId != null ? "userExtraInfoParentId=" + userExtraInfoParentId + ", " : "") +
            "}";
    }

}
