package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerSource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerSource entity.
 */
public interface CustomerSourceSearchRepository extends ElasticsearchRepository<CustomerSource, Long> {
}
