package vn.nextlogix.repository.search;

import vn.nextlogix.domain.PaymentType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PaymentType entity.
 */
public interface PaymentTypeSearchRepository extends ElasticsearchRepository<PaymentType, Long> {
}
