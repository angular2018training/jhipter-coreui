package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderFee;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderFeeRepository extends JpaRepository<OrderFee, Long> {

}
