    package vn.nextlogix.service.dto;


    import java.time.Instant;
    import javax.validation.constraints.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Objects;
    import javax.persistence.Lob;

/**
 * A DTO for the UserExtraInfo entity.
 */
public class UserExtraInfoSearchDTO implements Serializable {

private Long id;


private String email;


private String phone;


private String address;


private Instant validDate;


private Instant lastLoginDate;


@Lob
private byte[] contractFile;
private String contractFileContentType;


private Instant contractExpirationDate;

private Long companyId;



public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
    }

public String getEmail() {
    return email;
    }

public void setEmail(String email) {
    this.email = email;
    }

public String getPhone() {
    return phone;
    }

public void setPhone(String phone) {
    this.phone = phone;
    }

public String getAddress() {
    return address;
    }

public void setAddress(String address) {
    this.address = address;
    }

public Instant getValidDate() {
    return validDate;
    }

public void setValidDate(Instant validDate) {
    this.validDate = validDate;
    }

public Instant getLastLoginDate() {
    return lastLoginDate;
    }

public void setLastLoginDate(Instant lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
    }

public byte[] getContractFile() {
    return contractFile;
    }

public void setContractFile(byte[] contractFile) {
    this.contractFile = contractFile;
    }

public String getContractFileContentType() {
    return contractFileContentType;
    }

public void setContractFileContentType(String contractFileContentType) {
    this.contractFileContentType = contractFileContentType;
    }

public Instant getContractExpirationDate() {
    return contractExpirationDate;
    }

public void setContractExpirationDate(Instant contractExpirationDate) {
    this.contractExpirationDate = contractExpirationDate;
    }


public Long getCompanyId() {
    return companyId;
    }

public void setCompanyId(Long companyId) {
    this.companyId = companyId;
    }


@Override
public boolean equals(Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }

UserExtraInfoSearchDTO userExtraInfoSearchDTO = (UserExtraInfoSearchDTO) o;
    if(userExtraInfoSearchDTO.getId() == null || getId() == null) {
    return false;
    }
    return Objects.equals(getId(), userExtraInfoSearchDTO.getId());
    }

@Override
public int hashCode() {
    return Objects.hashCode(getId());
    }

@Override
public String toString() {
    return "UserExtraInfoSearchDTO{" +
    "id=" + getId() +
    ", email='" + getEmail() + "'" +
    ", phone='" + getPhone() + "'" +
    ", address='" + getAddress() + "'" +
    ", validDate='" + getValidDate() + "'" +
    ", lastLoginDate='" + getLastLoginDate() + "'" +
    ", contractFile='" + getContractFile() + "'" +
    ", contractExpirationDate='" + getContractExpirationDate() + "'" +
    "}";
    }
    }
