package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderService;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

}
