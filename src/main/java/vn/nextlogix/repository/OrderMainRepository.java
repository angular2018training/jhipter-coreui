package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderMain;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderMain entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderMainRepository extends JpaRepository<OrderMain, Long>, JpaSpecificationExecutor<OrderMain> {

}
