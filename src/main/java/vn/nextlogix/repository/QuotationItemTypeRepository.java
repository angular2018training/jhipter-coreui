package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationItemType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationItemType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationItemTypeRepository extends JpaRepository<QuotationItemType, Long>, JpaSpecificationExecutor<QuotationItemType> {

}
