package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationType entity.
 */
public interface QuotationTypeSearchRepository extends ElasticsearchRepository<QuotationType, Long> {
}
