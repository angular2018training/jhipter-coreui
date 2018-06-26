package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderUserRoute;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderUserRoute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderUserRouteRepository extends JpaRepository<OrderUserRoute, Long>, JpaSpecificationExecutor<OrderUserRoute> {

}
