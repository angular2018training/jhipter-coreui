package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderFee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderFee entity.
 */
public interface OrderFeeSearchRepository extends ElasticsearchRepository<OrderFee, Long> {
}
