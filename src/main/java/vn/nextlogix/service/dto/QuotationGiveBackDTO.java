package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the QuotationGiveBack entity.
 */
public class QuotationGiveBackDTO implements Serializable {

    private Long id;

    @NotNull
    private Double feePercent;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long districtTypeId;

    private DistrictTypeDTO  districtTypeDTO;


    private Long regionId;

    private RegionDTO  regionDTO;


    private Long quotationParentId;

    private QuotationDTO  quotationParentDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(Double feePercent) {
        this.feePercent = feePercent;
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


    public DistrictTypeDTO getDistrictTypeDTO() {
        return this.districtTypeDTO;
    }

    public void setDistrictTypeDTO(DistrictTypeDTO districtTypeDTO ) {
        this.districtTypeDTO = districtTypeDTO;
    }
    public Long getDistrictTypeId() {
        return districtTypeId;
        }

    public void setDistrictTypeId(Long districtTypeId) {
        this.districtTypeId = districtTypeId;
        }


    public RegionDTO getRegionDTO() {
        return this.regionDTO;
    }

    public void setRegionDTO(RegionDTO regionDTO ) {
        this.regionDTO = regionDTO;
    }
    public Long getRegionId() {
        return regionId;
        }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
        }


    public QuotationDTO getQuotationParentDTO() {
        return this.quotationParentDTO;
    }

    public void setQuotationParentDTO(QuotationDTO quotationParentDTO ) {
        this.quotationParentDTO = quotationParentDTO;
    }
    public Long getQuotationParentId() {
        return quotationParentId;
        }

    public void setQuotationParentId(Long quotationId) {
        this.quotationParentId = quotationId;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuotationGiveBackDTO quotationGiveBackDTO = (QuotationGiveBackDTO) o;
        if(quotationGiveBackDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationGiveBackDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationGiveBackDTO{" +
            "id=" + getId() +
            ", feePercent=" + getFeePercent() +
            "}";
    }
}
