package vn.nextlogix.repository;

import vn.nextlogix.domain.DistrictType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DistrictType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistrictTypeRepository extends JpaRepository<DistrictType, Long>, JpaSpecificationExecutor<DistrictType> {

}
