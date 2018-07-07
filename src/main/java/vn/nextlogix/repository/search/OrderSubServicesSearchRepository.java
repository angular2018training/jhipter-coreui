package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderSubServices;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderSubServices entity.
 */
public interface OrderSubServicesSearchRepository extends ElasticsearchRepository<OrderSubServices, Long> {
}
