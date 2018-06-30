package vn.nextlogix.repository;

import vn.nextlogix.domain.MasterForm;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MasterForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterFormRepository extends JpaRepository<MasterForm, Long>, JpaSpecificationExecutor<MasterForm> {

}
