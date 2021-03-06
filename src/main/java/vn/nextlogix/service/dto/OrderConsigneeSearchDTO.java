    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the OrderConsignee entity.
 */
public class OrderConsigneeSearchDTO implements Serializable {

private Long id;


private String address;


private String consigneePhone;


private String consigneeName;

private Long companyId;



private Long districtId;



private Long provinceId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public String getAddress() {
    return address;
    }

public void setAddress(String address) {
    this.address = address;
    }

public String getConsigneePhone() {
    return consigneePhone;
    }

public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
    }

public String getConsigneeName() {
    return consigneeName;
    }

public void setConsigneeName(String consigneeName) {
    this.consigneeName = consigneeName;
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



public Long getProvinceId() {
    return provinceId;
    }

public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

OrderConsigneeSearchDTO orderConsigneeSearchDTO = (OrderConsigneeSearchDTO) o;
    if(orderConsigneeSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), orderConsigneeSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "OrderConsigneeSearchDTO{" +
    "id=" + getId() +
    ", address='" + getAddress() + "'" +
    ", consigneePhone='" + getConsigneePhone() + "'" +
    ", consigneeName='" + getConsigneeName() + "'" +
    "}";
    }
    }
