package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationDomesticDelivery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationDomesticDelivery entity.
 */
public interface QuotationDomesticDeliverySearchRepository extends ElasticsearchRepository<QuotationDomesticDelivery, Long> {
}
