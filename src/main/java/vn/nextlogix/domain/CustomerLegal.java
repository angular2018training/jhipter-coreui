package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CustomerLegal.
 */
@Entity
@Table(name = "customer_legal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customerlegal")
public class CustomerLegal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "contract_customer_name", nullable = false)
    private String contractCustomerName;

    @Column(name = "contract_contact_name")
    private String contractContactName;

    @Column(name = "contract_contact_phone")
    private String contractContactPhone;

    @Column(name = "tax_code")
    private String taxCode;

    @NotNull
    @Column(name = "contract_expiration_date", nullable = false)
    private String contractExpirationDate;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "customer_legal_file_upload",
               joinColumns = @JoinColumn(name="customer_legals_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="file_uploads_id", referencedColumnName="id"))
    private Set<FileUpload> fileUploads = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractCustomerName() {
        return contractCustomerName;
    }

    public CustomerLegal contractCustomerName(String contractCustomerName) {
        this.contractCustomerName = contractCustomerName;
        return this;
    }

    public void setContractCustomerName(String contractCustomerName) {
        this.contractCustomerName = contractCustomerName;
    }

    public String getContractContactName() {
        return contractContactName;
    }

    public CustomerLegal contractContactName(String contractContactName) {
        this.contractContactName = contractContactName;
        return this;
    }

    public void setContractContactName(String contractContactName) {
        this.contractContactName = contractContactName;
    }

    public String getContractContactPhone() {
        return contractContactPhone;
    }

    public CustomerLegal contractContactPhone(String contractContactPhone) {
        this.contractContactPhone = contractContactPhone;
        return this;
    }

    public void setContractContactPhone(String contractContactPhone) {
        this.contractContactPhone = contractContactPhone;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public CustomerLegal taxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getContractExpirationDate() {
        return contractExpirationDate;
    }

    public CustomerLegal contractExpirationDate(String contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
        return this;
    }

    public void setContractExpirationDate(String contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }

    public Set<FileUpload> getFileUploads() {
        return fileUploads;
    }

    public CustomerLegal fileUploads(Set<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
        return this;
    }

    public CustomerLegal addFileUpload(FileUpload fileUpload) {
        this.fileUploads.add(fileUpload);
        return this;
    }

    public CustomerLegal removeFileUpload(FileUpload fileUpload) {
        this.fileUploads.remove(fileUpload);
        return this;
    }

    public void setFileUploads(Set<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
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
        CustomerLegal customerLegal = (CustomerLegal) o;
        if (customerLegal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerLegal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerLegal{" +
            "id=" + getId() +
            ", contractCustomerName='" + getContractCustomerName() + "'" +
            ", contractContactName='" + getContractContactName() + "'" +
            ", contractContactPhone='" + getContractContactPhone() + "'" +
            ", taxCode='" + getTaxCode() + "'" +
            ", contractExpirationDate='" + getContractExpirationDate() + "'" +
            "}";
    }
}
