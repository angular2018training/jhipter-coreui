package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderUserRoute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderUserRoute entity.
 */
public interface OrderUserRouteSearchRepository extends ElasticsearchRepository<OrderUserRoute, Long> {
}
