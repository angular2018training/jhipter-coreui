package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerType entity.
 */
public interface CustomerTypeSearchRepository extends ElasticsearchRepository<CustomerType, Long> {
}
