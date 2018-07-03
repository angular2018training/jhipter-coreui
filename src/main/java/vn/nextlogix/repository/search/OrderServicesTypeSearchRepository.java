package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderServicesType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderServicesType entity.
 */
public interface OrderServicesTypeSearchRepository extends ElasticsearchRepository<OrderServicesType, Long> {
}
