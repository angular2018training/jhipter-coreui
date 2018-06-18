package vn.nextlogix.service.impl;

import vn.nextlogix.service.BankService;
import vn.nextlogix.domain.Bank;
import vn.nextlogix.repository.BankRepository;
import vn.nextlogix.repository.search.BankSearchRepository;
import vn.nextlogix.service.dto.BankDTO;
import vn.nextlogix.service.mapper.BankMapper;
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
 * Service Implementation for managing Bank.
 */
@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);

    private final BankRepository bankRepository;

    private final BankMapper bankMapper;

    private final BankSearchRepository bankSearchRepository;

    public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper, BankSearchRepository bankSearchRepository) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.bankSearchRepository = bankSearchRepository;
    }

    /**
     * Save a bank.
     *
     * @param bankDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BankDTO save(BankDTO bankDTO) {
        log.debug("Request to save Bank : {}", bankDTO);
        Bank bank = bankMapper.toEntity(bankDTO);
        bank = bankRepository.save(bank);
        BankDTO result = bankMapper.toDto(bank);
        bankSearchRepository.save(bank);
        return result;
    }

    /**
     * Get all the banks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankDTO> findAll() {
        log.debug("Request to get all Banks");
        return bankRepository.findAll().stream()
            .map(bankMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bank by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BankDTO findOne(Long id) {
        log.debug("Request to get Bank : {}", id);
        Bank bank = bankRepository.findOne(id);
        return bankMapper.toDto(bank);
    }

    /**
     * Delete the bank by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bank : {}", id);
        bankRepository.delete(id);
        bankSearchRepository.delete(id);
    }

    /**
     * Search for the bank corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankDTO> search(String query) {
        log.debug("Request to search Banks for query {}", query);
        return StreamSupport
            .stream(bankSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(bankMapper::toDto)
            .collect(Collectors.toList());
    }
}
