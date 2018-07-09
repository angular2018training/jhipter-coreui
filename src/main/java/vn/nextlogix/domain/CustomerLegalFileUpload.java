package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CustomerLegalFileUpload.
 */
@Entity
@Table(name = "customer_legal_file_upload")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customerlegalfileupload")
public class CustomerLegalFileUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name="file_upload_id")
    private FileUpload fileUpload;

    @ManyToOne
    private CustomerLegal customerLegalParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public CustomerLegalFileUpload company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public CustomerLegalFileUpload fileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
        return this;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    public CustomerLegal getCustomerLegalParent() {
        return customerLegalParent;
    }

    public CustomerLegalFileUpload customerLegalParent(CustomerLegal customerLegal) {
        this.customerLegalParent = customerLegal;
        return this;
    }

    public void setCustomerLegalParent(CustomerLegal customerLegal) {
        this.customerLegalParent = customerLegal;
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
        CustomerLegalFileUpload customerLegalFileUpload = (CustomerLegalFileUpload) o;
        if (customerLegalFileUpload.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerLegalFileUpload.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerLegalFileUpload{" +
            "id=" + getId() +
            "}";
    }
}
