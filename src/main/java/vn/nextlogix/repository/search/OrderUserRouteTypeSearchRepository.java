package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderUserRouteType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderUserRouteType entity.
 */
public interface OrderUserRouteTypeSearchRepository extends ElasticsearchRepository<OrderUserRouteType, Long> {
}
