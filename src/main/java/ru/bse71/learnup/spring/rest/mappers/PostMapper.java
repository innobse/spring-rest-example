package ru.bse71.learnup.spring.rest.mappers;

import org.mapstruct.Mapper;
import ru.bse71.learnup.spring.rest.dto.PostDto;
import ru.bse71.learnup.spring.rest.entities.PostEntity;
import ru.bse71.learnup.spring.rest.model.Post;

/**
 * Created by bse71
 * Date: 06.09.2021
 * Time: 14:35
 */

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto(Post source);
    Post toModel(PostDto dto);
    PostEntity toEntity(Post source);
    Post toModel(PostEntity entity);
}
