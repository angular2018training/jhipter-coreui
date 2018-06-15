package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationItemService;
import vn.nextlogix.domain.QuotationItem;
import vn.nextlogix.repository.QuotationItemRepository;
import vn.nextlogix.repository.search.QuotationItemSearchRepository;
import vn.nextlogix.service.dto.QuotationItemDTO;
import vn.nextlogix.service.mapper.QuotationItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing QuotationItem.
 */
@Service
@Transactional
public class QuotationItemServiceImpl implements QuotationItemService {

    private final Logger log = LoggerFactory.getLogger(QuotationItemServiceImpl.class);

    private final QuotationItemRepository quotationItemRepository;

    private final QuotationItemMapper quotationItemMapper;

    private final QuotationItemSearchRepository quotationItemSearchRepository;

    public QuotationItemServiceImpl(QuotationItemRepository quotationItemRepository, QuotationItemMapper quotationItemMapper, QuotationItemSearchRepository quotationItemSearchRepository) {
        this.quotationItemRepository = quotationItemRepository;
        this.quotationItemMapper = quotationItemMapper;
        this.quotationItemSearchRepository = quotationItemSearchRepository;
    }

    /**
     * Save a quotationItem.
     *
     * @param quotationItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationItemDTO save(QuotationItemDTO quotationItemDTO) {
        log.debug("Request to save QuotationItem : {}", quotationItemDTO);
        QuotationItem quotationItem = quotationItemMapper.toEntity(quotationItemDTO);
        quotationItem = quotationItemRepository.save(quotationItem);
        QuotationItemDTO result = quotationItemMapper.toDto(quotationItem);
        quotationItemSearchRepository.save(quotationItem);
        return result;
    }

    /**
     * Get all the quotationItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuotationItemDTO> findAll() {
        log.debug("Request to get all QuotationItems");
        return quotationItemRepository.findAll().stream()
            .map(quotationItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one quotationItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationItemDTO findOne(Long id) {
        log.debug("Request to get QuotationItem : {}", id);
        QuotationItem quotationItem = quotationItemRepository.findOne(id);
        return quotationItemMapper.toDto(quotationItem);
    }

    /**
     * Delete the quotationItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationItem : {}", id);
        quotationItemRepository.delete(id);
        quotationItemSearchRepository.delete(id);
    }

    /**
     * Search for the quotationItem corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuotationItemDTO> search(String query) {
        log.debug("Request to search QuotationItems for query {}", query);
        return StreamSupport
            .stream(quotationItemSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(quotationItemMapper::toDto)
            .collect(Collectors.toList());
    }
}
