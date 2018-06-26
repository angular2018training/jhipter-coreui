package vn.nextlogix.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderMain entity.
 */
public class OrderMainDTO implements Serializable {

    private Long id;

    @NotNull
    private String orderNumber;

    @NotNull
    private Instant createDate;

    private Instant sendDate;

    private Double weight;

    private Integer width;

    private Integer height;

    private Integer depth;

    private Integer quantityItem;

    private Double orderPrice;

    @NotNull
    private Double finalWeight;

    private String customerOrderNumber;

    private Double codAmount;

    private Long orderPickupId;

    private OrderPickupDTO  orderPickupDTO;


    private Long orderConsigneeId;

    private OrderConsigneeDTO  orderConsigneeDTO;


    private Long orderFeeId;

    private OrderFeeDTO  orderFeeDTO;


    private Long orderDeliveryId;

    private OrderDeliveryDTO  orderDeliveryDTO;


    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long createUserId;

    private UserExtraInfoDTO  createUserDTO;


    private Long orderStatusId;

    private OrderStatusDTO  orderStatusDTO;


    private Long customerId;

    private CustomerDTO  customerDTO;


    private Long mainServiceId;

    private OrderServiceDTO  mainServiceDTO;


    private Long createPostOfficeId;

    private PostOfficeDTO  createPostOfficeDTO;


    private Long currentPostOfficeId;

    private PostOfficeDTO  currentPostOfficeDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(Integer quantityItem) {
        this.quantityItem = quantityItem;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(Double finalWeight) {
        this.finalWeight = finalWeight;
    }

    public String getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    public Double getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(Double codAmount) {
        this.codAmount = codAmount;
    }

    public OrderPickupDTO getOrderPickupDTO() {
        return this.orderPickupDTO;
    }

    public void setOrderPickupDTO(OrderPickupDTO orderPickupDTO ) {
        this.orderPickupDTO = orderPickupDTO;
    }
    public Long getOrderPickupId() {
        return orderPickupId;
        }

    public void setOrderPickupId(Long orderPickupId) {
        this.orderPickupId = orderPickupId;
        }


    public OrderConsigneeDTO getOrderConsigneeDTO() {
        return this.orderConsigneeDTO;
    }

    public void setOrderConsigneeDTO(OrderConsigneeDTO orderConsigneeDTO ) {
        this.orderConsigneeDTO = orderConsigneeDTO;
    }
    public Long getOrderConsigneeId() {
        return orderConsigneeId;
        }

    public void setOrderConsigneeId(Long orderConsigneeId) {
        this.orderConsigneeId = orderConsigneeId;
        }


    public OrderFeeDTO getOrderFeeDTO() {
        return this.orderFeeDTO;
    }

    public void setOrderFeeDTO(OrderFeeDTO orderFeeDTO ) {
        this.orderFeeDTO = orderFeeDTO;
    }
    public Long getOrderFeeId() {
        return orderFeeId;
        }

    public void setOrderFeeId(Long orderFeeId) {
        this.orderFeeId = orderFeeId;
        }


    public OrderDeliveryDTO getOrderDeliveryDTO() {
        return this.orderDeliveryDTO;
    }

    public void setOrderDeliveryDTO(OrderDeliveryDTO orderDeliveryDTO ) {
        this.orderDeliveryDTO = orderDeliveryDTO;
    }
    public Long getOrderDeliveryId() {
        return orderDeliveryId;
        }

    public void setOrderDeliveryId(Long orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
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


    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO ) {
        this.customerDTO = customerDTO;
    }
    public Long getCustomerId() {
        return customerId;
        }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
        }


    public OrderServiceDTO getMainServiceDTO() {
        return this.mainServiceDTO;
    }

    public void setMainServiceDTO(OrderServiceDTO mainServiceDTO ) {
        this.mainServiceDTO = mainServiceDTO;
    }
    public Long getMainServiceId() {
        return mainServiceId;
        }

    public void setMainServiceId(Long orderServiceId) {
        this.mainServiceId = orderServiceId;
        }


    public PostOfficeDTO getCreatePostOfficeDTO() {
        return this.createPostOfficeDTO;
    }

    public void setCreatePostOfficeDTO(PostOfficeDTO createPostOfficeDTO ) {
        this.createPostOfficeDTO = createPostOfficeDTO;
    }
    public Long getCreatePostOfficeId() {
        return createPostOfficeId;
        }

    public void setCreatePostOfficeId(Long postOfficeId) {
        this.createPostOfficeId = postOfficeId;
        }


    public PostOfficeDTO getCurrentPostOfficeDTO() {
        return this.currentPostOfficeDTO;
    }

    public void setCurrentPostOfficeDTO(PostOfficeDTO currentPostOfficeDTO ) {
        this.currentPostOfficeDTO = currentPostOfficeDTO;
    }
    public Long getCurrentPostOfficeId() {
        return currentPostOfficeId;
        }

    public void setCurrentPostOfficeId(Long postOfficeId) {
        this.currentPostOfficeId = postOfficeId;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderMainDTO orderMainDTO = (OrderMainDTO) o;
        if(orderMainDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderMainDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderMainDTO{" +
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
