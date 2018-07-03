package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerServices entity.
 */
public class CustomerServicesDTO implements Serializable {

    private Long id;

    private Double discountPercent;

    private Double increasePercent;

    private Double decreasePercent;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long orderServicesId;

    private OrderServicesDTO  orderServicesDTO;


    private Long quotationId;

    private QuotationDTO  quotationDTO;


    private Long customerParentId;

    private CustomerDTO  customerParentDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getIncreasePercent() {
        return increasePercent;
    }

    public void setIncreasePercent(Double increasePercent) {
        this.increasePercent = increasePercent;
    }

    public Double getDecreasePercent() {
        return decreasePercent;
    }

    public void setDecreasePercent(Double decreasePercent) {
        this.decreasePercent = decreasePercent;
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


    public OrderServicesDTO getOrderServicesDTO() {
        return this.orderServicesDTO;
    }

    public void setOrderServicesDTO(OrderServicesDTO orderServicesDTO ) {
        this.orderServicesDTO = orderServicesDTO;
    }
    public Long getOrderServicesId() {
        return orderServicesId;
        }

    public void setOrderServicesId(Long orderServicesId) {
        this.orderServicesId = orderServicesId;
        }


    public QuotationDTO getQuotationDTO() {
        return this.quotationDTO;
    }

    public void setQuotationDTO(QuotationDTO quotationDTO ) {
        this.quotationDTO = quotationDTO;
    }
    public Long getQuotationId() {
        return quotationId;
        }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
        }


    public CustomerDTO getCustomerParentDTO() {
        return this.customerParentDTO;
    }

    public void setCustomerParentDTO(CustomerDTO customerParentDTO ) {
        this.customerParentDTO = customerParentDTO;
    }
    public Long getCustomerParentId() {
        return customerParentId;
        }

    public void setCustomerParentId(Long customerId) {
        this.customerParentId = customerId;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerServicesDTO customerServicesDTO = (CustomerServicesDTO) o;
        if(customerServicesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerServicesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerServicesDTO{" +
            "id=" + getId() +
            ", discountPercent=" + getDiscountPercent() +
            ", increasePercent=" + getIncreasePercent() +
            ", decreasePercent=" + getDecreasePercent() +
            "}";
    }
}
