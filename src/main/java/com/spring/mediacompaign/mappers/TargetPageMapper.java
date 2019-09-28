package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.TargetPageEntity;
import com.spring.mediacompaign.dao.models.TargetPageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(uses = {CampaignMapper.class}, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface TargetPageMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    TargetPageModel toModel(TargetPageEntity entity);

    List<TargetPageModel> toModels(List<TargetPageEntity> entities);

    @Mapping(source = "ownerId", target = "owner.id")
    TargetPageEntity toEntity(TargetPageModel model);

    List<TargetPageEntity> toEntities(List<TargetPageModel> models);
}
