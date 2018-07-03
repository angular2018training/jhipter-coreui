package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderServices;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderServicesRepository extends JpaRepository<OrderServices, Long>, JpaSpecificationExecutor<OrderServices> {

}
