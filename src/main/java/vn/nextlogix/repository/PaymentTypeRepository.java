package vn.nextlogix.repository;

import vn.nextlogix.domain.PaymentType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PaymentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>, JpaSpecificationExecutor<PaymentType> {

}
