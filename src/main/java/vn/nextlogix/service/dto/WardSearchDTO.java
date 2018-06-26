    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the Ward entity.
 */
public class WardSearchDTO implements Serializable {

private Long id;


private String code;


private String name;


private String description;

private Long companyId;



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

WardSearchDTO wardSearchDTO = (WardSearchDTO) o;
    if(wardSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), wardSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "WardSearchDTO{" +
    "id=" + getId() +
    ", code='" + getCode() + "'" +
    ", name='" + getName() + "'" +
    ", description='" + getDescription() + "'" +
    "}";
    }
    }
