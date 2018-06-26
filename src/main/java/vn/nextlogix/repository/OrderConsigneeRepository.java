package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderConsignee;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderConsignee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderConsigneeRepository extends JpaRepository<OrderConsignee, Long>, JpaSpecificationExecutor<OrderConsignee> {

}
