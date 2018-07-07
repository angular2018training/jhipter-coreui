package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderSubServices;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderSubServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderSubServicesRepository extends JpaRepository<OrderSubServices, Long>, JpaSpecificationExecutor<OrderSubServices> {

}
