package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.VideoEntity;
import com.spring.mediacompaign.dao.models.VideoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface VideoMapper {

    @Mapping(source = "post.id", target = "postId")
    VideoModel toModel(VideoEntity entity);

    List<VideoModel> toModels(List<VideoEntity> entities);

    @Mapping(source = "postId", target = "post.id")
    VideoEntity toEntity(VideoModel model);

    List<VideoEntity> toEntities(List<VideoModel> models);
}
