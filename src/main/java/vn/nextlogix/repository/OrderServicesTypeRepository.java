package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderServicesType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderServicesType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderServicesTypeRepository extends JpaRepository<OrderServicesType, Long>, JpaSpecificationExecutor<OrderServicesType> {

}
