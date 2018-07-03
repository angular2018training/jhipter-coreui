package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerWarehouse entity.
 */
public class CustomerWarehouseDTO implements Serializable {

    private Long id;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long warehouseId;

    private WarehouseDTO  warehouseDTO;


    private Long customerParentId;

    private CustomerDTO  customerParentDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyDTO getCompanyDTO() {
        return this.companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO ) {
        this.companyDTO = companyDTO;
    }
    public Long getCompanyId() {
        return companyId;
        }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
        }


    public WarehouseDTO getWarehouseDTO() {
        return this.warehouseDTO;
    }

    public void setWarehouseDTO(WarehouseDTO warehouseDTO ) {
        this.warehouseDTO = warehouseDTO;
    }
    public Long getWarehouseId() {
        return warehouseId;
        }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        }


    public CustomerDTO getCustomerParentDTO() {
        return this.customerParentDTO;
    }

    public void setCustomerParentDTO(CustomerDTO customerParentDTO ) {
        this.customerParentDTO = customerParentDTO;
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

        CustomerWarehouseDTO customerWarehouseDTO = (CustomerWarehouseDTO) o;
        if(customerWarehouseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerWarehouseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerWarehouseDTO{" +
            "id=" + getId() +
            "}";
    }
}
