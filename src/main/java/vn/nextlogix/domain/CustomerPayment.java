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
 * A CustomerPayment.
 */
@Entity
@Table(name = "customer_payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customerpayment")
public class CustomerPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "account_number")
    private String accountNumber;

    @NotNull
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "payment_amount_money")
    private Double paymentAmountMoney;

    @Lob
    @Column(name = "id_image")
    private byte[] idImage;

    @Column(name = "id_image_content_type")
    private String idImageContentType;

    @NotNull
    @Column(name = "is_verify", nullable = false)
    private Boolean isVerify;

    @Column(name = "date_verify")
    private Instant dateVerify;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private Bank bank;

    @ManyToOne
    private UserExtraInfo userVerify;

    @ManyToOne
    private PaymentType paymentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public CustomerPayment branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public CustomerPayment accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public CustomerPayment accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CustomerPayment cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getPaymentAmountMoney() {
        return paymentAmountMoney;
    }

    public CustomerPayment paymentAmountMoney(Double paymentAmountMoney) {
        this.paymentAmountMoney = paymentAmountMoney;
        return this;
    }

    public void setPaymentAmountMoney(Double paymentAmountMoney) {
        this.paymentAmountMoney = paymentAmountMoney;
    }

    public byte[] getIdImage() {
        return idImage;
    }

    public CustomerPayment idImage(byte[] idImage) {
        this.idImage = idImage;
        return this;
    }

    public void setIdImage(byte[] idImage) {
        this.idImage = idImage;
    }

    public String getIdImageContentType() {
        return idImageContentType;
    }

    public CustomerPayment idImageContentType(String idImageContentType) {
        this.idImageContentType = idImageContentType;
        return this;
    }

    public void setIdImageContentType(String idImageContentType) {
        this.idImageContentType = idImageContentType;
    }

    public Boolean isIsVerify() {
        return isVerify;
    }

    public CustomerPayment isVerify(Boolean isVerify) {
        this.isVerify = isVerify;
        return this;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    public Instant getDateVerify() {
        return dateVerify;
    }

    public CustomerPayment dateVerify(Instant dateVerify) {
        this.dateVerify = dateVerify;
        return this;
    }

    public void setDateVerify(Instant dateVerify) {
        this.dateVerify = dateVerify;
    }

    public Company getCompany() {
        return company;
    }

    public CustomerPayment company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Bank getBank() {
        return bank;
    }

    public CustomerPayment bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public UserExtraInfo getUserVerify() {
        return userVerify;
    }

    public CustomerPayment userVerify(UserExtraInfo userExtraInfo) {
        this.userVerify = userExtraInfo;
        return this;
    }

    public void setUserVerify(UserExtraInfo userExtraInfo) {
        this.userVerify = userExtraInfo;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public CustomerPayment paymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
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
        CustomerPayment customerPayment = (CustomerPayment) o;
        if (customerPayment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerPayment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerPayment{" +
            "id=" + getId() +
            ", branchName='" + getBranchName() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", paymentAmountMoney=" + getPaymentAmountMoney() +
            ", idImage='" + getIdImage() + "'" +
            ", idImageContentType='" + getIdImageContentType() + "'" +
            ", isVerify='" + isIsVerify() + "'" +
            ", dateVerify='" + getDateVerify() + "'" +
            "}";
    }
}
