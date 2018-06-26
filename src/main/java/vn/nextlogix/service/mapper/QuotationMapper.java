package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.QuotationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quotation and its DTO QuotationDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface QuotationMapper extends EntityMapper<QuotationDTO, Quotation> {

    @Mapping(source = "company.id", target = "companyId")
    QuotationDTO toDto(Quotation quotation);

    @Mapping(target = "quotationItems", ignore = true)
    @Mapping(source = "companyId", target = "company")
    Quotation toEntity(QuotationDTO quotationDTO);

    default Quotation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quotation quotation = new Quotation();
        quotation.setId(id);
        return quotation;
    }
}
