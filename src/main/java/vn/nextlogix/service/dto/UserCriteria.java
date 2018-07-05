package vn.nextlogix.service.dto;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

public class UserCriteria {
	private StringFilter login;
	private StringFilter name;
	private StringFilter email;
	private StringFilter phone;
	private BooleanFilter activated;
	private InstantFilter createdDate;
	private LongFilter companyId;
	private LongFilter userGroupId;
	private LongFilter postOfficeId;
	public StringFilter getLogin() {
		return login;
	}
	public void setLogin(StringFilter login) {
		this.login = login;
	}
	public StringFilter getName() {
		return name;
	}
	public void setName(StringFilter name) {
		this.name = name;
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
	public BooleanFilter getActivated() {
		return activated;
	}
	public void setActivated(BooleanFilter activated) {
		this.activated = activated;
	}
	
	public InstantFilter getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(InstantFilter createdDate) {
		this.createdDate = createdDate;
	}
	public LongFilter getCompanyId() {
		return companyId;
	}
	public void setCompanyId(LongFilter companyId) {
		this.companyId = companyId;
	}
	public LongFilter getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(LongFilter userGroupId) {
		this.userGroupId = userGroupId;
	}
	public LongFilter getPostOfficeId() {
		return postOfficeId;
	}
	public void setPostOfficeId(LongFilter postOfficeId) {
		this.postOfficeId = postOfficeId;
	}
	
	
	
	
	
}
