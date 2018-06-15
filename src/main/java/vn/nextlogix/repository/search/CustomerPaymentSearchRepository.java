package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerPayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerPayment entity.
 */
public interface CustomerPaymentSearchRepository extends ElasticsearchRepository<CustomerPayment, Long> {
}
