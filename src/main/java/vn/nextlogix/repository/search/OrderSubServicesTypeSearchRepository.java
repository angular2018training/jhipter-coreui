package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderSubServicesType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderSubServicesType entity.
 */
public interface OrderSubServicesTypeSearchRepository extends ElasticsearchRepository<OrderSubServicesType, Long> {
}
