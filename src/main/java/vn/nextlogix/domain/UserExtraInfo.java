package vn.nextlogix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
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
public class UserExtraInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "userExtraInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserPosition> userPositions = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "user_extra_info_role",
               joinColumns = @JoinColumn(name="user_extra_infos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="roles_id", referencedColumnName="id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "user_extra_info_user_group",
               joinColumns = @JoinColumn(name="user_extra_infos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="user_groups_id", referencedColumnName="id"))
    private Set<UserGroup> userGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public UserExtraInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public UserExtraInfo roles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserExtraInfo addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public UserExtraInfo removeRole(Role role) {
        this.roles.remove(role);
        return this;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public UserExtraInfo userGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
        return this;
    }

    public UserExtraInfo addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
        return this;
    }

    public UserExtraInfo removeUserGroup(UserGroup userGroup) {
        this.userGroups.remove(userGroup);
        return this;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
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
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
