package vn.nextlogix.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the CustomerPayment entity. This class is used in CustomerPaymentResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /customer-payments?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CustomerPaymentCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter branchName;

    private StringFilter accountNumber;

    private StringFilter accountName;

    private StringFilter cardNumber;

    private DoubleFilter paymentAmountMoney;

    private BooleanFilter isVerify;

    private InstantFilter dateVerify;

    private LongFilter companyId;

    private LongFilter bankId;

    private LongFilter userVerifyId;

    private LongFilter paymentTypeId;

    public CustomerPaymentCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getBranchName() {
        return branchName;
    }

    public void setBranchName(StringFilter branchName) {
        this.branchName = branchName;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public StringFilter getAccountName() {
        return accountName;
    }

    public void setAccountName(StringFilter accountName) {
        this.accountName = accountName;
    }

    public StringFilter getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(StringFilter cardNumber) {
        this.cardNumber = cardNumber;
    }

    public DoubleFilter getPaymentAmountMoney() {
        return paymentAmountMoney;
    }

    public void setPaymentAmountMoney(DoubleFilter paymentAmountMoney) {
        this.paymentAmountMoney = paymentAmountMoney;
    }

    public BooleanFilter getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(BooleanFilter isVerify) {
        this.isVerify = isVerify;
    }

    public InstantFilter getDateVerify() {
        return dateVerify;
    }

    public void setDateVerify(InstantFilter dateVerify) {
        this.dateVerify = dateVerify;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getBankId() {
        return bankId;
    }

    public void setBankId(LongFilter bankId) {
        this.bankId = bankId;
    }

    public LongFilter getUserVerifyId() {
        return userVerifyId;
    }

    public void setUserVerifyId(LongFilter userVerifyId) {
        this.userVerifyId = userVerifyId;
    }

    public LongFilter getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(LongFilter paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Override
    public String toString() {
        return "CustomerPaymentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (branchName != null ? "branchName=" + branchName + ", " : "") +
                (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
                (accountName != null ? "accountName=" + accountName + ", " : "") +
                (cardNumber != null ? "cardNumber=" + cardNumber + ", " : "") +
                (paymentAmountMoney != null ? "paymentAmountMoney=" + paymentAmountMoney + ", " : "") +
                (isVerify != null ? "isVerify=" + isVerify + ", " : "") +
                (dateVerify != null ? "dateVerify=" + dateVerify + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (bankId != null ? "bankId=" + bankId + ", " : "") +
                (userVerifyId != null ? "userVerifyId=" + userVerifyId + ", " : "") +
                (paymentTypeId != null ? "paymentTypeId=" + paymentTypeId + ", " : "") +
            "}";
    }

}
