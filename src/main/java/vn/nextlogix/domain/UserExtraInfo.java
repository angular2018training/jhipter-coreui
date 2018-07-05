package vn.nextlogix.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserExtraInfo.
 */
@Entity
@Table(name = "user_extra_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "userextrainfo")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class UserExtraInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "valid_date", nullable = false)
    private LocalDate validDate;

    @Column(name = "last_login_date")
    private Instant lastLoginDate;

    @Lob
    @Column(name = "contract_file")
    private byte[] contractFile;

    @Column(name = "contract_file_content_type")
    private String contractFileContentType;

    @Column(name = "contract_expiration_date")
    private Instant contractExpirationDate;

    @OneToMany(mappedBy = "userExtraInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserPosition> userPositions = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    

    @OneToMany(mappedBy = "userExtraInfoParent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserPostOffice> userPostOfficeDetailLists = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public UserExtraInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public UserExtraInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public UserExtraInfo validDate(LocalDate validDate) {
        this.validDate = validDate;
        return this;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public UserExtraInfo lastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public byte[] getContractFile() {
        return contractFile;
    }

    public UserExtraInfo contractFile(byte[] contractFile) {
        this.contractFile = contractFile;
        return this;
    }

    public void setContractFile(byte[] contractFile) {
        this.contractFile = contractFile;
    }

    public String getContractFileContentType() {
        return contractFileContentType;
    }

    public UserExtraInfo contractFileContentType(String contractFileContentType) {
        this.contractFileContentType = contractFileContentType;
        return this;
    }

    public void setContractFileContentType(String contractFileContentType) {
        this.contractFileContentType = contractFileContentType;
    }

    public Instant getContractExpirationDate() {
        return contractExpirationDate;
    }

    public UserExtraInfo contractExpirationDate(Instant contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
        return this;
    }

    public void setContractExpirationDate(Instant contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }

    public Set<UserPosition> getUserPositions() {
        return userPositions;
    }

    public UserExtraInfo userPositions(Set<UserPosition> userPositions) {
        this.userPositions = userPositions;
        return this;
    }

    public UserExtraInfo addUserPosition(UserPosition userPosition) {
        this.userPositions.add(userPosition);
        userPosition.setUserExtraInfo(this);
        return this;
    }

    public UserExtraInfo removeUserPosition(UserPosition userPosition) {
        this.userPositions.remove(userPosition);
        userPosition.setUserExtraInfo(null);
        return this;
    }

    public void setUserPositions(Set<UserPosition> userPositions) {
        this.userPositions = userPositions;
    }

    public Company getCompany() {
        return company;
    }

    public UserExtraInfo company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	 public Set<UserPostOffice> getUserPostOfficeDetailLists() {
	        return userPostOfficeDetailLists;
	    }

	    public UserExtraInfo userPostOfficeDetailLists(Set<UserPostOffice> userPostOffices) {
	        this.userPostOfficeDetailLists = userPostOffices;
	        return this;
	    }

	    public UserExtraInfo addUserPostOfficeDetailList(UserPostOffice userPostOffice) {
	        this.userPostOfficeDetailLists.add(userPostOffice);
	        userPostOffice.setUserExtraInfoParent(this);
	        return this;
	    }

	    public UserExtraInfo removeUserPostOfficeDetailList(UserPostOffice userPostOffice) {
	        this.userPostOfficeDetailLists.remove(userPostOffice);
	        userPostOffice.setUserExtraInfoParent(null);
	        return this;
	    }

	    public void setUserPostOfficeDetailLists(Set<UserPostOffice> userPostOffices) {
	        this.userPostOfficeDetailLists = userPostOffices;
	    }

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserExtraInfo userExtraInfo = (UserExtraInfo) o;
        if (userExtraInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtraInfo.getId());
    }



    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtraInfo{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", validDate='" + getValidDate() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", contractFile='" + getContractFile() + "'" +
            ", contractFileContentType='" + getContractFileContentType() + "'" +
            ", contractExpirationDate='" + getContractExpirationDate() + "'" +
            "}";
    }
}
