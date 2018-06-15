package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderPickup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderPickup entity.
 */
public interface OrderPickupSearchRepository extends ElasticsearchRepository<OrderPickup, Long> {
}
