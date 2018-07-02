    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the PostOffice entity.
 */
public class PostOfficeSearchDTO implements Serializable {

private Long id;


private String code;


private String name;


private String address;


private Integer sortOrder;


private String phone;


private Double latitude;


private Double longitude;


private String description;

private Long companyId;



private Long provinceId;



private Long districtId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
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

public String getAddress() {
    return address;
    }

public void setAddress(String address) {
    this.address = address;
    }

public Integer getSortOrder() {
    return sortOrder;
    }

public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
    }

public String getPhone() {
    return phone;
    }

public void setPhone(String phone) {
    this.phone = phone;
    }

public Double getLatitude() {
    return latitude;
    }

public void setLatitude(Double latitude) {
    this.latitude = latitude;
    }

public Double getLongitude() {
    return longitude;
    }

public void setLongitude(Double longitude) {
    this.longitude = longitude;
    }

public String getDescription() {
    return description;
    }

public void setDescription(String description) {
    this.description = description;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getProvinceId() {
    return provinceId;
    }

public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
    }



public Long getDistrictId() {
    return districtId;
    }

public void setDistrictId(Long districtId) {
    this.districtId = districtId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

PostOfficeSearchDTO postOfficeSearchDTO = (PostOfficeSearchDTO) o;
    if(postOfficeSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), postOfficeSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "PostOfficeSearchDTO{" +
    "id=" + getId() +
    ", code='" + getCode() + "'" +
    ", name='" + getName() + "'" +
    ", address='" + getAddress() + "'" +
    ", sortOrder=" + getSortOrder() +
    ", phone='" + getPhone() + "'" +
    ", latitude=" + getLatitude() +
    ", longitude=" + getLongitude() +
    ", description='" + getDescription() + "'" +
    "}";
    }
    }
