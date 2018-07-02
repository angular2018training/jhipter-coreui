package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DetailForm.
 */
@Entity
@Table(name = "detail_form")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "detailform")
public class DetailForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Lob
    @Column(name = "upload_file", nullable = false)
    private byte[] uploadFile;

    @Column(name = "upload_file_content_type", nullable = false)
    private String uploadFileContentType;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne
    private Province province;

    @ManyToOne
    private District district;

    @ManyToOne
    private MasterForm masterFormParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public DetailForm description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public DetailForm createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public byte[] getUploadFile() {
        return uploadFile;
    }

    public DetailForm uploadFile(byte[] uploadFile) {
        this.uploadFile = uploadFile;
        return this;
    }

    public void setUploadFile(byte[] uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileContentType() {
        return uploadFileContentType;
    }

    public DetailForm uploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
        return this;
    }

    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public DetailForm isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Province getProvince() {
        return province;
    }

    public DetailForm province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public DetailForm district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public MasterForm getMasterFormParent() {
        return masterFormParent;
    }

    public DetailForm masterFormParent(MasterForm masterForm) {
        this.masterFormParent = masterForm;
        return this;
    }

    public void setMasterFormParent(MasterForm masterForm) {
        this.masterFormParent = masterForm;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetailForm detailForm = (DetailForm) o;
        if (detailForm.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detailForm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DetailForm{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", uploadFile='" + getUploadFile() + "'" +
            ", uploadFileContentType='" + getUploadFileContentType() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
