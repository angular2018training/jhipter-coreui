package vn.nextlogix.service.mapper;

import vn.nextlogix.domain.*;
import vn.nextlogix.service.dto.PostOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PostOffice and its DTO PostOfficeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, ProvinceMapper.class})
public interface PostOfficeMapper extends EntityMapper<PostOfficeDTO, PostOffice> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "province.id", target = "provinceId")
    PostOfficeDTO toDto(PostOffice postOffice);

    @Mapping(source = "companyId", target = "company")
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
