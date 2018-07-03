package vn.nextlogix.repository;

import vn.nextlogix.domain.CustomerLegalFileUpload;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerLegalFileUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerLegalFileUploadRepository extends JpaRepository<CustomerLegalFileUpload, Long>, JpaSpecificationExecutor<CustomerLegalFileUpload> {

}
