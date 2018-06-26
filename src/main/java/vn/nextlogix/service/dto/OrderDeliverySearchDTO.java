    package vn.nextlogix.service.dto;


    import java.time.Instant;
    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;

/**
 * A DTO for the OrderDelivery entity.
 */
public class OrderDeliverySearchDTO implements Serializable {

private Long id;


private String receiver;


private String note;


private Instant receiveTime;


private Instant createDate;

private Long companyId;



private Long createUserId;



private Long orderStatusId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public String getReceiver() {
    return receiver;
    }

public void setReceiver(String receiver) {
    this.receiver = receiver;
    }

public String getNote() {
    return note;
    }

public void setNote(String note) {
    this.note = note;
    }

public Instant getReceiveTime() {
    return receiveTime;
    }

public void setReceiveTime(Instant receiveTime) {
    this.receiveTime = receiveTime;
    }

public Instant getCreateDate() {
    return createDate;
    }

public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }



public Long getCreateUserId() {
    return createUserId;
    }

public void setCreateUserId(Long userExtraInfoId) {
    this.createUserId = userExtraInfoId;
    }



public Long getOrderStatusId() {
    return orderStatusId;
    }

public void setOrderStatusId(Long orderStatusId) {
    this.orderStatusId = orderStatusId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

OrderDeliverySearchDTO orderDeliverySearchDTO = (OrderDeliverySearchDTO) o;
    if(orderDeliverySearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), orderDeliverySearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "OrderDeliverySearchDTO{" +
    "id=" + getId() +
    ", receiver='" + getReceiver() + "'" +
    ", note='" + getNote() + "'" +
    ", receiveTime='" + getReceiveTime() + "'" +
    ", createDate='" + getCreateDate() + "'" +
    "}";
    }
    }
