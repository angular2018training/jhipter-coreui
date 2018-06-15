package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerLegalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerLegal and its DTO CustomerLegalDTO.
 */
@Mapper(componentModel = "spring", uses = {FileUploadMapper.class})
public interface CustomerLegalMapper extends EntityMapper<CustomerLegalDTO, CustomerLegal> {



    default CustomerLegal fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerLegal customerLegal = new CustomerLegal();
        customerLegal.setId(id);
        return customerLegal;
    }
}
