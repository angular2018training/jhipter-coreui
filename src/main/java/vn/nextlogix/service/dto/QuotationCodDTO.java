package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the QuotationCod entity.
 */
public class QuotationCodDTO implements Serializable {

    private Long id;

    @NotNull
    private Double amountFrom;

    @NotNull
    private Double amountTo;

    @NotNull
    private Double feeAmountMin;

    @NotNull
    private Double feeAmount;

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

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
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

        QuotationCodDTO quotationCodDTO = (QuotationCodDTO) o;
        if(quotationCodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationCodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationCodDTO{" +
            "id=" + getId() +
            ", amountFrom=" + getAmountFrom() +
            ", amountTo=" + getAmountTo() +
            ", feeAmountMin=" + getFeeAmountMin() +
            ", feeAmount=" + getFeeAmount() +
            "}";
    }
}
