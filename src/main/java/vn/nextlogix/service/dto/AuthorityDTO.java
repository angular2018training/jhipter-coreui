package vn.nextlogix.service.dto;

import vn.nextlogix.domain.Authority;

public class AuthorityDTO {
	private String name;
	private String description;
	
	
	public AuthorityDTO(Authority authority) {
		this.name = authority.getName();
		this.description = authority.getDescription();
	}
	public AuthorityDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
