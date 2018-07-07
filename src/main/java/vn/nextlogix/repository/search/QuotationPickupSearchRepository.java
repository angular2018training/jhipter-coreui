package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationPickup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationPickup entity.
 */
public interface QuotationPickupSearchRepository extends ElasticsearchRepository<QuotationPickup, Long> {
}
