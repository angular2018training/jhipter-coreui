package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

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

    private Double paymentAmountMoney;

    @Lob
    private byte[] idImage;
    private String idImageContentType;

    @NotNull
    private Boolean isVerify;

    private Instant dateVerify;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long bankId;

    private BankDTO  bankDTO;


    private Long userVerifyId;

    private UserExtraInfoDTO  userVerifyDTO;


    private Long paymentTypeId;

    private PaymentTypeDTO  paymentTypeDTO;


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

    public Double getPaymentAmountMoney() {
        return paymentAmountMoney;
    }

    public void setPaymentAmountMoney(Double paymentAmountMoney) {
        this.paymentAmountMoney = paymentAmountMoney;
    }

    public byte[] getIdImage() {
        return idImage;
    }

    public void setIdImage(byte[] idImage) {
        this.idImage = idImage;
    }

    public String getIdImageContentType() {
        return idImageContentType;
    }

    public void setIdImageContentType(String idImageContentType) {
        this.idImageContentType = idImageContentType;
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


    public BankDTO getBankDTO() {
        return this.bankDTO;
    }

    public void setBankDTO(BankDTO bankDTO ) {
        this.bankDTO = bankDTO;
    }
    public Long getBankId() {
        return bankId;
        }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
        }


    public UserExtraInfoDTO getUserVerifyDTO() {
        return this.userVerifyDTO;
    }

    public void setUserVerifyDTO(UserExtraInfoDTO userVerifyDTO ) {
        this.userVerifyDTO = userVerifyDTO;
    }
    public Long getUserVerifyId() {
        return userVerifyId;
        }

    public void setUserVerifyId(Long userExtraInfoId) {
        this.userVerifyId = userExtraInfoId;
        }


    public PaymentTypeDTO getPaymentTypeDTO() {
        return this.paymentTypeDTO;
    }

    public void setPaymentTypeDTO(PaymentTypeDTO paymentTypeDTO ) {
        this.paymentTypeDTO = paymentTypeDTO;
    }
    public Long getPaymentTypeId() {
        return paymentTypeId;
        }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
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
            ", paymentAmountMoney=" + getPaymentAmountMoney() +
            ", idImage='" + getIdImage() + "'" +
            ", isVerify='" + isIsVerify() + "'" +
            ", dateVerify='" + getDateVerify() + "'" +
            "}";
    }
}
