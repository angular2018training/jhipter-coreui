package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerServices;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerServices entity.
 */
public interface CustomerServicesSearchRepository extends ElasticsearchRepository<CustomerServices, Long> {
}
