package vn.nextlogix.repository;

import vn.nextlogix.domain.Ward;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Ward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WardRepository extends JpaRepository<Ward, Long>, JpaSpecificationExecutor<Ward> {

}
