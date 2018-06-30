package vn.nextlogix.repository;

import vn.nextlogix.domain.DetailForm;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DetailForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailFormRepository extends JpaRepository<DetailForm, Long>, JpaSpecificationExecutor<DetailForm> {

}
