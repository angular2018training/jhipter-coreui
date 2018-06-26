package vn.nextlogix.repository.search;

import vn.nextlogix.domain.QuotationItemType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuotationItemType entity.
 */
public interface QuotationItemTypeSearchRepository extends ElasticsearchRepository<QuotationItemType, Long> {
}
