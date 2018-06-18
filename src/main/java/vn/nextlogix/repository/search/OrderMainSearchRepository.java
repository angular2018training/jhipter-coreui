package vn.nextlogix.repository.search;

import vn.nextlogix.domain.OrderMain;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderMain entity.
 */
public interface OrderMainSearchRepository extends ElasticsearchRepository<OrderMain, Long> {
}
