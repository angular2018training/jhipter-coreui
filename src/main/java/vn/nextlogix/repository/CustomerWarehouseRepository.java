package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerWarehouse;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerWarehouse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerWarehouseRepository extends JpaRepository<CustomerWarehouse, Long>, JpaSpecificationExecutor<CustomerWarehouse> {

}
