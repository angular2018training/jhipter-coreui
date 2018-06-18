package vn.nextlogix.repository.search;

import vn.nextlogix.domain.CustomerPostOffice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerPostOffice entity.
 */
public interface CustomerPostOfficeSearchRepository extends ElasticsearchRepository<CustomerPostOffice, Long> {
}
