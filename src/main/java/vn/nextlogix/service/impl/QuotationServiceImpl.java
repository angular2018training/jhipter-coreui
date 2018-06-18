package vn.nextlogix.service.impl;

import vn.nextlogix.service.QuotationService;
import vn.nextlogix.domain.Quotation;
import vn.nextlogix.repository.QuotationRepository;
import vn.nextlogix.repository.search.QuotationSearchRepository;
import vn.nextlogix.service.dto.QuotationDTO;
import vn.nextlogix.service.mapper.QuotationMapper;
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
 * Service Implementation for managing Quotation.
 */
@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

    private final Logger log = LoggerFactory.getLogger(QuotationServiceImpl.class);

    private final QuotationRepository quotationRepository;

    private final QuotationMapper quotationMapper;

    private final QuotationSearchRepository quotationSearchRepository;

    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper, QuotationSearchRepository quotationSearchRepository) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
        this.quotationSearchRepository = quotationSearchRepository;
    }

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationDTO save(QuotationDTO quotationDTO) {
        log.debug("Request to save Quotation : {}", quotationDTO);
        Quotation quotation = quotationMapper.toEntity(quotationDTO);
        quotation = quotationRepository.save(quotation);
        QuotationDTO result = quotationMapper.toDto(quotation);
        quotationSearchRepository.save(quotation);
        return result;
    }

    /**
     * Get all the quotations.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuotationDTO> findAll() {
        log.debug("Request to get all Quotations");
        return quotationRepository.findAll().stream()
            .map(quotationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one quotation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationDTO findOne(Long id) {
        log.debug("Request to get Quotation : {}", id);
        Quotation quotation = quotationRepository.findOne(id);
        return quotationMapper.toDto(quotation);
    }

    /**
     * Delete the quotation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quotation : {}", id);
        quotationRepository.delete(id);
        quotationSearchRepository.delete(id);
    }

    /**
     * Search for the quotation corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuotationDTO> search(String query) {
        log.debug("Request to search Quotations for query {}", query);
        return StreamSupport
            .stream(quotationSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(quotationMapper::toDto)
            .collect(Collectors.toList());
    }
}
