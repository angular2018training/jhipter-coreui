    package vn.nextlogix.service.dto;


    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.Objects;

/**
 * A DTO for the Weight entity.
 */
public class WeightSearchDTO implements Serializable {

private Long id;


private Double minAmount;


private Double maxAmount;


private String name;


private String description;

public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public Double getMinAmount() {
    return minAmount;
    }

public void setMinAmount(Double minAmount) {
    this.minAmount = minAmount;
    }

public Double getMaxAmount() {
    return maxAmount;
    }

public void setMaxAmount(Double maxAmount) {
    this.maxAmount = maxAmount;
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

@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

WeightSearchDTO weightSearchDTO = (WeightSearchDTO) o;
    if(weightSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), weightSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "WeightSearchDTO{" +
    "id=" + getId() +
    ", minAmount=" + getMinAmount() +
    ", maxAmount=" + getMaxAmount() +
    ", name='" + getName() + "'" +
    ", description='" + getDescription() + "'" +
    "}";
    }
    }
