package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationInsurance;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationInsurance entity.
 */
public interface QuotationInsuranceSearchRepository extends ElasticsearchRepository<QuotationInsurance, Long> {
}
