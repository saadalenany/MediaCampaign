package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.TargetPageEntity;
import com.spring.mediacompaign.dao.models.TargetPageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.TargetPageRepository;
import com.spring.mediacompaign.mappers.TargetPageMapper;
import com.spring.mediacompaign.services.api.TargetPageService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TargetPageServiceImpl implements TargetPageService, GeneralValidator<TargetPageModel> {

    @Autowired
    private TargetPageRepository targetPageRepository;

    @Autowired
    private TargetPageMapper targetPageMapper;

    @Override
    public VersionedModel save(TargetPageModel targetPageModel) {
        final VersionedModel posted = post(targetPageModel);
        if (posted != null) {
            return posted;
        }
        TargetPageEntity saved = targetPageRepository.save(targetPageMapper.toEntity(targetPageModel));
        return targetPageMapper.toModel(saved);
    }

    @Override
    public VersionedModel update(TargetPageModel targetPageModel) {
        final VersionedModel putted = put(targetPageModel);
        if (putted != null) {
            return putted;
        }
        TargetPageEntity saved = targetPageRepository.save(targetPageMapper.toEntity(targetPageModel));
        return targetPageMapper.toModel(saved);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public TargetPageModel getById(String id) {
        TargetPageEntity targetPageEntity = targetPageRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Target Page found with this id [%s]", id)));
        return targetPageMapper.toModel(targetPageEntity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public TargetPageModel getByPageUrl(String pageUrl) {
        TargetPageEntity targetPageEntity = targetPageRepository.findByPageUrl(pageUrl).orElse(null);
        return targetPageMapper.toModel(targetPageEntity);
    }

    @Override
    public Boolean delete(String id) {
        targetPageRepository.deleteById(id);
        return !targetPageRepository.findById(id).isPresent();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<TargetPageModel> list() {
        List<TargetPageEntity> all = targetPageRepository.findAll();
        return targetPageMapper.toModels(all);
    }
}