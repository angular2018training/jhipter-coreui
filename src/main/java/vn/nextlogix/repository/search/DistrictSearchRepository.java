package vn.nextlogix.repository.search;

import vn.nextlogix.domain.District;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the District entity.
 */
public interface DistrictSearchRepository extends ElasticsearchRepository<District, Long> {
}
