package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationSubServices;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationSubServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationSubServicesRepository extends JpaRepository<QuotationSubServices, Long>, JpaSpecificationExecutor<QuotationSubServices> {

}
