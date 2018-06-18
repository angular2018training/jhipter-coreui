package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderStatus entity.
 */
public interface OrderStatusSearchRepository extends ElasticsearchRepository<OrderStatus, Long> {
}
