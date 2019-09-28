package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.SocialPlatformEntity;
import com.spring.mediacompaign.dao.models.SocialPlatformModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(uses = {CampaignMapper.class}, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface SocialPlatformMapper {

    SocialPlatformModel toModel(SocialPlatformEntity entity);

    List<SocialPlatformModel> toModels(List<SocialPlatformEntity> entities);

    SocialPlatformEntity toEntity(SocialPlatformModel model);

    List<SocialPlatformEntity> toEntities(List<SocialPlatformModel> models);
}
