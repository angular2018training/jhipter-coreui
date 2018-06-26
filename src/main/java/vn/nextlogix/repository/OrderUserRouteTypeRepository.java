package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderUserRouteType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderUserRouteType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderUserRouteTypeRepository extends JpaRepository<OrderUserRouteType, Long>, JpaSpecificationExecutor<OrderUserRouteType> {

}
