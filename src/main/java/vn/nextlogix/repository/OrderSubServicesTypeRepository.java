package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderSubServicesType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderSubServicesType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderSubServicesTypeRepository extends JpaRepository<OrderSubServicesType, Long>, JpaSpecificationExecutor<OrderSubServicesType> {

}
