package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationCod;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationCod entity.
 */
public interface QuotationCodSearchRepository extends ElasticsearchRepository<QuotationCod, Long> {
}
