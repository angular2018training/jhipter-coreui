package vn.nextlogix.repository;

import vn.nextlogix.domain.Warehouse;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Warehouse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {

}
