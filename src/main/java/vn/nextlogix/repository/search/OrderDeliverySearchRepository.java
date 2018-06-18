package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderDelivery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderDelivery entity.
 */
public interface OrderDeliverySearchRepository extends ElasticsearchRepository<OrderDelivery, Long> {
}
