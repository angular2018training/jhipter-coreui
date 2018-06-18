package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.BankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bank and its DTO BankDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankMapper extends EntityMapper<BankDTO, Bank> {



    default Bank fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bank bank = new Bank();
        bank.setId(id);
        return bank;
    }
}
