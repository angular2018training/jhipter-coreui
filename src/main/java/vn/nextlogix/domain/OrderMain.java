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
 * A OrderMain.
 */
@Entity
@Table(name = "order_main")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "ordermain")
public class OrderMain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "send_date")
    private Instant sendDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "quantity_item")
    private Integer quantityItem;

    @Column(name = "order_price")
    private Double orderPrice;

    @NotNull
    @Column(name = "final_weight", nullable = false)
    private Double finalWeight;

    @Column(name = "customer_order_number")
    private String customerOrderNumber;

    @Column(name = "cod_amount")
    private Double codAmount;

    @OneToOne
    @JoinColumn(unique = true)
    private OrderPickup orderPickup;

    @OneToOne
    @JoinColumn(unique = true)
    private OrderConsignee orderConsignee;

    @OneToOne
    @JoinColumn(unique = true)
    private OrderFee orderFee;

    @OneToOne
    @JoinColumn(unique = true)
    private OrderDelivery orderDelivery;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne
    private UserExtraInfo createUser;

    @ManyToOne(optional = false)
    @NotNull
    private OrderStatus orderStatus;

    @ManyToOne(optional = false)
    @NotNull
    private Customer customer;

    @ManyToOne(optional = false)
    @NotNull
    private OrderService mainService;

    @ManyToOne(optional = false)
    @NotNull
    private PostOffice createPostOffice;

    @ManyToOne(optional = false)
    @NotNull
    private PostOffice currentPostOffice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderMain orderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public OrderMain createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public OrderMain sendDate(Instant sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }

    public Double getWeight() {
        return weight;
    }

    public OrderMain weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public OrderMain width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public OrderMain height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDepth() {
        return depth;
    }

    public OrderMain depth(Integer depth) {
        this.depth = depth;
        return this;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getQuantityItem() {
        return quantityItem;
    }

    public OrderMain quantityItem(Integer quantityItem) {
        this.quantityItem = quantityItem;
        return this;
    }

    public void setQuantityItem(Integer quantityItem) {
        this.quantityItem = quantityItem;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public OrderMain orderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getFinalWeight() {
        return finalWeight;
    }

    public OrderMain finalWeight(Double finalWeight) {
        this.finalWeight = finalWeight;
        return this;
    }

    public void setFinalWeight(Double finalWeight) {
        this.finalWeight = finalWeight;
    }

    public String getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public OrderMain customerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
        return this;
    }

    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    public Double getCodAmount() {
        return codAmount;
    }

    public OrderMain codAmount(Double codAmount) {
        this.codAmount = codAmount;
        return this;
    }

    public void setCodAmount(Double codAmount) {
        this.codAmount = codAmount;
    }

    public OrderPickup getOrderPickup() {
        return orderPickup;
    }

    public OrderMain orderPickup(OrderPickup orderPickup) {
        this.orderPickup = orderPickup;
        return this;
    }

    public void setOrderPickup(OrderPickup orderPickup) {
        this.orderPickup = orderPickup;
    }

    public OrderConsignee getOrderConsignee() {
        return orderConsignee;
    }

    public OrderMain orderConsignee(OrderConsignee orderConsignee) {
        this.orderConsignee = orderConsignee;
        return this;
    }

    public void setOrderConsignee(OrderConsignee orderConsignee) {
        this.orderConsignee = orderConsignee;
    }

    public OrderFee getOrderFee() {
        return orderFee;
    }

    public OrderMain orderFee(OrderFee orderFee) {
        this.orderFee = orderFee;
        return this;
    }

    public void setOrderFee(OrderFee orderFee) {
        this.orderFee = orderFee;
    }

    public OrderDelivery getOrderDelivery() {
        return orderDelivery;
    }

    public OrderMain orderDelivery(OrderDelivery orderDelivery) {
        this.orderDelivery = orderDelivery;
        return this;
    }

    public void setOrderDelivery(OrderDelivery orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    public Company getCompany() {
        return company;
    }

    public OrderMain company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserExtraInfo getCreateUser() {
        return createUser;
    }

    public OrderMain createUser(UserExtraInfo userExtraInfo) {
        this.createUser = userExtraInfo;
        return this;
    }

    public void setCreateUser(UserExtraInfo userExtraInfo) {
        this.createUser = userExtraInfo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderMain orderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderMain customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderService getMainService() {
        return mainService;
    }

    public OrderMain mainService(OrderService orderService) {
        this.mainService = orderService;
        return this;
    }

    public void setMainService(OrderService orderService) {
        this.mainService = orderService;
    }

    public PostOffice getCreatePostOffice() {
        return createPostOffice;
    }

    public OrderMain createPostOffice(PostOffice postOffice) {
        this.createPostOffice = postOffice;
        return this;
    }

    public void setCreatePostOffice(PostOffice postOffice) {
        this.createPostOffice = postOffice;
    }

    public PostOffice getCurrentPostOffice() {
        return currentPostOffice;
    }

    public OrderMain currentPostOffice(PostOffice postOffice) {
        this.currentPostOffice = postOffice;
        return this;
    }

    public void setCurrentPostOffice(PostOffice postOffice) {
        this.currentPostOffice = postOffice;
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
        OrderMain orderMain = (OrderMain) o;
        if (orderMain.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderMain.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderMain{" +
            "id=" + getId() +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", sendDate='" + getSendDate() + "'" +
            ", weight=" + getWeight() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", depth=" + getDepth() +
            ", quantityItem=" + getQuantityItem() +
            ", orderPrice=" + getOrderPrice() +
            ", finalWeight=" + getFinalWeight() +
            ", customerOrderNumber='" + getCustomerOrderNumber() + "'" +
            ", codAmount=" + getCodAmount() +
            "}";
    }
}
