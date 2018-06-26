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
public class WarehouseDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String contactName;

    @NotNull
    private String contactPhone;

    private String address;

    @NotNull
    private Instant createDate;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long provinceId;

    private ProvinceDTO  provinceDTO;


    private Long districtId;

    private DistrictDTO  districtDTO;


    private Long wardId;

    private WardDTO  wardDTO;


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


    public WardDTO getWardDTO() {
        return this.wardDTO;
    }

    public void setWardDTO(WardDTO wardDTO ) {
        this.wardDTO = wardDTO;
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

        WarehouseDTO warehouseDTO = (WarehouseDTO) o;
        if(warehouseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), warehouseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WarehouseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
