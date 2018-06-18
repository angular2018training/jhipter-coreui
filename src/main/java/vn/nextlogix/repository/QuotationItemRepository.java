package vn.nextlogix.repository;

import vn.nextlogix.domain.QuotationItem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationItemRepository extends JpaRepository<QuotationItem, Long> {

}
