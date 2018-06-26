package vn.nextlogix.service.impl;

import vn.nextlogix.service.BankService;
import vn.nextlogix.domain.Bank;
import vn.nextlogix.repository.BankRepository;
import vn.nextlogix.repository.search.BankSearchRepository;
import vn.nextlogix.service.dto.BankDTO;
import vn.nextlogix.service.dto.BankSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
import vn.nextlogix.service.mapper.BankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import java.util.stream.StreamSupport;

    import java.util.List;
    import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    import vn.nextlogix.repository.search.CompanySearchRepository;
    import vn.nextlogix.service.mapper.CompanyMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
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


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;


    public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper, BankSearchRepository bankSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.bankSearchRepository = bankSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Banks");
        return bankRepository.findAll(pageable)
            .map(bankMapper::toDto);
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
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Banks for query {}", query);
        Page<Bank> result = bankSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(bankMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<BankDTO> searchExample(BankSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getCode())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*"+searchDto.getCode()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*"+searchDto.getName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getDescription())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*"+searchDto.getDescription()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<Bank> bankPage= bankSearchRepository.search(query);
            List<BankDTO> bankList =  StreamSupport
            .stream(bankPage.spliterator(), false)
            .map(bankMapper::toDto)
            .collect(Collectors.toList());
            bankList.forEach(bankDto -> {
            if(bankDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(bankDto.getCompanyId());
                bankDto.setCompanyDTO(companyMapper.toDto(company));
            }
            });
            return new PageImpl<>(bankList,pageable,bankPage.getTotalElements());
        }
}
