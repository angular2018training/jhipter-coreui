package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerLegalFileUpload;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerLegalFileUpload entity.
 */
public interface CustomerLegalFileUploadSearchRepository extends ElasticsearchRepository<CustomerLegalFileUpload, Long> {
}
