package vn.nextlogix.repository.search;

import vn.nextlogix.domain.DetailForm;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DetailForm entity.
 */
public interface DetailFormSearchRepository extends ElasticsearchRepository<DetailForm, Long> {
}
