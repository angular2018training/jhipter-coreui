package vn.nextlogix.repository.search;

import vn.nextlogix.domain.UserExtraInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserExtraInfo entity.
 */
public interface UserExtraInfoSearchRepository extends ElasticsearchRepository<UserExtraInfo, Long> {
}
