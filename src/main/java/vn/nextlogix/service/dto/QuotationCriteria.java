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
import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Quotation entity. This class is used in QuotationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /quotations?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class QuotationCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private BooleanFilter isActive;

    private StringFilter description;

    private InstantFilter createDate;

    private LocalDateFilter activeFrom;

    private LongFilter quotationPickupDetailListId;

    private LongFilter quotationDomesticDeliveryDetailListId;

    private LongFilter quotationReturnDetailListId;

    private LongFilter quotationGiveBackDetailListId;

    private LongFilter quotationInsuranceDetailListId;

    private LongFilter quotationCodDetailListId;

    private LongFilter quotationSubServicesDetailListId;

    private LongFilter companyId;

    public QuotationCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
    }

    public LocalDateFilter getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDateFilter activeFrom) {
        this.activeFrom = activeFrom;
    }

    public LongFilter getQuotationPickupDetailListId() {
        return quotationPickupDetailListId;
    }

    public void setQuotationPickupDetailListId(LongFilter quotationPickupDetailListId) {
        this.quotationPickupDetailListId = quotationPickupDetailListId;
    }

    public LongFilter getQuotationDomesticDeliveryDetailListId() {
        return quotationDomesticDeliveryDetailListId;
    }

    public void setQuotationDomesticDeliveryDetailListId(LongFilter quotationDomesticDeliveryDetailListId) {
        this.quotationDomesticDeliveryDetailListId = quotationDomesticDeliveryDetailListId;
    }

    public LongFilter getQuotationReturnDetailListId() {
        return quotationReturnDetailListId;
    }

    public void setQuotationReturnDetailListId(LongFilter quotationReturnDetailListId) {
        this.quotationReturnDetailListId = quotationReturnDetailListId;
    }

    public LongFilter getQuotationGiveBackDetailListId() {
        return quotationGiveBackDetailListId;
    }

    public void setQuotationGiveBackDetailListId(LongFilter quotationGiveBackDetailListId) {
        this.quotationGiveBackDetailListId = quotationGiveBackDetailListId;
    }

    public LongFilter getQuotationInsuranceDetailListId() {
        return quotationInsuranceDetailListId;
    }

    public void setQuotationInsuranceDetailListId(LongFilter quotationInsuranceDetailListId) {
        this.quotationInsuranceDetailListId = quotationInsuranceDetailListId;
    }

    public LongFilter getQuotationCodDetailListId() {
        return quotationCodDetailListId;
    }

    public void setQuotationCodDetailListId(LongFilter quotationCodDetailListId) {
        this.quotationCodDetailListId = quotationCodDetailListId;
    }

    public LongFilter getQuotationSubServicesDetailListId() {
        return quotationSubServicesDetailListId;
    }

    public void setQuotationSubServicesDetailListId(LongFilter quotationSubServicesDetailListId) {
        this.quotationSubServicesDetailListId = quotationSubServicesDetailListId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "QuotationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (activeFrom != null ? "activeFrom=" + activeFrom + ", " : "") +
                (quotationPickupDetailListId != null ? "quotationPickupDetailListId=" + quotationPickupDetailListId + ", " : "") +
                (quotationDomesticDeliveryDetailListId != null ? "quotationDomesticDeliveryDetailListId=" + quotationDomesticDeliveryDetailListId + ", " : "") +
                (quotationReturnDetailListId != null ? "quotationReturnDetailListId=" + quotationReturnDetailListId + ", " : "") +
                (quotationGiveBackDetailListId != null ? "quotationGiveBackDetailListId=" + quotationGiveBackDetailListId + ", " : "") +
                (quotationInsuranceDetailListId != null ? "quotationInsuranceDetailListId=" + quotationInsuranceDetailListId + ", " : "") +
                (quotationCodDetailListId != null ? "quotationCodDetailListId=" + quotationCodDetailListId + ", " : "") +
                (quotationSubServicesDetailListId != null ? "quotationSubServicesDetailListId=" + quotationSubServicesDetailListId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
            "}";
    }

}
