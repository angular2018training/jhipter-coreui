package vn.nextlogix.service.impl;

import vn.nextlogix.service.OrderServicesService;
import vn.nextlogix.domain.OrderServices;

import vn.nextlogix.repository.OrderServicesRepository;
import vn.nextlogix.repository.OrderServicesTypeRepository;

import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import vn.nextlogix.repository.search.OrderServicesSearchRepository;
import vn.nextlogix.service.dto.OrderServicesDTO;
import vn.nextlogix.service.dto.OrderServicesSearchDTO;
import org.springframework.data.domain.PageImpl;
import vn.nextlogix.domain.Company;
import vn.nextlogix.domain.OrderServicesType;
import vn.nextlogix.service.mapper.OrderServicesMapper;
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

import vn.nextlogix.repository.search.OrderServicesTypeSearchRepository;
import vn.nextlogix.service.mapper.OrderServicesTypeMapper;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderServices.
 */
@Service
@Transactional
public class OrderServicesServiceImpl implements OrderServicesService {

	private final Logger log = LoggerFactory.getLogger(OrderServicesServiceImpl.class);

	private final OrderServicesRepository orderServicesRepository;

	private final OrderServicesMapper orderServicesMapper;

	private final OrderServicesSearchRepository orderServicesSearchRepository;

	private final CompanySearchRepository companySearchRepository;
	private final CompanyMapper companyMapper;

	private final OrderServicesTypeRepository orderServicesTypeRepository;
	private final OrderServicesTypeSearchRepository orderServicesTypeSearchRepository;
	private final OrderServicesTypeMapper orderServicesTypeMapper;

	public OrderServicesServiceImpl(OrderServicesRepository orderServicesRepository,
			OrderServicesMapper orderServicesMapper, OrderServicesSearchRepository orderServicesSearchRepository,
			CompanySearchRepository companySearchRepository, CompanyMapper companyMapper,
			OrderServicesTypeRepository orderServicesTypeRepository,
			OrderServicesTypeSearchRepository orderServicesTypeSearchRepository,
			OrderServicesTypeMapper orderServicesTypeMapper) {
		this.orderServicesRepository = orderServicesRepository;
		this.orderServicesMapper = orderServicesMapper;
		this.orderServicesSearchRepository = orderServicesSearchRepository;
		this.companySearchRepository = companySearchRepository;
		this.companyMapper = companyMapper;
		this.orderServicesTypeRepository = orderServicesTypeRepository;
		this.orderServicesTypeSearchRepository = orderServicesTypeSearchRepository;
		this.orderServicesTypeMapper = orderServicesTypeMapper;

	}

	/**
	 * Save a orderServices.
	 *
	 * @param orderServicesDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public OrderServicesDTO save(OrderServicesDTO orderServicesDTO) {
		log.debug("Request to save OrderServices : {}", orderServicesDTO);
		OrderServices orderServices = orderServicesMapper.toEntity(orderServicesDTO);
		orderServices.setOrderServicesType(
				orderServicesTypeRepository.findOne(orderServices.getOrderServicesType().getId()));
		orderServices = orderServicesRepository.save(orderServices);
		OrderServicesDTO result = orderServicesMapper.toDto(orderServices);
		log.debug("vncud Request to save OrderServices : {}", orderServices.getOrderServicesType());
		orderServicesSearchRepository.save(orderServices);
		return result;
	}

	/**
	 * Get all the orderServices.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OrderServicesDTO> findAll(Pageable pageable) {
		log.debug("Request to get all OrderServices");
		return orderServicesRepository.findAll(pageable).map(orderServicesMapper::toDto);
	}

	/**
	 * Get one orderServices by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public OrderServicesDTO findOne(Long id) {
		log.debug("Request to get OrderServices : {}", id);
		OrderServices orderServices = orderServicesRepository.findOneWithEagerRelationships(id);
		return orderServicesMapper.toDto(orderServices);
	}

	/**
	 * Delete the orderServices by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete OrderServices : {}", id);
		orderServicesRepository.delete(id);
		orderServicesSearchRepository.delete(id);
	}

	/**
	 * Search for the orderServices corresponding to the query.
	 *
	 * @param query
	 *            the query of the search
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OrderServicesDTO> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of OrderServices for query {}", query);
		Page<OrderServices> result = orderServicesSearchRepository.search(queryStringQuery(query), pageable);
		//log.debug("vncud55 - Request to search for a page of OrderServices for query {}", result.getContent().get(0).getOrderServicesType());
		return result.map(orderServicesMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<OrderServicesDTO> searchExample(OrderServicesSearchDTO searchDto, Pageable pageable) {
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		if (StringUtils.isNotBlank(searchDto.getCode())) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("code", "*" + searchDto.getCode() + "*"));
		}
		if (StringUtils.isNotBlank(searchDto.getName())) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*" + searchDto.getName() + "*"));
		}
		if (StringUtils.isNotBlank(searchDto.getDescription())) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("description", "*" + searchDto.getDescription() + "*"));
		}
		if (searchDto.getCompanyId() != null) {
			boolQueryBuilder.must(QueryBuilders.matchQuery("company.id", searchDto.getCompanyId()));
		}
		if (searchDto.getOrderServicesTypeId() != null) {
			boolQueryBuilder.must(QueryBuilders.matchQuery("orderServicesType.id", searchDto.getOrderServicesTypeId()));
		}
		NativeSearchQueryBuilder queryBuilder = nativeSearchQueryBuilder.withQuery(boolQueryBuilder)
				.withPageable(pageable);

		pageable.getSort().forEach(sort -> {
			queryBuilder.withSort(SortBuilders.fieldSort(sort.getProperty())
					.order(sort.getDirection() == org.springframework.data.domain.Sort.Direction.ASC ? SortOrder.ASC
							: SortOrder.DESC)
					.unmappedType("long"));
		});
		NativeSearchQuery query = queryBuilder.build();
		Page<OrderServices> orderServicesPage = orderServicesSearchRepository.search(query);
		List<OrderServicesDTO> orderServicesList = StreamSupport.stream(orderServicesPage.spliterator(), false)
				.map(orderServicesMapper::toDto).collect(Collectors.toList());
		orderServicesList.forEach(orderServicesDto -> {
			if (orderServicesDto.getCompanyId() != null) {
				Company company = companySearchRepository.findOne(orderServicesDto.getCompanyId());
				orderServicesDto.setCompanyDTO(companyMapper.toDto(company));
			}
			if (orderServicesDto.getOrderServicesTypeId() != null) {
				OrderServicesType orderServicesType = orderServicesTypeSearchRepository
						.findOne(orderServicesDto.getOrderServicesTypeId());
				//log.debug("vncud 66 - Request to search for a page of OrderServicesType for query {}", result.getContent().get(0).getOrderServicesType());
				orderServicesDto.setOrderServicesTypeDTO(orderServicesTypeMapper.toDto(orderServicesType));
			}
		});
		return new PageImpl<>(orderServicesList, pageable, orderServicesPage.getTotalElements());
	}
}
