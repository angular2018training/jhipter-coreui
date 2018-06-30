    package vn.nextlogix.service.dto;


    import java.time.Instant;
    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;
    import javax.persistence.Lob;

/**
 * A DTO for the MasterForm entity.
 */
public class MasterFormSearchDTO implements Serializable {

private Long id;


private String code;


private String note;


private Instant receiveTime;


@Lob
private byte[] imageFile;
private String imageFileContentType;


private Boolean isActive;

private Long provinceId;



private Long districtId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public String getCode() {
    return code;
    }

public void setCode(String code) {
    this.code = code;
    }

public String getNote() {
    return note;
    }

public void setNote(String note) {
    this.note = note;
    }

public Instant getReceiveTime() {
    return receiveTime;
    }

public void setReceiveTime(Instant receiveTime) {
    this.receiveTime = receiveTime;
    }

public byte[] getImageFile() {
    return imageFile;
    }

public void setImageFile(byte[] imageFile) {
    this.imageFile = imageFile;
    }

public String getImageFileContentType() {
    return imageFileContentType;
    }

public void setImageFileContentType(String imageFileContentType) {
    this.imageFileContentType = imageFileContentType;
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


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

MasterFormSearchDTO masterFormSearchDTO = (MasterFormSearchDTO) o;
    if(masterFormSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), masterFormSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "MasterFormSearchDTO{" +
    "id=" + getId() +
    ", code='" + getCode() + "'" +
    ", note='" + getNote() + "'" +
    ", receiveTime='" + getReceiveTime() + "'" +
    ", imageFile='" + getImageFile() + "'" +
    ", isActive='" + isIsActive() + "'" +
    "}";
    }
    }
