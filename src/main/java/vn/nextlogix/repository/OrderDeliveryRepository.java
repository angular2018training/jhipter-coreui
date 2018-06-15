package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderDelivery;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

}
