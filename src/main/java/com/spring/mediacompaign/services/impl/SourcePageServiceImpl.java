package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.SourcePageEntity;
import com.spring.mediacompaign.dao.models.SourcePageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.SourcePageRepository;
import com.spring.mediacompaign.mappers.SourcePageMapper;
import com.spring.mediacompaign.services.api.SourcePageService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourcePageServiceImpl implements SourcePageService, GeneralValidator<SourcePageModel> {

    @Autowired
    private SourcePageRepository sourcePageRepository;

    @Autowired
    private SourcePageMapper sourcePageMapper;

    @Override
    public VersionedModel save(SourcePageModel sourcePageModel) {
        final VersionedModel posted = post(sourcePageModel);
        if (posted != null) {
            return posted;
        }
        SourcePageEntity saved = sourcePageRepository.save(sourcePageMapper.toEntity(sourcePageModel));
        return sourcePageMapper.toModel(saved);
    }

    @Override
    public VersionedModel update(SourcePageModel sourcePageModel) {
        final VersionedModel putted = put(sourcePageModel);
        if (putted != null) {
            return putted;
        }
        SourcePageEntity saved = sourcePageRepository.save(sourcePageMapper.toEntity(sourcePageModel));
        return sourcePageMapper.toModel(saved);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public SourcePageModel getById(String id) {
        SourcePageEntity sourcePageEntity = sourcePageRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Source Page found with this id [%s]", id)));
        return sourcePageMapper.toModel(sourcePageEntity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public SourcePageModel getByPageUrl(String pageUrl) {
        SourcePageEntity sourcePageEntity = sourcePageRepository.findByPageUrl(pageUrl).orElse(null);
        return sourcePageMapper.toModel(sourcePageEntity);
    }

    @Override
    public Boolean delete(String id) {
        sourcePageRepository.deleteById(id);
        return !sourcePageRepository.findById(id).isPresent();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<SourcePageModel> list() {
        List<SourcePageEntity> all = sourcePageRepository.findAll();
        return sourcePageMapper.toModels(all);
    }
}
