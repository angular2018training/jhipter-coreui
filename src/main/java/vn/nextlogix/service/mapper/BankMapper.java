package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.BankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bank and its DTO BankDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface BankMapper extends EntityMapper<BankDTO, Bank> {

    @Mapping(source = "company.id", target = "companyId")
    BankDTO toDto(Bank bank);

    @Mapping(source = "companyId", target = "company")
    Bank toEntity(BankDTO bankDTO);

    default Bank fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bank bank = new Bank();
        bank.setId(id);
        return bank;
    }
}
