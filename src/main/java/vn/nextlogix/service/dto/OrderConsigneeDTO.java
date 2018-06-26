package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderConsignee entity.
 */
public class OrderConsigneeDTO implements Serializable {

    private Long id;

    @NotNull
    private String address;

    @NotNull
    private String consigneePhone;

    private String consigneeName;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long districtId;

    private DistrictDTO  districtDTO;


    private Long provinceId;

    private ProvinceDTO  provinceDTO;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderConsigneeDTO orderConsigneeDTO = (OrderConsigneeDTO) o;
        if(orderConsigneeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderConsigneeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderConsigneeDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", consigneePhone='" + getConsigneePhone() + "'" +
            ", consigneeName='" + getConsigneeName() + "'" +
            "}";
    }
}
