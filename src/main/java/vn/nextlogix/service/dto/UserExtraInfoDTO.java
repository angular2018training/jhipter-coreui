package vn.nextlogix.service.dto;


import java.time.Instant;
import java.time.LocalDate;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the UserExtraInfo entity.
 */
public class UserExtraInfoDTO implements Serializable {

    private Long id;


    private String phone;

    private String address;

    @NotNull
    private LocalDate validDate;

    private Instant lastLoginDate;

    @Lob
    private byte[] contractFile;
    private String contractFileContentType;

    private Instant contractExpirationDate;

    private Long companyId;

    private CompanyDTO  companyDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

    public LocalDate getValidDate() {
		return validDate;
	}

	public void setValidDate(LocalDate validDate) {
		this.validDate = validDate;
	}

	public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public byte[] getContractFile() {
        return contractFile;
    }

    public void setContractFile(byte[] contractFile) {
        this.contractFile = contractFile;
    }

    public String getContractFileContentType() {
        return contractFileContentType;
    }

    public void setContractFileContentType(String contractFileContentType) {
        this.contractFileContentType = contractFileContentType;
    }

    public Instant getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(Instant contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
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

        UserExtraInfoDTO userExtraInfoDTO = (UserExtraInfoDTO) o;
        if(userExtraInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtraInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtraInfoDTO{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", validDate='" + getValidDate() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", contractFile='" + getContractFile() + "'" +
            ", contractExpirationDate='" + getContractExpirationDate() + "'" +
            "}";
    }
}
