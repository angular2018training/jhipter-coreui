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

    @NotNull
    @Column(name = "contract_address", nullable = false)
    private String contractAddress;

    @Column(name = "contract_contact_name")
    private String contractContactName;

    @Column(name = "contract_contact_phone")
    private String contractContactPhone;

    @Column(name = "tax_code")
    private String taxCode;

    @NotNull
    @Column(name = "contract_expiration_date", nullable = false)
    private String contractExpirationDate;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private Province province;

    @ManyToOne(optional = false)
    @NotNull
    private District district;

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

    public String getContractAddress() {
        return contractAddress;
    }

    public CustomerLegal contractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
        return this;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
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

    public Company getCompany() {
        return company;
    }

    public CustomerLegal company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Province getProvince() {
        return province;
    }

    public CustomerLegal province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public CustomerLegal district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
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
            ", contractAddress='" + getContractAddress() + "'" +
            ", contractContactName='" + getContractContactName() + "'" +
            ", contractContactPhone='" + getContractContactPhone() + "'" +
            ", taxCode='" + getTaxCode() + "'" +
            ", contractExpirationDate='" + getContractExpirationDate() + "'" +
            "}";
    }
}
