package vn.nextlogix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MasterForm.
 */
@Entity
@Table(name = "master_form")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "masterform")
public class MasterForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "note")
    private String note;

    @NotNull
    @Column(name = "receive_time", nullable = false)
    private Instant receiveTime;

    @NotNull
    @Lob
    @Column(name = "image_file", nullable = false)
    private byte[] imageFile;

    @Column(name = "image_file_content_type", nullable = false)
    private String imageFileContentType;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "masterFormParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DetailForm> detailFormDetailLists = new HashSet<>();

    @ManyToOne
    private Province province;

    @ManyToOne
    private District district;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public MasterForm code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public MasterForm note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getReceiveTime() {
        return receiveTime;
    }

    public MasterForm receiveTime(Instant receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public void setReceiveTime(Instant receiveTime) {
        this.receiveTime = receiveTime;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public MasterForm imageFile(byte[] imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return imageFileContentType;
    }

    public MasterForm imageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
        return this;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public MasterForm isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<DetailForm> getDetailFormDetailLists() {
        return detailFormDetailLists;
    }

    public MasterForm detailFormDetailLists(Set<DetailForm> detailForms) {
        this.detailFormDetailLists = detailForms;
        return this;
    }

    public MasterForm addDetailFormDetailList(DetailForm detailForm) {
        this.detailFormDetailLists.add(detailForm);
        detailForm.setMasterFormParent(this);
        return this;
    }

    public MasterForm removeDetailFormDetailList(DetailForm detailForm) {
        this.detailFormDetailLists.remove(detailForm);
        detailForm.setMasterFormParent(null);
        return this;
    }

    public void setDetailFormDetailLists(Set<DetailForm> detailForms) {
        this.detailFormDetailLists = detailForms;
    }

    public Province getProvince() {
        return province;
    }

    public MasterForm province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public MasterForm district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
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
        MasterForm masterForm = (MasterForm) o;
        if (masterForm.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masterForm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MasterForm{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", note='" + getNote() + "'" +
            ", receiveTime='" + getReceiveTime() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", imageFileContentType='" + getImageFileContentType() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
