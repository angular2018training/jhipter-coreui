package vn.nextlogix.service.impl;

import vn.nextlogix.service.CustomerPaymentService;
import vn.nextlogix.domain.CustomerPayment;
import vn.nextlogix.repository.CustomerPaymentRepository;
import vn.nextlogix.repository.search.CustomerPaymentSearchRepository;
import vn.nextlogix.service.dto.CustomerPaymentDTO;
import vn.nextlogix.service.dto.CustomerPaymentSearchDTO;
import org.springframework.data.domain.PageImpl;
    import vn.nextlogix.domain.Company;
    import vn.nextlogix.domain.Bank;
    import vn.nextlogix.domain.UserExtraInfo;
    import vn.nextlogix.domain.PaymentType;
import vn.nextlogix.service.mapper.CustomerPaymentMapper;
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

    import vn.nextlogix.repository.search.BankSearchRepository;
    import vn.nextlogix.service.mapper.BankMapper;

    import vn.nextlogix.repository.search.UserExtraInfoSearchRepository;
    import vn.nextlogix.service.mapper.UserExtraInfoMapper;

    import vn.nextlogix.repository.search.PaymentTypeSearchRepository;
    import vn.nextlogix.service.mapper.PaymentTypeMapper;
    import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerPayment.
 */
@Service
@Transactional
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    private final Logger log = LoggerFactory.getLogger(CustomerPaymentServiceImpl.class);

    private final CustomerPaymentRepository customerPaymentRepository;

    private final CustomerPaymentMapper customerPaymentMapper;

    private final CustomerPaymentSearchRepository customerPaymentSearchRepository;


        private final CompanySearchRepository companySearchRepository;
        private final CompanyMapper companyMapper;

        private final BankSearchRepository bankSearchRepository;
        private final BankMapper bankMapper;

        private final UserExtraInfoSearchRepository userExtraInfoSearchRepository;
        private final UserExtraInfoMapper userExtraInfoMapper;

        private final PaymentTypeSearchRepository paymentTypeSearchRepository;
        private final PaymentTypeMapper paymentTypeMapper;


    public CustomerPaymentServiceImpl(CustomerPaymentRepository customerPaymentRepository, CustomerPaymentMapper customerPaymentMapper, CustomerPaymentSearchRepository customerPaymentSearchRepository     ,CompanySearchRepository companySearchRepository,CompanyMapper  companyMapper
,BankSearchRepository bankSearchRepository,BankMapper  bankMapper
,UserExtraInfoSearchRepository userExtraInfoSearchRepository,UserExtraInfoMapper  userExtraInfoMapper
,PaymentTypeSearchRepository paymentTypeSearchRepository,PaymentTypeMapper  paymentTypeMapper
) {
        this.customerPaymentRepository = customerPaymentRepository;
        this.customerPaymentMapper = customerPaymentMapper;
        this.customerPaymentSearchRepository = customerPaymentSearchRepository;
                                    this.companySearchRepository = companySearchRepository;
                                     this.companyMapper = companyMapper;
                                    this.bankSearchRepository = bankSearchRepository;
                                     this.bankMapper = bankMapper;
                                    this.userExtraInfoSearchRepository = userExtraInfoSearchRepository;
                                     this.userExtraInfoMapper = userExtraInfoMapper;
                                    this.paymentTypeSearchRepository = paymentTypeSearchRepository;
                                     this.paymentTypeMapper = paymentTypeMapper;

    }

    /**
     * Save a customerPayment.
     *
     * @param customerPaymentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerPaymentDTO save(CustomerPaymentDTO customerPaymentDTO) {
        log.debug("Request to save CustomerPayment : {}", customerPaymentDTO);
        CustomerPayment customerPayment = customerPaymentMapper.toEntity(customerPaymentDTO);
        customerPayment = customerPaymentRepository.save(customerPayment);
        CustomerPaymentDTO result = customerPaymentMapper.toDto(customerPayment);
        customerPaymentSearchRepository.save(customerPayment);
        return result;
    }

    /**
     * Get all the customerPayments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerPaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerPayments");
        return customerPaymentRepository.findAll(pageable)
            .map(customerPaymentMapper::toDto);
    }

    /**
     * Get one customerPayment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerPaymentDTO findOne(Long id) {
        log.debug("Request to get CustomerPayment : {}", id);
        CustomerPayment customerPayment = customerPaymentRepository.findOne(id);
        return customerPaymentMapper.toDto(customerPayment);
    }

    /**
     * Delete the customerPayment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerPayment : {}", id);
        customerPaymentRepository.delete(id);
        customerPaymentSearchRepository.delete(id);
    }

    /**
     * Search for the customerPayment corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerPaymentDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerPayments for query {}", query);
        Page<CustomerPayment> result = customerPaymentSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerPaymentMapper::toDto);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<CustomerPaymentDTO> searchExample(CustomerPaymentSearchDTO searchDto, Pageable pageable) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotBlank(searchDto.getBranchName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("branchName", "*"+searchDto.getBranchName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getAccountNumber())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("accountNumber", "*"+searchDto.getAccountNumber()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getAccountName())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("accountName", "*"+searchDto.getAccountName()+"*"));
            }
            if(StringUtils.isNotBlank(searchDto.getCardNumber())) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("cardNumber", "*"+searchDto.getCardNumber()+"*"));
            }
            SearchQuery  query = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable).build();
            Page<CustomerPayment> customerPaymentPage= customerPaymentSearchRepository.search(query);
            List<CustomerPaymentDTO> customerPaymentList =  StreamSupport
            .stream(customerPaymentPage.spliterator(), false)
            .map(customerPaymentMapper::toDto)
            .collect(Collectors.toList());
            customerPaymentList.forEach(customerPaymentDto -> {
            if(customerPaymentDto.getCompanyId()!=null){
                Company company= companySearchRepository.findOne(customerPaymentDto.getCompanyId());
                customerPaymentDto.setCompanyDTO(companyMapper.toDto(company));
            }
            if(customerPaymentDto.getBankId()!=null){
                Bank bank= bankSearchRepository.findOne(customerPaymentDto.getBankId());
                customerPaymentDto.setBankDTO(bankMapper.toDto(bank));
            }
            if(customerPaymentDto.getUserVerifyId()!=null){
                UserExtraInfo userExtraInfo= userExtraInfoSearchRepository.findOne(customerPaymentDto.getUserVerifyId());
                customerPaymentDto.setUserVerifyDTO(userExtraInfoMapper.toDto(userExtraInfo));
            }
            if(customerPaymentDto.getPaymentTypeId()!=null){
                PaymentType paymentType= paymentTypeSearchRepository.findOne(customerPaymentDto.getPaymentTypeId());
                customerPaymentDto.setPaymentTypeDTO(paymentTypeMapper.toDto(paymentType));
            }
            });
            return new PageImpl<>(customerPaymentList,pageable,customerPaymentPage.getTotalElements());
        }
}
