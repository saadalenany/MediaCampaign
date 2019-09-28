package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.TextEntity;
import com.spring.mediacompaign.dao.models.TextModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface TextMapper {

    @Mapping(source = "post.id", target = "postId")
    TextModel toModel(TextEntity entity);

    List<TextModel> toModels(List<TextEntity> entities);

    @Mapping(source = "postId", target = "post.id")
    TextEntity toEntity(TextModel model);

    List<TextEntity> toEntities(List<TextModel> models);
}
