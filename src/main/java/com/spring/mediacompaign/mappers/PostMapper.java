package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.PostEntity;
import com.spring.mediacompaign.dao.models.PostModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(uses = {ImageMapper.class, VideoMapper.class, TextMapper.class},
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface PostMapper {

    @Mapping(source = "campaign.id", target = "campaignId")
    PostModel toModel(PostEntity entity);

    List<PostModel> toModels(List<PostEntity> entities);

    @Mapping(source = "campaignId", target = "campaign.id")
    PostEntity toEntity(PostModel model);

    List<PostEntity> toEntities(List<PostModel> models);
}
