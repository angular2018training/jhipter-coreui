package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the QuotationItem entity.
 */
public class QuotationItemDTO implements Serializable {

    private Long id;

    @NotNull
    @Lob
    private byte[] quotationFile;
    private String quotationFileContentType;

    @NotNull
    private Instant createDate;

    private Long quotationId;

    private QuotationDTO  quotationDTO;


    private Long companyId;

    private CompanyDTO  companyDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getQuotationFile() {
        return quotationFile;
    }

    public void setQuotationFile(byte[] quotationFile) {
        this.quotationFile = quotationFile;
    }

    public String getQuotationFileContentType() {
        return quotationFileContentType;
    }

    public void setQuotationFileContentType(String quotationFileContentType) {
        this.quotationFileContentType = quotationFileContentType;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public QuotationDTO getQuotationDTO() {
        return this.quotationDTO;
    }

    public void setQuotationDTO(QuotationDTO quotationDTO ) {
        this.quotationDTO = quotationDTO;
    }
    public Long getQuotationId() {
        return quotationId;
        }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuotationItemDTO quotationItemDTO = (QuotationItemDTO) o;
        if(quotationItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationItemDTO{" +
            "id=" + getId() +
            ", quotationFile='" + getQuotationFile() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
