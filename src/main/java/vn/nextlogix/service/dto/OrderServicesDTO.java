package vn.nextlogix.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderServices entity.
 */
public class OrderServicesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String description;

    private Long companyId;

    private CompanyDTO  companyDTO;


    private Long orderServicesTypeId;

    private OrderServicesTypeDTO  orderServicesTypeDTO;


    private Set<QuotationDTO> quotations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public OrderServicesTypeDTO getOrderServicesTypeDTO() {
        return this.orderServicesTypeDTO;
    }

    public void setOrderServicesTypeDTO(OrderServicesTypeDTO orderServicesTypeDTO ) {
        this.orderServicesTypeDTO = orderServicesTypeDTO;
    }
    public Long getOrderServicesTypeId() {
        return orderServicesTypeId;
        }

    public void setOrderServicesTypeId(Long orderServicesTypeId) {
        this.orderServicesTypeId = orderServicesTypeId;
        }


    public Set<QuotationDTO> getQuotations() {
        return quotations;
    }

    public void setQuotations(Set<QuotationDTO> quotations) {
        this.quotations = quotations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderServicesDTO orderServicesDTO = (OrderServicesDTO) o;
        if(orderServicesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderServicesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderServicesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
