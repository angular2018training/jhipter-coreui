    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the OrderUserRoute entity.
 */
public class OrderUserRouteSearchDTO implements Serializable {

private Long id;

private Long companyId;



private Long userId;



private Long provinceId;



private Long districtId;



private Long orderUserRouteTypeId;



private Long wardId;



private Long customerId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getUserId() {
    return userId;
    }

public void setUserId(Long userExtraInfoId) {
    this.userId = userExtraInfoId;
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



public Long getOrderUserRouteTypeId() {
    return orderUserRouteTypeId;
    }

public void setOrderUserRouteTypeId(Long orderUserRouteTypeId) {
    this.orderUserRouteTypeId = orderUserRouteTypeId;
    }



public Long getWardId() {
    return wardId;
    }

public void setWardId(Long districtId) {
    this.wardId = districtId;
    }



public Long getCustomerId() {
    return customerId;
    }

public void setCustomerId(Long customerId) {
    this.customerId = customerId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

OrderUserRouteSearchDTO orderUserRouteSearchDTO = (OrderUserRouteSearchDTO) o;
    if(orderUserRouteSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), orderUserRouteSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "OrderUserRouteSearchDTO{" +
    "id=" + getId() +
    "}";
    }
    }
