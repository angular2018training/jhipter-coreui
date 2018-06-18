package vn.nextlogix.repository;

import vn.nextlogix.domain.PostOffice;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PostOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {

}
