package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerLegalFileUploadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerLegalFileUpload and its DTO CustomerLegalFileUploadDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, FileUploadMapper.class, CustomerLegalMapper.class})
public interface CustomerLegalFileUploadMapper extends EntityMapper<CustomerLegalFileUploadDTO, CustomerLegalFileUpload> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "fileUpload.id", target = "fileUploadId")
    @Mapping(source = "customerLegalParent.id", target = "customerLegalParentId")
    CustomerLegalFileUploadDTO toDto(CustomerLegalFileUpload customerLegalFileUpload);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "fileUploadId", target = "fileUpload")
    @Mapping(source = "customerLegalParentId", target = "customerLegalParent")
    CustomerLegalFileUpload toEntity(CustomerLegalFileUploadDTO customerLegalFileUploadDTO);

    default CustomerLegalFileUpload fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerLegalFileUpload customerLegalFileUpload = new CustomerLegalFileUpload();
        customerLegalFileUpload.setId(id);
        return customerLegalFileUpload;
    }
}
