package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.FileUploadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FileUpload and its DTO FileUploadDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileUploadMapper extends EntityMapper<FileUploadDTO, FileUpload> {



    default FileUpload fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileUpload fileUpload = new FileUpload();
        fileUpload.setId(id);
        return fileUpload;
    }
}
