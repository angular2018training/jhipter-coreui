package vn.nextlogix.repository;

import vn.nextlogix.domain.Weight;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Weight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeightRepository extends JpaRepository<Weight, Long>, JpaSpecificationExecutor<Weight> {

}
