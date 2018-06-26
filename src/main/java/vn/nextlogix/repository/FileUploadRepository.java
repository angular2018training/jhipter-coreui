package vn.nextlogix.repository;

import vn.nextlogix.domain.FileUpload;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FileUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long>, JpaSpecificationExecutor<FileUpload> {

}
