package com.spring.mediacompaign.mappers;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import com.spring.mediacompaign.dao.models.AdminModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(uses = {SourcePageMapper.class, TargetPageMapper.class},
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface AdminMapper {

    AdminModel toModel(AdminEntity entity);

    List<AdminModel> toModels(List<AdminEntity> entities);

    AdminEntity toEntity(AdminModel model);

    List<AdminEntity> toEntities(List<AdminModel> models);
}
