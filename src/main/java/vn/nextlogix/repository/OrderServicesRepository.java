package vn.nextlogix.repository;

import vn.nextlogix.domain.OrderServices;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the OrderServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderServicesRepository extends JpaRepository<OrderServices, Long>, JpaSpecificationExecutor<OrderServices> {
    @Query("select distinct order_services from OrderServices order_services left join fetch order_services.quotations")
    List<OrderServices> findAllWithEagerRelationships();

    @Query("select order_services from OrderServices order_services left join fetch order_services.quotations where order_services.id =:id")
    OrderServices findOneWithEagerRelationships(@Param("id") Long id);

}
