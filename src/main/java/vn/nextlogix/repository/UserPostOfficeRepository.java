package vn.nextlogix.repository;

import vn.nextlogix.domain.Authority;
import vn.nextlogix.domain.UserGroup;
import vn.nextlogix.domain.UserPostOffice;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for the UserPostOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPostOfficeRepository extends JpaRepository<UserPostOffice, Long>, JpaSpecificationExecutor<UserPostOffice> {
    @Query("select distinct user_post_office from UserPostOffice user_post_office left join fetch user_post_office.userGroups")
    List<UserPostOffice> findAllWithEagerRelationships();

    @Query("select user_post_office from UserPostOffice user_post_office left join fetch user_post_office.userGroups where user_post_office.id =:id")
    UserPostOffice findOneWithEagerRelationships(@Param("id") Long id);

	List<UserPostOffice> findByUserExtraInfoParent_IdAndPostOfficeId(Long userExtraInfoId, Long postOfficeId);
	
	List<UserPostOffice> findByUserExtraInfoParent_Id(Long userExtraInfoId);
	 @Query("select DISTINCT authority from UserPostOffice user_post_office left join  user_post_office.userGroups userGroup left join user_post_office.userExtraInfoParent userExtraInfo left join userGroup.authorities authority where user_post_office.id =:postOfficeId and userExtraInfo.user.id =:userId ")
	Set<Authority> getAllAuthoritiesByUserExtraInfoParent_User_IdAndPostOfficeId(@Param("userId") Long userId, @Param("postOfficeId") Long postOfficeId);
	
	

}
