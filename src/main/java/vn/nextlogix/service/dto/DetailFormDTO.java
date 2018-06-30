package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the DetailForm entity.
 */
public class DetailFormDTO implements Serializable {

    private Long id;

    private String description;

    @NotNull
    private Instant createDate;

    @NotNull
    @Lob
    private byte[] uploadFile;
    private String uploadFileContentType;

    @NotNull
    private Boolean isActive;

    private Long masterFormId;

    private MasterFormDTO  masterFormDTO;


    private Long provinceId;

    private ProvinceDTO  provinceDTO;


    private Long districtId;

    private DistrictDTO  districtDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public byte[] getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(byte[] uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileContentType() {
        return uploadFileContentType;
    }

    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public MasterFormDTO getMasterFormDTO() {
        return this.masterFormDTO;
    }

    public void setMasterFormDTO(MasterFormDTO masterFormDTO ) {
        this.masterFormDTO = masterFormDTO;
    }
    public Long getMasterFormId() {
        return masterFormId;
        }

    public void setMasterFormId(Long masterFormId) {
        this.masterFormId = masterFormId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DetailFormDTO detailFormDTO = (DetailFormDTO) o;
        if(detailFormDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detailFormDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DetailFormDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", uploadFile='" + getUploadFile() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
