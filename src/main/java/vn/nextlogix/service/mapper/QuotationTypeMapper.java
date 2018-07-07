package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationType and its DTO QuotationTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuotationTypeMapper extends EntityMapper<QuotationTypeDTO, QuotationType> {



    default QuotationType fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationType quotationType = new QuotationType();
        quotationType.setId(id);
        return quotationType;
    }
}
