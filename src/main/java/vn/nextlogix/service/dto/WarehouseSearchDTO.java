    package vn.nextlogix.service.dto;


    import java.time.Instant;
    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the Warehouse entity.
 */
public class WarehouseSearchDTO implements Serializable {

private Long id;


private String name;


private String contactName;


private String contactPhone;


private String address;


private Instant createDate;

private Long companyId;



private Long provinceId;



private Long districtId;



private Long wardId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public String getName() {
    return name;
    }

public void setName(String name) {
    this.name = name;
    }

public String getContactName() {
    return contactName;
    }

public void setContactName(String contactName) {
    this.contactName = contactName;
    }

public String getContactPhone() {
    return contactPhone;
    }

public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
    }

public String getAddress() {
    return address;
    }

public void setAddress(String address) {
    this.address = address;
    }

public Instant getCreateDate() {
    return createDate;
    }

public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
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



public Long getWardId() {
    return wardId;
    }

public void setWardId(Long wardId) {
    this.wardId = wardId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

WarehouseSearchDTO warehouseSearchDTO = (WarehouseSearchDTO) o;
    if(warehouseSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), warehouseSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "WarehouseSearchDTO{" +
    "id=" + getId() +
    ", name='" + getName() + "'" +
    ", contactName='" + getContactName() + "'" +
    ", contactPhone='" + getContactPhone() + "'" +
    ", address='" + getAddress() + "'" +
    ", createDate='" + getCreateDate() + "'" +
    "}";
    }
    }
