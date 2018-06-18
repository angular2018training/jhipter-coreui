package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderService entity.
 */
public interface OrderServiceSearchRepository extends ElasticsearchRepository<OrderService, Long> {
}
