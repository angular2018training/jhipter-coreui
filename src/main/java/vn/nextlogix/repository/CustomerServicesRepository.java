package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerServices;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerServicesRepository extends JpaRepository<CustomerServices, Long>, JpaSpecificationExecutor<CustomerServices> {

}
