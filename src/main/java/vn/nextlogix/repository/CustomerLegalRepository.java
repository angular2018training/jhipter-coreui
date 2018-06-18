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
public interface CustomerLegalRepository extends JpaRepository<CustomerLegal, Long> {
    @Query("select distinct customer_legal from CustomerLegal customer_legal left join fetch customer_legal.fileUploads")
    List<CustomerLegal> findAllWithEagerRelationships();

    @Query("select customer_legal from CustomerLegal customer_legal left join fetch customer_legal.fileUploads where customer_legal.id =:id")
    CustomerLegal findOneWithEagerRelationships(@Param("id") Long id);

}
