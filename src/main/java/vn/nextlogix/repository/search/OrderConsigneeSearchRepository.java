package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderConsignee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderConsignee entity.
 */
public interface OrderConsigneeSearchRepository extends ElasticsearchRepository<OrderConsignee, Long> {
}
