package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerSource;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerSourceRepository extends JpaRepository<CustomerSource, Long>, JpaSpecificationExecutor<CustomerSource> {

}
