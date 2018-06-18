package vn.nextlogix.repository;

import vn.nextlogix.domain.UserPosition;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserPosition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPositionRepository extends JpaRepository<UserPosition, Long> {

}
