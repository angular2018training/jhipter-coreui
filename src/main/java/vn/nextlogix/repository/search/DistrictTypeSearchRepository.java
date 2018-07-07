package vn.nextlogix.repository.search;

import vn.nextlogix.domain.DistrictType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DistrictType entity.
 */
public interface DistrictTypeSearchRepository extends ElasticsearchRepository<DistrictType, Long> {
}
