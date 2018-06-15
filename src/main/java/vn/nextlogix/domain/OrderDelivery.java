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
 * A OrderDelivery.
 */
@Entity
@Table(name = "order_delivery")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderdelivery")
public class OrderDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "note")
    private String note;

    @Column(name = "receive_time")
    private Instant receiveTime;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @ManyToOne
    private UserExtraInfo createUser;

    @ManyToOne
    private OrderStatus orderStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public OrderDelivery receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getNote() {
        return note;
    }

    public OrderDelivery note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getReceiveTime() {
        return receiveTime;
    }

    public OrderDelivery receiveTime(Instant receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public void setReceiveTime(Instant receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public OrderDelivery createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public UserExtraInfo getCreateUser() {
        return createUser;
    }

    public OrderDelivery createUser(UserExtraInfo userExtraInfo) {
        this.createUser = userExtraInfo;
        return this;
    }

    public void setCreateUser(UserExtraInfo userExtraInfo) {
        this.createUser = userExtraInfo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderDelivery orderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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
        OrderDelivery orderDelivery = (OrderDelivery) o;
        if (orderDelivery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDelivery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDelivery{" +
            "id=" + getId() +
            ", receiver='" + getReceiver() + "'" +
            ", note='" + getNote() + "'" +
            ", receiveTime='" + getReceiveTime() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
