package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.SourcePageEntity;
import com.spring.mediacompaign.dao.models.SourcePageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(uses = {CampaignMapper.class}, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface SourcePageMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    SourcePageModel toModel(SourcePageEntity entity);

    List<SourcePageModel> toModels(List<SourcePageEntity> entities);

    @Mapping(source = "ownerId", target = "owner.id")
    SourcePageEntity toEntity(SourcePageModel model);

    List<SourcePageEntity> toEntities(List<SourcePageModel> models);
}
