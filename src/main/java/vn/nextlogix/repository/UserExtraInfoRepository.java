package vn.nextlogix.repository;

import vn.nextlogix.domain.UserExtraInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the UserExtraInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtraInfoRepository extends JpaRepository<UserExtraInfo, Long> {
    @Query("select distinct user_extra_info from UserExtraInfo user_extra_info left join fetch user_extra_info.roles left join fetch user_extra_info.userGroups")
    List<UserExtraInfo> findAllWithEagerRelationships();

    @Query("select user_extra_info from UserExtraInfo user_extra_info left join fetch user_extra_info.roles left join fetch user_extra_info.userGroups where user_extra_info.id =:id")
    UserExtraInfo findOneWithEagerRelationships(@Param("id") Long id);

}
