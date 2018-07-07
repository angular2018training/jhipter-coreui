package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationReturn;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationReturn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationReturnRepository extends JpaRepository<QuotationReturn, Long>, JpaSpecificationExecutor<QuotationReturn> {

}
