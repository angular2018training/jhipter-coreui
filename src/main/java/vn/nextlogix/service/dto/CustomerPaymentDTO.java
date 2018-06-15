package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import vn.nextlogix.domain.enumeration.PaymentType;

/**
 * A DTO for the CustomerPayment entity.
 */
public class CustomerPaymentDTO implements Serializable {

    private Long id;

    private String branchName;

    private String accountNumber;

    @NotNull
    private String accountName;

    private String cardNumber;

    @NotNull
    private Boolean isVerify;

    private Instant dateVerify;

    @NotNull
    private PaymentType paymentType;

    private Long bankId;

    private Long userVerifyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Boolean isIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    public Instant getDateVerify() {
        return dateVerify;
    }

    public void setDateVerify(Instant dateVerify) {
        this.dateVerify = dateVerify;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getUserVerifyId() {
        return userVerifyId;
    }

    public void setUserVerifyId(Long userExtraInfoId) {
        this.userVerifyId = userExtraInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerPaymentDTO customerPaymentDTO = (CustomerPaymentDTO) o;
        if(customerPaymentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerPaymentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerPaymentDTO{" +
            "id=" + getId() +
            ", branchName='" + getBranchName() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", isVerify='" + isIsVerify() + "'" +
            ", dateVerify='" + getDateVerify() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            "}";
    }
}
