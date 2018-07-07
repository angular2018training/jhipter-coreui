package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationSubServices;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationSubServices entity.
 */
public interface QuotationSubServicesSearchRepository extends ElasticsearchRepository<QuotationSubServices, Long> {
}
