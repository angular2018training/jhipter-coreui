package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the QuotationDomesticDelivery entity.
 */
public class QuotationDomesticDeliveryDTO implements Serializable {

    private Long id;

    @NotNull
    private Double amount;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long districtTypeId;

    private DistrictTypeDTO  districtTypeDTO;


    private Long regionId;

    private RegionDTO  regionDTO;


    private Long weightId;

    private WeightDTO  weightDTO;


    private Long quotationParentId;

    private QuotationDTO  quotationParentDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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


    public WeightDTO getWeightDTO() {
        return this.weightDTO;
    }

    public void setWeightDTO(WeightDTO weightDTO ) {
        this.weightDTO = weightDTO;
    }
    public Long getWeightId() {
        return weightId;
        }

    public void setWeightId(Long weightId) {
        this.weightId = weightId;
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

        QuotationDomesticDeliveryDTO quotationDomesticDeliveryDTO = (QuotationDomesticDeliveryDTO) o;
        if(quotationDomesticDeliveryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationDomesticDeliveryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationDomesticDeliveryDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            "}";
    }
}
