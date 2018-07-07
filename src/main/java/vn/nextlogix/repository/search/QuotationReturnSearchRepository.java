package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationReturn;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationReturn entity.
 */
public interface QuotationReturnSearchRepository extends ElasticsearchRepository<QuotationReturn, Long> {
}
