package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationPickup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationPickup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationPickupRepository extends JpaRepository<QuotationPickup, Long>, JpaSpecificationExecutor<QuotationPickup> {

}
