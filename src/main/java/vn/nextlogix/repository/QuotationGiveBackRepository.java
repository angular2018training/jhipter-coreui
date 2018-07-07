package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationGiveBack;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationGiveBack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationGiveBackRepository extends JpaRepository<QuotationGiveBack, Long>, JpaSpecificationExecutor<QuotationGiveBack> {

}
