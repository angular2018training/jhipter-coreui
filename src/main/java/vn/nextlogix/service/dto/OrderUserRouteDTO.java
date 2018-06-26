package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderUserRoute entity.
 */
public class OrderUserRouteDTO implements Serializable {

    private Long id;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long userId;

    private UserExtraInfoDTO  userDTO;


    private Long provinceId;

    private ProvinceDTO  provinceDTO;


    private Long districtId;

    private DistrictDTO  districtDTO;


    private Long orderUserRouteTypeId;

    private OrderUserRouteTypeDTO  orderUserRouteTypeDTO;


    private Long wardId;

    private DistrictDTO  wardDTO;


    private Long customerId;

    private CustomerDTO  customerDTO;


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


    public UserExtraInfoDTO getUserDTO() {
        return this.userDTO;
    }

    public void setUserDTO(UserExtraInfoDTO userDTO ) {
        this.userDTO = userDTO;
    }
    public Long getUserId() {
        return userId;
        }

    public void setUserId(Long userExtraInfoId) {
        this.userId = userExtraInfoId;
        }


    public ProvinceDTO getProvinceDTO() {
        return this.provinceDTO;
    }

    public void setProvinceDTO(ProvinceDTO provinceDTO ) {
        this.provinceDTO = provinceDTO;
    }
    public Long getProvinceId() {
        return provinceId;
        }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
        }


    public DistrictDTO getDistrictDTO() {
        return this.districtDTO;
    }

    public void setDistrictDTO(DistrictDTO districtDTO ) {
        this.districtDTO = districtDTO;
    }
    public Long getDistrictId() {
        return districtId;
        }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
        }


    public OrderUserRouteTypeDTO getOrderUserRouteTypeDTO() {
        return this.orderUserRouteTypeDTO;
    }

    public void setOrderUserRouteTypeDTO(OrderUserRouteTypeDTO orderUserRouteTypeDTO ) {
        this.orderUserRouteTypeDTO = orderUserRouteTypeDTO;
    }
    public Long getOrderUserRouteTypeId() {
        return orderUserRouteTypeId;
        }

    public void setOrderUserRouteTypeId(Long orderUserRouteTypeId) {
        this.orderUserRouteTypeId = orderUserRouteTypeId;
        }


    public DistrictDTO getWardDTO() {
        return this.wardDTO;
    }

    public void setWardDTO(DistrictDTO wardDTO ) {
        this.wardDTO = wardDTO;
    }
    public Long getWardId() {
        return wardId;
        }

    public void setWardId(Long districtId) {
        this.wardId = districtId;
        }


    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO ) {
        this.customerDTO = customerDTO;
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

        OrderUserRouteDTO orderUserRouteDTO = (OrderUserRouteDTO) o;
        if(orderUserRouteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderUserRouteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderUserRouteDTO{" +
            "id=" + getId() +
            "}";
    }
}
