package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderPickup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderPickup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderPickupRepository extends JpaRepository<OrderPickup, Long>, JpaSpecificationExecutor<OrderPickup> {

}
