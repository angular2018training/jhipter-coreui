package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationDomesticDelivery;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationDomesticDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationDomesticDeliveryRepository extends JpaRepository<QuotationDomesticDelivery, Long>, JpaSpecificationExecutor<QuotationDomesticDelivery> {

}
