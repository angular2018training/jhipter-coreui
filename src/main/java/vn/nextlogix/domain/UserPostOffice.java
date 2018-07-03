package vn.nextlogix.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserPostOffice.
 */
@Entity
@Table(name = "user_post_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "userpostoffice")
public class UserPostOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @ManyToOne(optional = false)
    @NotNull
    private PostOffice postOffice;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @NotNull
    @JoinTable(name = "user_post_office_user_group",
               joinColumns = @JoinColumn(name="user_post_offices_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="user_groups_id", referencedColumnName="id"))
    private Set<UserGroup> userGroups = new HashSet<>();

    @ManyToOne
    private UserExtraInfo userExtraInfoParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public UserPostOffice company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public UserPostOffice postOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
        return this;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public UserPostOffice userGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
        return this;
    }

    public UserPostOffice addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
        return this;
    }

    public UserPostOffice removeUserGroup(UserGroup userGroup) {
        this.userGroups.remove(userGroup);
        return this;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public UserExtraInfo getUserExtraInfoParent() {
        return userExtraInfoParent;
    }

    public UserPostOffice userExtraInfoParent(UserExtraInfo userExtraInfo) {
        this.userExtraInfoParent = userExtraInfo;
        return this;
    }

    public void setUserExtraInfoParent(UserExtraInfo userExtraInfo) {
        this.userExtraInfoParent = userExtraInfo;
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
        UserPostOffice userPostOffice = (UserPostOffice) o;
        if (userPostOffice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userPostOffice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserPostOffice{" +
            "id=" + getId() +
            "}";
    }
}
