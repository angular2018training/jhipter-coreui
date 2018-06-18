package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerLegal;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerLegal entity.
 */
public interface CustomerLegalSearchRepository extends ElasticsearchRepository<CustomerLegal, Long> {
}
