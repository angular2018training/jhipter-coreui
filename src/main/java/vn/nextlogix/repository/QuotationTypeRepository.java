package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationTypeRepository extends JpaRepository<QuotationType, Long>, JpaSpecificationExecutor<QuotationType> {

}
