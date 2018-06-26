package vn.nextlogix.repository;

import vn.nextlogix.domain.UserPosition;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the UserPosition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPositionRepository extends JpaRepository<UserPosition, Long>, JpaSpecificationExecutor<UserPosition> {
    @Query("select distinct user_position from UserPosition user_position left join fetch user_position.userGroups")
    List<UserPosition> findAllWithEagerRelationships();

    @Query("select user_position from UserPosition user_position left join fetch user_position.userGroups where user_position.id =:id")
    UserPosition findOneWithEagerRelationships(@Param("id") Long id);

}
