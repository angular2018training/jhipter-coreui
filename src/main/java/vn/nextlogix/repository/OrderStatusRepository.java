package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderStatus;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>, JpaSpecificationExecutor<OrderStatus> {

}
