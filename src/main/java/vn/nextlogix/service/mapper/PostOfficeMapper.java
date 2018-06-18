package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.PostOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PostOffice and its DTO PostOfficeDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceMapper.class})
public interface PostOfficeMapper extends EntityMapper<PostOfficeDTO, PostOffice> {

    @Mapping(source = "province.id", target = "provinceId")
    PostOfficeDTO toDto(PostOffice postOffice);

    @Mapping(source = "provinceId", target = "province")
    PostOffice toEntity(PostOfficeDTO postOfficeDTO);

    default PostOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        PostOffice postOffice = new PostOffice();
        postOffice.setId(id);
        return postOffice;
    }
}
