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
 * Criteria class for the OrderMain entity. This class is used in OrderMainResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-mains?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderMainCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter orderNumber;

    private InstantFilter createDate;

    private InstantFilter sendDate;

    private DoubleFilter weight;

    private IntegerFilter width;

    private IntegerFilter height;

    private IntegerFilter depth;

    private IntegerFilter quantityItem;

    private DoubleFilter orderPrice;

    private DoubleFilter finalWeight;

    private StringFilter customerOrderNumber;

    private DoubleFilter codAmount;

    private LongFilter orderPickupId;

    private LongFilter orderConsigneeId;

    private LongFilter orderFeeId;

    private LongFilter orderDeliveryId;

    private LongFilter companyId;

    private LongFilter createUserId;

    private LongFilter orderStatusId;

    private LongFilter customerId;

    private LongFilter mainServiceId;

    private LongFilter createPostOfficeId;

    private LongFilter currentPostOfficeId;

    public OrderMainCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(StringFilter orderNumber) {
        this.orderNumber = orderNumber;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
    }

    public InstantFilter getSendDate() {
        return sendDate;
    }

    public void setSendDate(InstantFilter sendDate) {
        this.sendDate = sendDate;
    }

    public DoubleFilter getWeight() {
        return weight;
    }

    public void setWeight(DoubleFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getWidth() {
        return width;
    }

    public void setWidth(IntegerFilter width) {
        this.width = width;
    }

    public IntegerFilter getHeight() {
        return height;
    }

    public void setHeight(IntegerFilter height) {
        this.height = height;
    }

    public IntegerFilter getDepth() {
        return depth;
    }

    public void setDepth(IntegerFilter depth) {
        this.depth = depth;
    }

    public IntegerFilter getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(IntegerFilter quantityItem) {
        this.quantityItem = quantityItem;
    }

    public DoubleFilter getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(DoubleFilter orderPrice) {
        this.orderPrice = orderPrice;
    }

    public DoubleFilter getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(DoubleFilter finalWeight) {
        this.finalWeight = finalWeight;
    }

    public StringFilter getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public void setCustomerOrderNumber(StringFilter customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    public DoubleFilter getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(DoubleFilter codAmount) {
        this.codAmount = codAmount;
    }

    public LongFilter getOrderPickupId() {
        return orderPickupId;
    }

    public void setOrderPickupId(LongFilter orderPickupId) {
        this.orderPickupId = orderPickupId;
    }

    public LongFilter getOrderConsigneeId() {
        return orderConsigneeId;
    }

    public void setOrderConsigneeId(LongFilter orderConsigneeId) {
        this.orderConsigneeId = orderConsigneeId;
    }

    public LongFilter getOrderFeeId() {
        return orderFeeId;
    }

    public void setOrderFeeId(LongFilter orderFeeId) {
        this.orderFeeId = orderFeeId;
    }

    public LongFilter getOrderDeliveryId() {
        return orderDeliveryId;
    }

    public void setOrderDeliveryId(LongFilter orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(LongFilter createUserId) {
        this.createUserId = createUserId;
    }

    public LongFilter getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(LongFilter orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public LongFilter getMainServiceId() {
        return mainServiceId;
    }

    public void setMainServiceId(LongFilter mainServiceId) {
        this.mainServiceId = mainServiceId;
    }

    public LongFilter getCreatePostOfficeId() {
        return createPostOfficeId;
    }

    public void setCreatePostOfficeId(LongFilter createPostOfficeId) {
        this.createPostOfficeId = createPostOfficeId;
    }

    public LongFilter getCurrentPostOfficeId() {
        return currentPostOfficeId;
    }

    public void setCurrentPostOfficeId(LongFilter currentPostOfficeId) {
        this.currentPostOfficeId = currentPostOfficeId;
    }

    @Override
    public String toString() {
        return "OrderMainCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (orderNumber != null ? "orderNumber=" + orderNumber + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (sendDate != null ? "sendDate=" + sendDate + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (width != null ? "width=" + width + ", " : "") +
                (height != null ? "height=" + height + ", " : "") +
                (depth != null ? "depth=" + depth + ", " : "") +
                (quantityItem != null ? "quantityItem=" + quantityItem + ", " : "") +
                (orderPrice != null ? "orderPrice=" + orderPrice + ", " : "") +
                (finalWeight != null ? "finalWeight=" + finalWeight + ", " : "") +
                (customerOrderNumber != null ? "customerOrderNumber=" + customerOrderNumber + ", " : "") +
                (codAmount != null ? "codAmount=" + codAmount + ", " : "") +
                (orderPickupId != null ? "orderPickupId=" + orderPickupId + ", " : "") +
                (orderConsigneeId != null ? "orderConsigneeId=" + orderConsigneeId + ", " : "") +
                (orderFeeId != null ? "orderFeeId=" + orderFeeId + ", " : "") +
                (orderDeliveryId != null ? "orderDeliveryId=" + orderDeliveryId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (createUserId != null ? "createUserId=" + createUserId + ", " : "") +
                (orderStatusId != null ? "orderStatusId=" + orderStatusId + ", " : "") +
                (customerId != null ? "customerId=" + customerId + ", " : "") +
                (mainServiceId != null ? "mainServiceId=" + mainServiceId + ", " : "") +
                (createPostOfficeId != null ? "createPostOfficeId=" + createPostOfficeId + ", " : "") +
                (currentPostOfficeId != null ? "currentPostOfficeId=" + currentPostOfficeId + ", " : "") +
            "}";
    }

}
