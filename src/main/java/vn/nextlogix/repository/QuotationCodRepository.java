package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationCod;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationCod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationCodRepository extends JpaRepository<QuotationCod, Long>, JpaSpecificationExecutor<QuotationCod> {

}
