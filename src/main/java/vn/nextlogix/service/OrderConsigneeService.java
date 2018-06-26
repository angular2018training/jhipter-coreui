package vn.nextlogix.service;

import vn.nextlogix.service.dto.OrderConsigneeDTO;
import vn.nextlogix.service.dto.OrderConsigneeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderConsignee.
 */
public interface OrderConsigneeService {

    /**
     * Save a orderConsignee.
     *
     * @param orderConsigneeDTO the entity to save
     * @return the persisted entity
     */
    OrderConsigneeDTO save(OrderConsigneeDTO orderConsigneeDTO);

    /**
     * Get all the orderConsignees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderConsigneeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderConsignee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderConsigneeDTO findOne(Long id);

    /**
     * Delete the "id" orderConsignee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderConsignee corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderConsigneeDTO> search(String query, Pageable pageable);

    Page<OrderConsigneeDTO> searchExample( OrderConsigneeSearchDTO searchDTO, Pageable pageable);
    

    }
