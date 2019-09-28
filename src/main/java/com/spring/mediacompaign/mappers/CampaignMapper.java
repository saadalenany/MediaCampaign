package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.CampaignEntity;
import com.spring.mediacompaign.dao.models.CampaignModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(uses = {PostMapper.class}, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface CampaignMapper {

    @Mapping(source = "sourcePage.id", target = "sourcePageId")
    @Mapping(source = "targetPage.id", target = "targetPageId")
    @Mapping(source = "socialPlatform.id", target = "socialPlatformId")
    CampaignModel toModel(CampaignEntity entity);

    List<CampaignModel> toModels(List<CampaignEntity> entities);

    @Mapping(source = "sourcePageId", target = "sourcePage.id")
    @Mapping(source = "targetPageId", target = "targetPage.id")
    @Mapping(source = "socialPlatformId", target = "socialPlatform.id")
    CampaignEntity toEntity(CampaignModel model);

    List<CampaignEntity> toEntities(List<CampaignModel> models);
}
