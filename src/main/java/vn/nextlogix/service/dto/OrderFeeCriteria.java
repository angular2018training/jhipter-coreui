package vn.nextlogix.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the OrderFee entity. This class is used in OrderFeeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-fees?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderFeeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter deliveryFee;

    private DoubleFilter pickupFee;

    private DoubleFilter codFee;

    private DoubleFilter insuranceFee;

    private DoubleFilter otherFee;

    private DoubleFilter discount;

    private DoubleFilter totalFee;

    private LongFilter companyId;

    private LongFilter quotationId;

    public OrderFeeCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(DoubleFilter deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public DoubleFilter getPickupFee() {
        return pickupFee;
    }

    public void setPickupFee(DoubleFilter pickupFee) {
        this.pickupFee = pickupFee;
    }

    public DoubleFilter getCodFee() {
        return codFee;
    }

    public void setCodFee(DoubleFilter codFee) {
        this.codFee = codFee;
    }

    public DoubleFilter getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(DoubleFilter insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public DoubleFilter getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(DoubleFilter otherFee) {
        this.otherFee = otherFee;
    }

    public DoubleFilter getDiscount() {
        return discount;
    }

    public void setDiscount(DoubleFilter discount) {
        this.discount = discount;
    }

    public DoubleFilter getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(DoubleFilter totalFee) {
        this.totalFee = totalFee;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(LongFilter quotationId) {
        this.quotationId = quotationId;
    }

    @Override
    public String toString() {
        return "OrderFeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (deliveryFee != null ? "deliveryFee=" + deliveryFee + ", " : "") +
                (pickupFee != null ? "pickupFee=" + pickupFee + ", " : "") +
                (codFee != null ? "codFee=" + codFee + ", " : "") +
                (insuranceFee != null ? "insuranceFee=" + insuranceFee + ", " : "") +
                (otherFee != null ? "otherFee=" + otherFee + ", " : "") +
                (discount != null ? "discount=" + discount + ", " : "") +
                (totalFee != null ? "totalFee=" + totalFee + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (quotationId != null ? "quotationId=" + quotationId + ", " : "") +
            "}";
    }

}
