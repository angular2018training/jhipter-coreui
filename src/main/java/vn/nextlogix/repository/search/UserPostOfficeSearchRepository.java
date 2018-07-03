package vn.nextlogix.repository.search;

import vn.nextlogix.domain.UserPostOffice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserPostOffice entity.
 */
public interface UserPostOfficeSearchRepository extends ElasticsearchRepository<UserPostOffice, Long> {
}
