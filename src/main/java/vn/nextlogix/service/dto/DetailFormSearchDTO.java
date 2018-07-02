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
public class DetailFormSearchDTO implements Serializable {

private Long id;


private String description;


private Instant createDate;


@Lob
private byte[] uploadFile;
private String uploadFileContentType;


private Boolean isActive;

private Long provinceId;



private Long districtId;



private Long masterFormParentId;



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


public Long getProvinceId() {
    return provinceId;
    }

public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
    }



public Long getDistrictId() {
    return districtId;
    }

public void setDistrictId(Long districtId) {
    this.districtId = districtId;
    }



public Long getMasterFormParentId() {
    return masterFormParentId;
    }

public void setMasterFormParentId(Long masterFormId) {
    this.masterFormParentId = masterFormId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

DetailFormSearchDTO detailFormSearchDTO = (DetailFormSearchDTO) o;
    if(detailFormSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), detailFormSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "DetailFormSearchDTO{" +
    "id=" + getId() +
    ", description='" + getDescription() + "'" +
    ", createDate='" + getCreateDate() + "'" +
    ", uploadFile='" + getUploadFile() + "'" +
    ", isActive='" + isIsActive() + "'" +
    "}";
    }
    }
