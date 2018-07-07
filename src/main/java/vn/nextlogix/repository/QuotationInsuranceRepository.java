package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationInsurance;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationInsurance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationInsuranceRepository extends JpaRepository<QuotationInsurance, Long>, JpaSpecificationExecutor<QuotationInsurance> {

}
