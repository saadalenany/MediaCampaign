package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.ImageEntity;
import com.spring.mediacompaign.dao.models.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface ImageMapper {

    @Mapping(source = "post.id", target = "postId")
    ImageModel toModel(ImageEntity entity);

    List<ImageModel> toModels(List<ImageEntity> entities);

    @Mapping(source = "postId", target = "post.id")
    ImageEntity toEntity(ImageModel model);

    List<ImageEntity> toEntities(List<ImageModel> models);

}
