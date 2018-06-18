package vn.nextlogix.repository.search;

import vn.nextlogix.domain.FileUpload;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FileUpload entity.
 */
public interface FileUploadSearchRepository extends ElasticsearchRepository<FileUpload, Long> {
}
