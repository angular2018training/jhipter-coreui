package vn.nextlogix.service.dto;

/**
 * Created by USER on 6/15/2018.
 */
public class DistrictSearchDTO {
    private String code;

    private String name;

    private String description;

    private Long provinceId;
    
    

	public DistrictSearchDTO(String code, String name, String description, Long provinceId) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.provinceId = provinceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	
	
}
