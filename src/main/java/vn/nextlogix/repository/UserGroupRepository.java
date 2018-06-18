package vn.nextlogix.repository;

import vn.nextlogix.domain.UserGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the UserGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @Query("select distinct user_group from UserGroup user_group left join fetch user_group.roles")
    List<UserGroup> findAllWithEagerRelationships();

    @Query("select user_group from UserGroup user_group left join fetch user_group.roles where user_group.id =:id")
    UserGroup findOneWithEagerRelationships(@Param("id") Long id);

}
