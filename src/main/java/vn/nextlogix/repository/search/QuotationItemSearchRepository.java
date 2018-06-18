package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationItem entity.
 */
public interface QuotationItemSearchRepository extends ElasticsearchRepository<QuotationItem, Long> {
}
