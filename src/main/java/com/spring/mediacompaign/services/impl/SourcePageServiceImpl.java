package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.SourcePageEntity;
import com.spring.mediacompaign.dao.models.SourcePageModel;
import com.spring.mediacompaign.dao.repos.SourcePageReository;
import com.spring.mediacompaign.mappers.SourcePageMapper;
import com.spring.mediacompaign.services.api.SourcePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourcePageServiceImpl implements SourcePageService {

    @Autowired
    private SourcePageReository sourcePageRepository;

    @Autowired
    private SourcePageMapper sourcePageMapper;

    @Override
    public SourcePageModel save(SourcePageModel sourcePageModel) {
        SourcePageEntity saved = sourcePageRepository.save(sourcePageMapper.toEntity(sourcePageModel));
        return sourcePageMapper.toModel(saved);
    }

    @Override
    public SourcePageModel update(SourcePageModel sourcePageModel) {
        SourcePageEntity saved = sourcePageRepository.save(sourcePageMapper.toEntity(sourcePageModel));
        return sourcePageMapper.toModel(saved);
    }

    @Override
    public SourcePageModel getById(String id) {
        SourcePageEntity sourcePageEntity = sourcePageRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Source Page found with this id [%s]", id)));
        return sourcePageMapper.toModel(sourcePageEntity);
    }

    @Override
    public Boolean delete(String id) {
        sourcePageRepository.deleteById(id);
        return !sourcePageRepository.findById(id).isPresent();
    }

    @Override
    public List<SourcePageModel> list() {
        List<SourcePageEntity> all = sourcePageRepository.findAll();
        return sourcePageMapper.toModels(all);
    }
}
