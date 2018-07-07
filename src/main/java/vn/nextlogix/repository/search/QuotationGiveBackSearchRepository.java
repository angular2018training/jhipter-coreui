package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationGiveBack;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationGiveBack entity.
 */
public interface QuotationGiveBackSearchRepository extends ElasticsearchRepository<QuotationGiveBack, Long> {
}
