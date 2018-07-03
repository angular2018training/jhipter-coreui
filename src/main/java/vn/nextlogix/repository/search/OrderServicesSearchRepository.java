package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderServices;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderServices entity.
 */
public interface OrderServicesSearchRepository extends ElasticsearchRepository<OrderServices, Long> {
}
