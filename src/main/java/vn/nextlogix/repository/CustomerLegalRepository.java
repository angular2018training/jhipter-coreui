package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerLegal;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the CustomerLegal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerLegalRepository extends JpaRepository<CustomerLegal, Long>, JpaSpecificationExecutor<CustomerLegal> {
    

}
