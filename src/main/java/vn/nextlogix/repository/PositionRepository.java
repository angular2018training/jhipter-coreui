package vn.nextlogix.repository;

import vn.nextlogix.domain.Position;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Position entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PositionRepository extends JpaRepository<Position, Long>, JpaSpecificationExecutor<Position> {

}
