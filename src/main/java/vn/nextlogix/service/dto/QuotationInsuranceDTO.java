package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the QuotationInsurance entity.
 */
public class QuotationInsuranceDTO implements Serializable {

    private Long id;

    @NotNull
    private Double amountFrom;

    @NotNull
    private Double amountTo;

    @NotNull
    private Double feeAmountMin;

    @NotNull
    private Double feeAmountMax;

    @NotNull
    private Double feePercent;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long districtTypeId;

    private DistrictTypeDTO  districtTypeDTO;


    private Long quotationParentId;

    private QuotationDTO  quotationParentDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(Double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Double getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(Double amountTo) {
        this.amountTo = amountTo;
    }

    public Double getFeeAmountMin() {
        return feeAmountMin;
    }

    public void setFeeAmountMin(Double feeAmountMin) {
        this.feeAmountMin = feeAmountMin;
    }

    public Double getFeeAmountMax() {
        return feeAmountMax;
    }

    public void setFeeAmountMax(Double feeAmountMax) {
        this.feeAmountMax = feeAmountMax;
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

        QuotationInsuranceDTO quotationInsuranceDTO = (QuotationInsuranceDTO) o;
        if(quotationInsuranceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationInsuranceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationInsuranceDTO{" +
            "id=" + getId() +
            ", amountFrom=" + getAmountFrom() +
            ", amountTo=" + getAmountTo() +
            ", feeAmountMin=" + getFeeAmountMin() +
            ", feeAmountMax=" + getFeeAmountMax() +
            ", feePercent=" + getFeePercent() +
            "}";
    }
}
