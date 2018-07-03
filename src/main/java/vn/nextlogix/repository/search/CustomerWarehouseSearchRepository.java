package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerWarehouse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerWarehouse entity.
 */
public interface CustomerWarehouseSearchRepository extends ElasticsearchRepository<CustomerWarehouse, Long> {
}
