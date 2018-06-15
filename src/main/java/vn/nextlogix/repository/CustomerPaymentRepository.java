package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerPayment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {

}
