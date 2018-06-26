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
 * Criteria class for the OrderDelivery entity. This class is used in OrderDeliveryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-deliveries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderDeliveryCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter receiver;

    private StringFilter note;

    private InstantFilter receiveTime;

    private InstantFilter createDate;

    private LongFilter companyId;

    private LongFilter createUserId;

    private LongFilter orderStatusId;

    public OrderDeliveryCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getReceiver() {
        return receiver;
    }

    public void setReceiver(StringFilter receiver) {
        this.receiver = receiver;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public InstantFilter getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(InstantFilter receiveTime) {
        this.receiveTime = receiveTime;
    }

    public InstantFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(InstantFilter createDate) {
        this.createDate = createDate;
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

    @Override
    public String toString() {
        return "OrderDeliveryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (receiver != null ? "receiver=" + receiver + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
                (receiveTime != null ? "receiveTime=" + receiveTime + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (createUserId != null ? "createUserId=" + createUserId + ", " : "") +
                (orderStatusId != null ? "orderStatusId=" + orderStatusId + ", " : "") +
            "}";
    }

}
