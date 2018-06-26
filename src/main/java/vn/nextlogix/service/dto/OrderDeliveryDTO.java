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
public class OrderDeliveryDTO implements Serializable {

    private Long id;

    private String receiver;

    private String note;

    private Instant receiveTime;

    @NotNull
    private Instant createDate;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long createUserId;

    private UserExtraInfoDTO  createUserDTO;


    private Long orderStatusId;

    private OrderStatusDTO  orderStatusDTO;


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


    public UserExtraInfoDTO getCreateUserDTO() {
        return this.createUserDTO;
    }

    public void setCreateUserDTO(UserExtraInfoDTO createUserDTO ) {
        this.createUserDTO = createUserDTO;
    }
    public Long getCreateUserId() {
        return createUserId;
        }

    public void setCreateUserId(Long userExtraInfoId) {
        this.createUserId = userExtraInfoId;
        }


    public OrderStatusDTO getOrderStatusDTO() {
        return this.orderStatusDTO;
    }

    public void setOrderStatusDTO(OrderStatusDTO orderStatusDTO ) {
        this.orderStatusDTO = orderStatusDTO;
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

        OrderDeliveryDTO orderDeliveryDTO = (OrderDeliveryDTO) o;
        if(orderDeliveryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDeliveryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDeliveryDTO{" +
            "id=" + getId() +
            ", receiver='" + getReceiver() + "'" +
            ", note='" + getNote() + "'" +
            ", receiveTime='" + getReceiveTime() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
