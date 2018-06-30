package vn.nextlogix.repository.search;

import vn.nextlogix.domain.MasterForm;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MasterForm entity.
 */
public interface MasterFormSearchRepository extends ElasticsearchRepository<MasterForm, Long> {
}
