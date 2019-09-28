package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.TargetPageEntity;
import com.spring.mediacompaign.dao.models.TargetPageModel;
import com.spring.mediacompaign.dao.repos.TargetPageRepository;
import com.spring.mediacompaign.mappers.TargetPageMapper;
import com.spring.mediacompaign.services.api.TargetPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetPageServiceImpl implements TargetPageService {

    @Autowired
    private TargetPageRepository targetPageRepository;

    @Autowired
    private TargetPageMapper targetPageMapper;

    @Override
    public TargetPageModel save(TargetPageModel targetPageModel) {
        TargetPageEntity saved = targetPageRepository.save(targetPageMapper.toEntity(targetPageModel));
        return targetPageMapper.toModel(saved);
    }

    @Override
    public TargetPageModel update(TargetPageModel targetPageModel) {
        TargetPageEntity saved = targetPageRepository.save(targetPageMapper.toEntity(targetPageModel));
        return targetPageMapper.toModel(saved);
    }

    @Override
    public TargetPageModel getById(String id) {
        TargetPageEntity targetPageEntity = targetPageRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Target Page found with this id [%s]", id)));
        return targetPageMapper.toModel(targetPageEntity);
    }

    @Override
    public Boolean delete(String id) {
        targetPageRepository.deleteById(id);
        return !targetPageRepository.findById(id).isPresent();
    }

    @Override
    public List<TargetPageModel> list() {
        List<TargetPageEntity> all = targetPageRepository.findAll();
        return targetPageMapper.toModels(all);
    }
}