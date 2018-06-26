package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.FileUploadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FileUpload and its DTO FileUploadDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface FileUploadMapper extends EntityMapper<FileUploadDTO, FileUpload> {

    @Mapping(source = "company.id", target = "companyId")
    FileUploadDTO toDto(FileUpload fileUpload);

    @Mapping(source = "companyId", target = "company")
    FileUpload toEntity(FileUploadDTO fileUploadDTO);

    default FileUpload fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileUpload fileUpload = new FileUpload();
        fileUpload.setId(id);
        return fileUpload;
    }
}
