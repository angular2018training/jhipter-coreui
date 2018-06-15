package vn.nextlogix.repository.search;

import vn.nextlogix.domain.Ward;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Ward entity.
 */
public interface WardSearchRepository extends ElasticsearchRepository<Ward, Long> {
}
