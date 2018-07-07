package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.WeightDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Weight and its DTO WeightDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WeightMapper extends EntityMapper<WeightDTO, Weight> {



    default Weight fromId(Long id) {
        if (id == null) {
            return null;
        }
        Weight weight = new Weight();
        weight.setId(id);
        return weight;
    }
}
