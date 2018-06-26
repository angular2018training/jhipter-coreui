package vn.nextlogix.repository;

import vn.nextlogix.domain.UserGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup> {

}
