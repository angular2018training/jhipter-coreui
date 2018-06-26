package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the FileUpload entity.
 */
public class FileUploadDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Lob
    private byte[] content;
    private String contentContentType;

    @NotNull
    private Instant uploadTime;

    @NotNull
    private String contentType;

    private Long companyId;

    private CompanyDTO  companyDTO;


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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentContentType() {
        return contentContentType;
    }

    public void setContentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
    }

    public Instant getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Instant uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

        FileUploadDTO fileUploadDTO = (FileUploadDTO) o;
        if(fileUploadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileUploadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileUploadDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", content='" + getContent() + "'" +
            ", uploadTime='" + getUploadTime() + "'" +
            ", contentType='" + getContentType() + "'" +
            "}";
    }
}
