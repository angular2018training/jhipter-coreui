package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.CustomerLegalFileUploadDTO;
import vn.nextlogix.service.dto.FileUploadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerLegalFileUpload and its DTO CustomerLegalFileUploadDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, CustomerLegalMapper.class})
public interface CustomerLegalFileUploadMapper extends EntityMapper<CustomerLegalFileUploadDTO, CustomerLegalFileUpload> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "customerLegalParent.id", target = "customerLegalParentId")
    @Mapping(source="fileUpload",target="fileUploadDTO")
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
    
    default FileUploadDTO fromEntity(FileUpload fileUpload) {
    	if(fileUpload == null) return null;
    	FileUploadDTO fileUploadDTO = new FileUploadDTO();
    	fileUploadDTO.setId(fileUpload.getId());
    	fileUploadDTO.setName(fileUpload.getName());
    	fileUploadDTO.setHashedId(fileUpload.getHashedId());
    	return fileUploadDTO;
    }
    default FileUpload toEntity(Long fileUploadId ) {
    	if(fileUploadId == null) return null;
    	FileUpload fileUpload = new FileUpload();
    	fileUpload.setId(fileUploadId);
    	return fileUpload;
    }
    
}
