package vn.nextlogix.repository;

import vn.nextlogix.domain.District;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the District entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}
