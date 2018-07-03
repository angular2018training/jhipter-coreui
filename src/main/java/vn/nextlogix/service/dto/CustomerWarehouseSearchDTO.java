    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the CustomerWarehouse entity.
 */
public class CustomerWarehouseSearchDTO implements Serializable {

private Long id;

private Long companyId;



private Long warehouseId;



private Long customerParentId;



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



public Long getWarehouseId() {
    return warehouseId;
    }

public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
    }



public Long getCustomerParentId() {
    return customerParentId;
    }

public void setCustomerParentId(Long customerId) {
    this.customerParentId = customerId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

CustomerWarehouseSearchDTO customerWarehouseSearchDTO = (CustomerWarehouseSearchDTO) o;
    if(customerWarehouseSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), customerWarehouseSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "CustomerWarehouseSearchDTO{" +
    "id=" + getId() +
    "}";
    }
    }
