package vn.nextlogix.repository.search;

import vn.nextlogix.domain.PostOffice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PostOffice entity.
 */
public interface PostOfficeSearchRepository extends ElasticsearchRepository<PostOffice, Long> {
}
