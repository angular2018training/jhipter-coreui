package vn.nextlogix.repository.search;

import vn.nextlogix.domain.UserPosition;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserPosition entity.
 */
public interface UserPositionSearchRepository extends ElasticsearchRepository<UserPosition, Long> {
}
