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
 * A QuotationItem.
 */
@Entity
@Table(name = "quotation_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quotationitem")
public class QuotationItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Lob
    @Column(name = "quotation_file", nullable = false)
    private byte[] quotationFile;

    @Column(name = "quotation_file_content_type", nullable = false)
    private String quotationFileContentType;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @ManyToOne
    private Quotation quotation;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getQuotationFile() {
        return quotationFile;
    }

    public QuotationItem quotationFile(byte[] quotationFile) {
        this.quotationFile = quotationFile;
        return this;
    }

    public void setQuotationFile(byte[] quotationFile) {
        this.quotationFile = quotationFile;
    }

    public String getQuotationFileContentType() {
        return quotationFileContentType;
    }

    public QuotationItem quotationFileContentType(String quotationFileContentType) {
        this.quotationFileContentType = quotationFileContentType;
        return this;
    }

    public void setQuotationFileContentType(String quotationFileContentType) {
        this.quotationFileContentType = quotationFileContentType;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public QuotationItem createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public QuotationItem quotation(Quotation quotation) {
        this.quotation = quotation;
        return this;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public Company getCompany() {
        return company;
    }

    public QuotationItem company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        QuotationItem quotationItem = (QuotationItem) o;
        if (quotationItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationItem{" +
            "id=" + getId() +
            ", quotationFile='" + getQuotationFile() + "'" +
            ", quotationFileContentType='" + getQuotationFileContentType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
