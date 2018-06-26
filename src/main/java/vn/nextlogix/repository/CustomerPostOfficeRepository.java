package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerPostOffice;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerPostOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerPostOfficeRepository extends JpaRepository<CustomerPostOffice, Long>, JpaSpecificationExecutor<CustomerPostOffice> {

}
